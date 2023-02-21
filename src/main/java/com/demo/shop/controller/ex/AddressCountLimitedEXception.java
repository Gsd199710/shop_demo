package com.demo.shop.controller.ex;

import com.demo.shop.service.ex.ServiceException;

/***
 * 收货地址个数超出(20个)限制异常
 */
public class AddressCountLimitedEXception extends ServiceException {
    public AddressCountLimitedEXception() {
        super();
    }

    public AddressCountLimitedEXception(String message) {
        super(message);
    }

    public AddressCountLimitedEXception(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitedEXception(Throwable cause) {
        super(cause);
    }

    protected AddressCountLimitedEXception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
