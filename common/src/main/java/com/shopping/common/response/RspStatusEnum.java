package com.shopping.common.response;

/**
 * @author CaptHua
 */
public enum RspStatusEnum {

    /**
     * 返回值，成功
     */
    SUCCESS(200, "成功"),

    FAIL(999, "失败"),

    EXCEPTION(500, "系统异常");

    private int status;
    private String msg;

    RspStatusEnum(int status, String message) {
        this.status = status;
        this.msg = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
