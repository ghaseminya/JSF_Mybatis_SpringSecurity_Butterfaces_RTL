CREATE DATABASE IF NOT EXISTS `db`;
USE `db`;

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` text,
  PRIMARY KEY (`id`)
)

INSERT INTO `roles` (`id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'MODERATOR'),
	(3, 'VIEW');

CREATE TABLE IF NOT EXISTS `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` text,
  `user_password` text,
  `user_full_name` text,
  PRIMARY KEY (`id`)
);

INSERT INTO `user_details` (`id`, `user_name`, `user_password`, `user_full_name`) VALUES
	(1, 'test', 'test', 'Test Test'),
	(2, 'viewer', 'viewer', 'Viewer Test');

CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_role_idx` (`role_id`),
  KEY `FK_user_idx` (`user_id`),
  CONSTRAINT `FK_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO `user_roles` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 2, 3);