package icu.junyao.security.security;

import cn.bizfocus.scm.order.constant.CacheConstants;
import cn.bizfocus.scm.order.entity.User;
import cn.bizfocus.scm.order.mapper.UserMapper;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.security.contant.CacheConstants;
import icu.junyao.security.entity.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wu
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    private final CacheManager cacheManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return cache.get(username, JwtUser.class);
        }
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getUsername, username);
        User user = userMapper.selectOne(lambdaQuery);
        if (user == null) {
            throw new BadCredentialsException("用户名密码错误");
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(Convert.toStrArray(new ArrayList<>()));
        JwtUser jwtUser = new JwtUser(user.getId(), user.getUsername(), user.getRealName(), user.getAvatar(), user.getPassword(), authorityList);
        cache.put(username, jwtUser);
        return jwtUser;
    }


}

