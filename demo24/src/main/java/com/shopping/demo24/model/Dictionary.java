package com.shopping.demo24.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_order")
@Data
public class Dictionary {

    @TableId
    private Long id;
    private String code;
    private String name;
    private String value;

}
