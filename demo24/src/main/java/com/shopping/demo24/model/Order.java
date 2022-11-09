package com.shopping.demo24.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    private Long id;
    private Long userId;
    private int status;
}
