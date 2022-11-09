package com.shopping.demo24.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
@Data
public class User {
    @Id
    private Long id;
    private String name;

}
