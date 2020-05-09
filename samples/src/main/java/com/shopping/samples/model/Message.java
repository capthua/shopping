package com.shopping.samples.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class Message implements Serializable {

    private Long id;
    private String name;
    private String content;

}
