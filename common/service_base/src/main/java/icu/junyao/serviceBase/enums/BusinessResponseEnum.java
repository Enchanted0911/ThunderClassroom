package cn.bizfocus.scm.order.enums;


import cn.bizfocus.scm.order.exception.assertion.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>通用返回结果</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
@Getter
@AllArgsConstructor
public enum BusinessResponseEnum implements BusinessExceptionAssert {
    /**
     * 请输入有效的用户名以及密码
     */
    PWD_ERROR("A0210", "请输入有效的用户名以及密码。"),
    /**
     * 新密码不能和旧密码相同
     */
    NEW_PWD_EQUALS_OLD("A0212", "新密码不能与旧密码相同!"),
    /**
     * 用户名已存在
     */
    USER_NAME_REPEAT("A0211", "用户名已存在"),
    /**
     * 文件不存在!
     */
    FILE_IS_EMPTY("A0213", "文件不存在!"),
    /**
     * 用户不存在!
     */
    USER_NOT_FOUND("A0214", "用户不存在!"),
    /**
     * 装瓶厂不存在!
     */
    BOTTLE_NOT_FOUND("A0215", "装瓶厂不存在!"),
    /**
     * 关系不存在!
     */
    RELATION_NOT_EXISTS("A0216", "关系不存在!"),
    /**
     * 关系已经存在!
     */
    RELATION_ALREADY_EXISTS("A0216", "关系已经存在!"),

    /**
     * 广告不存在!
     */
    AD_NOT_FOUND("A0216", "广告不存在!"),

    /**
     * 客服邮件不存在
     */
    CM_NOT_FOUND("A0217", "客服邮件不存在!"),

    /**
     * 购物车中没有该物品!
     */
    NOT_EXIST_IN_SHOPPING_CART("A0218", "购物车中没有该物品!"),
    /**
     * 收藏夹中已存在该物品
     */
    ALREADY_EXIST_IN_FAVORITE("A0219", "收藏夹中已存在该商品!"),
    /**
     * 小订单周次获取失败
     */
    MOQ_WEEK_NOT_FOUND("A0220", "小订单周次获取失败!"),
    /**
     * 承运商没找到!
     */
    CARRIER_NOT_FOUND("A0221", "承运商不存在!"),
    /**
     * 工厂没找到!
     */
    FACTORY_NOT_FOUND("A0222", "工厂不存在!"),
    /**
     * 获取接口数据失败
     */
    DATA_FETCH_FAILED("A0223", "从接口获取数据失败"),

    /**
     * 投诉单不存在或不可修改
     */
    COMPLAINT_NOT_FOUNT("A0223", "投诉单不存在或不可修改！"),

    /**
     * 问题不存在
     */
    QUESTION_NOT_FOUNT("A0223", "问题不存在！"),

    /**
     * 购物车容量已满
     */
    SHOPPING_CART_CAPACITY_FULL("A0224", "购物车已满!"),

    /**
     * 广告位不存在!
     */
    AD_POS_NOT_FOUND("A0225", "广告位不存在!"),

    /**
     * 公告不存在!
     */
    PUBLIC_MSG_NOT_FOUND("A0226", "公告不存在!"),
    /**
     * 商品不存在!
     */
    COMMODITY_NOT_FOUND("A0227", "商品不存在!"),
    /**
     * 商品不存在!
     */
    CONDITION_TYPE_ERROR("A0228", "条件类型错误!");


    private final String code;

    private final String message;

}
