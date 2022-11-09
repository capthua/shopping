package com.shopping.common.exception;

import com.shopping.common.response.RspStatusEnum;

public class DefaultException extends RuntimeException {

    private RspStatusEnum rspStatusEnum;

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(RspStatusEnum rspStatusEnum) {
        super(rspStatusEnum.getMsg());
        this.rspStatusEnum = rspStatusEnum;
    }

    public DefaultException(RspStatusEnum rspStatusEnum, Throwable cause) {
        super(rspStatusEnum.getMsg(), cause);
        this.rspStatusEnum = rspStatusEnum;
    }

    public RspStatusEnum getRspStatusEnum() {
        return rspStatusEnum;
    }

    public void setRspStatusEnum(RspStatusEnum rspStatusEnum) {
        this.rspStatusEnum = rspStatusEnum;
    }
}
