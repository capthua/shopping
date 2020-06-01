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