package icu.junyao.security.handler;

import cn.bizfocus.scm.order.res.DictItemRes;
import cn.bizfocus.scm.order.res.LoginRes;
import cn.bizfocus.scm.order.res.R;
import cn.bizfocus.scm.order.res.UserInfoRes;
import cn.bizfocus.scm.order.security.JwtUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.properties.JwtProperties;
import icu.junyao.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wu
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final JwtProperties jwtProperties;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse response, Authentication authentication) throws IOException {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = response.getWriter();
        LoginRes loginRes = new LoginRes();
        loginRes.setAccessToken(jwtUtil.createAccessToken(jwtUser));
        loginRes.setRefreshToken(jwtUtil.createRefreshToken(jwtUser));
        loginRes.setExpiresIn(jwtProperties.getAccessTokenExpire());

        UserInfoRes userInfoRes = new UserInfoRes();
        userInfoRes.setRealName(jwtUser.getRealName());
        userInfoRes.setUsername(jwtUser.getUsername());
        userInfoRes.setAvatar(jwtUser.getAvatar());
        loginRes.setUserInfo(userInfoRes);
        Map<String, List<DictItemRes>> dictItemRes = new HashMap<>();
        List<DictItemRes> globalPermsTypeList = new ArrayList<>();
        //TODO 写死的字典
        DictItemRes globalPermis1 = new DictItemRes("1","可见/可访问(授权后可见/可访问)");
        DictItemRes globalPermis2 = new DictItemRes("2","可编辑(未授权时禁用)");
        globalPermsTypeList.add(globalPermis1);
        globalPermsTypeList.add(globalPermis2);
        dictItemRes.put("global_perms_type",globalPermsTypeList);
        List<DictItemRes> validStatus = new ArrayList<>();
        DictItemRes validStatus1 = new DictItemRes("0","无效");
        DictItemRes validStatus2 = new DictItemRes("1","有效");
        validStatus.add(validStatus1);
        validStatus.add(validStatus2);
        dictItemRes.put("valid_status",validStatus);
        loginRes.setDictItem(dictItemRes);
        writer.write(objectMapper.writeValueAsString(R.data(loginRes)));
    }
}
