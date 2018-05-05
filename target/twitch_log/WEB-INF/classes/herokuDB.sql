
-- auto-generated definition
CREATE TABLE User
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  code     VARCHAR(255) NULL,
  email    VARCHAR(255) NULL,
  roles    VARCHAR(255) NULL,
  username VARCHAR(255) NULL
)
  ENGINE = InnoDB;