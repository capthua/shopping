package com.shopping.samples.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "ht_t1")
@Data
public class HtT1 {

    private Integer id;
    private String name;
    private String description;
    @Column(name = "update_by_email")
    private Integer updateByEmail;
    @Column(name ="parent_id")
    private Integer parentId;

}
