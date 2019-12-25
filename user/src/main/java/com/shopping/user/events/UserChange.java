package com.shopping.user.events;

public class UserChange {

    Integer id;
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserChanges{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
