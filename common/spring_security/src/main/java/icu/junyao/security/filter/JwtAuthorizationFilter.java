package icu.junyao.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wu
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;


    public JwtAuthorizationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager,UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (checkJwtToken(request)) {
            jwtUtil.validateToken(request)
                    .ifPresentOrElse(this::setUpSpringAuthentication, SecurityContextHolder::clearContext);
        }
        chain.doFilter(request, response);
    }

    /**
     * 构造 Authentication
     */
    private void setUpSpringAuthentication(DecodedJWT decodedJWT) {
        //获取用户信息 重新加载下
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /**
     * 检查 JWT Token 是否在 HTTP 报头中
     *
     * @param req HTTP 请求
     * @return 是否有 JWT Token
     */
    private boolean checkJwtToken(HttpServletRequest req) {
        String authenticationHeader = req.getHeader("Authorization");
        return authenticationHeader != null;
    }
}
