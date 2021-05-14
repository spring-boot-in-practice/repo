DROP TABLE IF EXISTS courses;
CREATE TABLE courses (
  id          BIGINT NOT NULL auto_increment,
  category    VARCHAR(255),
  description VARCHAR(255),
  name        VARCHAR(255),
  rating      INTEGER NOT NULL,
  PRIMARY KEY (id)
);