package com.fran.shortlink.admin.test;

public class UserTableShardingTest {

    public static final String SQL = "CREATE TABLE `t_user_%d` (\n"
        + "  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n"
        + "  `username` VARCHAR(256) DEFAULT NULL COMMENT 'Username',\n"
        + "  `password` VARCHAR(512) DEFAULT NULL COMMENT 'Password',\n"
        + "  `real_name` VARCHAR(256) DEFAULT NULL COMMENT 'Real Name',\n"
        + "  `phone` VARCHAR(128) DEFAULT NULL COMMENT 'Phone Number',\n"
        + "  `email` VARCHAR(512) DEFAULT NULL COMMENT 'Email',\n"
        + "  `deletion_time` BIGINT(20) DEFAULT NULL COMMENT 'Deletion Timestamp',\n"
        + "  `create_time` DATETIME DEFAULT NULL COMMENT 'Creation Time',\n"
        + "  `update_time` DATETIME DEFAULT NULL COMMENT 'Update Time',\n"
        + "  `del_flag` TINYINT(1) DEFAULT NULL COMMENT 'Deletion Flag: 0 = Not Deleted, 1 = Deleted',\n"
        + "  PRIMARY KEY (`id`)\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }
}
