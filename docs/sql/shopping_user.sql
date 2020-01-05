create schema if not exists shopping DEFAULT CHARSET = utf8;

use shopping;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`              int(11)     NOT NULL AUTO_INCREMENT,
    `username`        varchar(24) NOT NULL,
    `password`        varchar(24) NOT NULL,
    `full_name`       varchar(96) NOT NULL,
    `email`           varchar(48) NOT NULL,
    `update_by_email` tinyint(1)  NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);

INSERT INTO shopping.user (username, password, full_name, email, update_by_email)
VALUES ('hsh', '02042018', '韩少华', 'hshua24@gmail.com', 0);
INSERT INTO shopping.user (username, password, full_name, email, update_by_email)
VALUES ('steve', '22222', '乔布斯', 'steve@apple.com', 0);

