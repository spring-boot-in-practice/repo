ALTER TABLE authors_courses
  DROP FOREIGN KEY course_id_fk;

ALTER TABLE authors_courses
  DROP FOREIGN KEY author_id_fk;

DROP TABLE IF EXISTS authors;

DROP TABLE IF EXISTS authors_courses CASCADE;

DROP TABLE IF EXISTS courses;

CREATE TABLE authors (
  id   BIGINT NOT NULL auto_increment,
  bio  VARCHAR(255),
  name VARCHAR(255),
  PRIMARY KEY (id)
) engine=innodb;

CREATE TABLE authors_courses (
  author_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  PRIMARY KEY (author_id, course_id)
) engine=innodb;

CREATE TABLE courses (
  id          BIGINT NOT NULL auto_increment,
  category    VARCHAR(255),
  description VARCHAR(255),
  name        VARCHAR(255),
  rating      INTEGER NOT NULL,
  PRIMARY KEY (id)
) engine=innodb;

ALTER TABLE authors_courses
  ADD CONSTRAINT course_id_fk FOREIGN KEY (course_id) REFERENCES courses (id);

ALTER TABLE authors_courses
  ADD CONSTRAINT author_id_fk FOREIGN KEY (author_id) REFERENCES authors (id);