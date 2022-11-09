package com.shooping.api.service.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderParam implements Serializable {
    Long orderId;
}
