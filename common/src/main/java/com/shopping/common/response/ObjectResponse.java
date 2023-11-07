package com.shopping.common.response;

import lombok.Data;

@Data
public class ObjectResponse<T> extends BaseResponse {

    private T data;

    public ObjectResponse(){}

    public ObjectResponse(T object){
        this.data=object;
    }

}
