package com.shopping.demo.prs;

import com.shopping.common.prs.PRS;
import lombok.Data;

@Data
@PRS
public class Male {

    private String name;
    private Integer age;
    private Integer stature;

}
