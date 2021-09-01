package icu.junyao.serviceBase.exception.assertion;

import icu.junyao.serviceBase.enums.ResponseEnum;
import icu.junyao.serviceBase.exception.BaseException;

import java.text.MessageFormat;


/**
 * <pre>
 *
 * </pre>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
public interface BaseExceptionAssert extends ResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BaseException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BaseException(this, args, msg, t);
    }

}
