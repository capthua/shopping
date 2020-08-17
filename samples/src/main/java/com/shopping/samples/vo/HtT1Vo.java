package com.shopping.samples.vo;

import com.shopping.samples.model.HtT1;
import lombok.Data;

import java.util.List;

@Data
public class HtT1Vo extends HtT1 {

    List<HtT1Vo> children;

}
