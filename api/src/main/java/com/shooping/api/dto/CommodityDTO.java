package com.shooping.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommodityDTO implements Serializable {

    private Long id;
    private String commodityCode;
    private String name;
    private Integer count;

}
