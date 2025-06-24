package com.fran.shortlink.admin.test;

public class UserTableShardingTest {

    public static final String SQL = "CREATE TABLE `t_group_%d` (\n"
        + "    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n"
        + "    `gid`         VARCHAR(32)  DEFAULT NULL COMMENT 'Group Identifier',\n"
        + "    `name`        VARCHAR(64)  DEFAULT NULL COMMENT 'Group Name',\n"
        + "    `username`    VARCHAR(256) DEFAULT NULL COMMENT 'Username of Group Creator',\n"
        + "    `sort_order`  INT(3)        DEFAULT NULL COMMENT 'Group Sort Order',\n"
        + "    `create_time` DATETIME    DEFAULT NULL COMMENT 'Creation Time',\n"
        + "    `update_time` DATETIME    DEFAULT NULL COMMENT 'Last Modified Time',\n"
        + "    `del_flag`    TINYINT(1)   DEFAULT NULL COMMENT 'Deletion Flag: 0 = Not Deleted, 1 = Deleted',\n"
        + "    PRIMARY KEY (`id`),\n"
        + "    UNIQUE KEY `idx_unique_username_gid` (`gid`, `username`) USING BTREE\n"
        + ") ENGINE=InnoDB \n"
        + "  AUTO_INCREMENT=1716734146606301186 \n"
        + "  DEFAULT CHARSET=utf8mb4;";

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }
}
