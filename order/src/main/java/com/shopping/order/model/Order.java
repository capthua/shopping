package com.shopping.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Order {
    private Long id;
    private String name;
}
