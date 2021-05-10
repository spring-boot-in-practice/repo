CREATE TABLE COURSES (
  id          BIGINT NOT NULL auto_increment,
  category    VARCHAR(255),
  description VARCHAR(255),
  name        VARCHAR(255),
  rating      INTEGER NOT NULL,
  PRIMARY KEY (id)
);

create table CT_USERS(
	id BIGINT NOT NULL auto_increment,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50),
    username varchar(50),
    password varchar(100),
    verified smallint(1),
    PRIMARY KEY (id)
);

create table CT_EMAIL_VERIFICATIONS(
    verification_id varchar(50),
    username varchar(50),
    PRIMARY KEY (verification_id)
);