package com.shooping.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BusinessDTO implements Serializable {

    private String userId;

    private String commodityCode;

    private String name;

    private Integer count;

    private BigDecimal amount;

}
