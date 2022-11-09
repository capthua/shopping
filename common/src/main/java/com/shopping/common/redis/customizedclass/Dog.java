package com.shopping.common.redis.customizedclass;

import java.io.Serializable;

public class Dog implements Serializable {

    private String name;
    private Integer weight;

    public Dog(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
