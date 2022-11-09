package com.shopping.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {

    private int status=200;
    private String msg;

}
