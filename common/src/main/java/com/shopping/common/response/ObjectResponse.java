package com.shopping.common.response;

import lombok.Data;

@Data
public class ObjectResponse<T> extends BaseResponse {

    private T data;

}
