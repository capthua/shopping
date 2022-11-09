package com.shopping.demo24.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_a_detail")
public class ADetail {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "a_id")
    private Integer aId;

}
