package icu.junyao.serviceBase.exception;

import icu.junyao.serviceBase.enums.ResponseEnum;

/**
 * <p>业务异常</p>
 * <p>业务处理时，出现异常，可以抛出该异常</p>
 *
 * @author wu
 */
public class BusinessException extends BaseException {

    public BusinessException(String code, String msg){
        super(code,msg);

    }

    private static final long serialVersionUID = 1L;

    public BusinessException(ResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BusinessException(ResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}