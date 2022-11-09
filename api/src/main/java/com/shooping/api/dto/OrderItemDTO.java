package com.shooping.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItemDTO implements Serializable {

    private Long goodsId;
    private Integer quantity;

}
