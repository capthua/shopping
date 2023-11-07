package com.shooping.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author CaptHua
 */
@Data
public class GoodsDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;

}
