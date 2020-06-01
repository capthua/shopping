package com.shopping.samples.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "sample_b")
@Builder
public class SampleB {
    @Id
    private Integer id; //添加@Id后，才能自动生成id
    private String name;
}
