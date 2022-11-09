package com.shopping.user.dao.dataobject;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "t_user")
public class User implements Serializable {

    @Id
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phoneNo;

}
