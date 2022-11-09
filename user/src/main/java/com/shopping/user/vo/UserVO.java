package com.shopping.user.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phoneNo;
    private Long createTime;
    private Long updateTime;
}
