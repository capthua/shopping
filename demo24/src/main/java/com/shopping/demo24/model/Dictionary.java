package com.shopping.demo24.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_dictionary")
@Data
public class Dictionary {

    @Id
    private Long id;
    private String code;
    private String name;
    private String value;

}
