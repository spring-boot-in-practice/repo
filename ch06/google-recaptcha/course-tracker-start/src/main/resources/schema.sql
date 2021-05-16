DROP TABLE IF EXISTS courses;
CREATE TABLE courses (
  id          BIGINT NOT NULL auto_increment,
  category    VARCHAR(255),
  description VARCHAR(255),
  name        VARCHAR(255),
  rating      INTEGER NOT NULL,
  PRIMARY KEY (id)
) engine=innodb;

drop table if exists ct_users;
create table ct_users(
	id BIGINT NOT NULL auto_increment,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50),
    username varchar(50),
    password varchar(100),
    verified smallint(1),
    PRIMARY KEY (id)
);

drop table if exists ct_email_verifications;
create table ct_email_verifications(
    verification_id varchar(50),
    username varchar(50),
    PRIMARY KEY (verification_id)
)