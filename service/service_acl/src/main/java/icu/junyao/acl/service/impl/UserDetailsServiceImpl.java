package icu.junyao.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.acl.entity.Permission;
import icu.junyao.acl.entity.RolePermission;
import icu.junyao.acl.entity.User;
import icu.junyao.acl.entity.UserRole;
import icu.junyao.acl.mapper.UserMapper;
import icu.junyao.acl.service.PermissionService;
import icu.junyao.acl.service.RolePermissionService;
import icu.junyao.acl.service.UserRoleService;
import icu.junyao.security.contant.CacheConstants;
import icu.junyao.security.entity.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author wu
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final PermissionService permissionService;
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

        List<GrantedAuthority> authorityList = permissionService.selectPermissionValueByUserId(user.getId())
                .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        JwtUser jwtUser = new JwtUser(user.getId(), user.getUsername(), user.getNickName(), user.getAvatar(), user.getPassword(), authorityList);
        cache.put(username, jwtUser);
        return jwtUser;
    }


}

