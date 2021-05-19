package com.shopping.demo.prs;

import com.shopping.common.annotation.PRS;
import lombok.Data;

@Data
@PRS
public class Person {

    private String name;
    private Integer age;

}
