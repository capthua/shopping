create table t_a
(
    id   int auto_increment
        primary key,
    name varchar(256) null
);

create table t_a_detail
(
    id   int auto_increment
        primary key,
    name varchar(256) null,
    a_id int          null
);

1	han
2	zhang
3	hanwang

