package com.shopping.demo.prs;

import com.shopping.common.annotation.PRS;
import com.shopping.demo.controller.AController;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@PRS("male-p")
public class Male {

    @Autowired
    AController aController;

    private String name;
    private Integer age;
    private Integer stature;

}
