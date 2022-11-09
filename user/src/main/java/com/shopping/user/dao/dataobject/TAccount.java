package com.shopping.user.dao.dataobject;

import javax.persistence.Table;

@Table(name = "t_account")
public class TAccount {
    private Long id;
    private Long userId;
    private Double amount;
}
