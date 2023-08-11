package com.shopping.user.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;

@Data
@TableName("t_user")
public class User implements Serializable {

    @TableId
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phoneNo;

}


