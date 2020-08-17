create table sample
(
    id          int auto_increment
        primary key,
    name        varchar(64)          not null,
    description varchar(128) null,
    create_time datetime     not null,
    modify_time date         null,
    constraint sample_name_uindex
        unique (name)
);

create table if not exists ht_t1
(
    id              int auto_increment
        primary key,
    name            varchar(64)  null,
    description     varchar(128) null,
    update_by_email tinyint(1)   null,
    parent_id       int          null
);