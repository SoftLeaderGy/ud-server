-- 建表
CREATE TABLE `t_user` (
                          `username` varchar(255) DEFAULT NULL,
                          `password` varchar(255) DEFAULT NULL,
                          `id` varchar(255) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `t_files` (
                           `id` varchar(100) NOT NULL,
                           `oldFileName` varchar(255) DEFAULT NULL,
                           `newFileName` varchar(255) DEFAULT NULL,
                           `ext` varchar(255) DEFAULT NULL,
                           `size` varchar(255) DEFAULT NULL,
                           `type` varchar(255) DEFAULT NULL,
                           `isImg` varchar(255) DEFAULT NULL,
                           `downcount` int DEFAULT NULL,
                           `uploadTime` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
