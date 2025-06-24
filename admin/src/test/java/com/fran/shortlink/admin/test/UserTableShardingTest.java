package com.fran.shortlink.admin.test;

public class UserTableShardingTest {

    public static final String SQL = "CREATE TABLE `t_link_%d` (\n"
        + "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n"
        + "  `domain` varchar(128) DEFAULT NULL COMMENT 'Domain',\n"
        + "  `short_uri` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Short URI',\n"
        + "  `full_short_url` varchar(128) DEFAULT NULL COMMENT 'Full Short URL',\n"
        + "  `origin_url` varchar(1024) DEFAULT NULL COMMENT 'Original URL',\n"
        + "  `click_count` int(11) DEFAULT 0 COMMENT 'Click Count',\n"
        + "  `gid` varchar(32) DEFAULT NULL COMMENT 'Group Identifier',\n"
        + "  `favicon` varchar(256) DEFAULT NULL COMMENT 'Web Icon',\n"
        + "  `enable_status` tinyint(1) DEFAULT NULL COMMENT 'Enable Status: 0 = Disabled, 1 = Enabled',\n"
        + "  `created_type` tinyint(1) DEFAULT NULL COMMENT 'Creation Type: 0 = Console, 1 = API',\n"
        + "  `valid_date_type` tinyint(1) DEFAULT NULL COMMENT 'Validity Type: 0 = Permanent, 1 = Custom',\n"
        + "  `valid_date` datetime DEFAULT NULL COMMENT 'Expiration Date',\n"
        + "  `description` varchar(1024) DEFAULT NULL COMMENT 'Description',\n"
        + "  `create_time` datetime DEFAULT NULL COMMENT 'Creation Time',\n"
        + "  `update_time` datetime DEFAULT NULL COMMENT 'Last Modified Time',\n"
        + "  `del_flag` tinyint(1) DEFAULT NULL COMMENT 'Deletion Flag: 0 = Not Deleted, 1 = Deleted',\n"
        + "  PRIMARY KEY (`id`),\n"
        + "  UNIQUE KEY `idx_unique_full_short_uri` (`full_short_url`) USING BTREE\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }
}
