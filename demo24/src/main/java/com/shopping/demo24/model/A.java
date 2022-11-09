package com.shopping.demo24.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "t_a")
public class A implements Serializable {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;

}
