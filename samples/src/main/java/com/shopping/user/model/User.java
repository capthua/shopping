package com.shopping.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Column(name = "update_by_email")
    private Integer updateByEmail;

}
