package icu.junyao.security.Res;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 登录返回
 *
 * @author wu
 */
@Data
public class LoginRes {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;


    /**
     * 过期时间
     */
    private Long expiresIn;

    private String username;

    private String nickname;

    private String avatar;
}
