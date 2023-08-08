#### 用户库
create schema sp_user collate utf8_general_ci;
use sp_user;
create table t_user
(
    id       bigint      not null
        primary key,
    username varchar(32) null,
    password varchar(64) null,
    nickname varchar(64) null,
    phone_no varchar(16) null
);
INSERT INTO sp_user.t_user (id, username, password, nickname, phone_no)
VALUES (1, 'capthua', '88888888', 'Capt. Hua', '13566668888');
create table t_account
(
    id      bigint not null
        primary key,
    user_id bigint null,
    amount  double(14, 2) DEFAULT '0.00'
);
INSERT INTO sp_user.t_account (id, user_id, amount)
VALUES (1, 1, 200000);
CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

#### 商品库
create schema sp_goods collate utf8_general_ci;
use sp_goods;
create table t_goods
(
    id          bigint       not null
        primary key,
    name        varchar(64)  null,
    description varchar(512) null,
    quantity    bigint       null,
    price       decimal      null
);
INSERT INTO sp_goods.t_goods (id, name, description, quantity, price)
VALUES (1, 'MacBook', '苹果电脑', 10, 12400);
CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

#### 订单库
create schema sp_order collate utf8_general_ci;
use sp_order;
create table t_order
(
    id          bigint  not null
        primary key,
    create_time bigint  null,
    modify_time bigint  null,
    total_cost  decimal null comment '商品总额',
    state       tinyint null comment '订单状态',
    user_id     bigint  null
);
create table t_order_item
(
    id       bigint not null
        primary key,
    order_id bigint null,
    goods_id bigint null,
    quantity int    null
);
CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
