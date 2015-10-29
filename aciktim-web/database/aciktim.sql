DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id`        INT(11) UNSIGNED        NOT NULL AUTO_INCREMENT,
  `email`     VARCHAR(128)
              COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `passwd`    CHAR(32)
              COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `full_name` VARCHAR(255)
              COLLATE utf8_unicode_ci NOT NULL,
  `username`  VARCHAR(255)
              COLLATE utf8_unicode_ci NOT NULL,
  `api_key`   CHAR(32)
              COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
