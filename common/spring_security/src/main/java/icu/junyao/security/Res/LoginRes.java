package cn.bizfocus.scm.order.res;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 登录返回
 *
 * @author songxuan
 * @date 2021/07/29
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

    private UserInfoRes userInfo;

    private Map<String, List<DictItemRes>> dictItem;
}
