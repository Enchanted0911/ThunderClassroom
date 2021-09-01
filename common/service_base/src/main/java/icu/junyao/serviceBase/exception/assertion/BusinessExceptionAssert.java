package cn.bizfocus.scm.order.exception.assertion;


import cn.bizfocus.scm.order.enums.ResponseEnum;
import cn.bizfocus.scm.order.exception.BaseException;
import cn.bizfocus.scm.order.exception.BusinessException;

import java.text.MessageFormat;

/**
 * @author sprainkle
 * @date 2019/5/2
 */
public interface BusinessExceptionAssert extends ResponseEnum, Assert {




    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }

}
