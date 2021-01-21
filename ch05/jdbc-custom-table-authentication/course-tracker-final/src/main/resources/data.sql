INSERT into CT_USERS(ct_user, ct_password, ct_enabled) values ('user','p@ssw0rd', true);
INSERT into CT_USERS(ct_user, ct_password, ct_enabled) values ('admin','pa$$w0rd', true);

INSERT into CT_AUTHORITIES(ct_user, ct_authority) values ('user','USER');
INSERT into CT_AUTHORITIES(ct_user, ct_authority) values ('admin','ADMIN');

INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)  VALUES(1, 'Rapid Spring Boot Application Development', 'Spring', 4, 'Spring Boot gives all the power of the Spring Framework without all of the complexity');
INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)  VALUES(2, 'Getting Started with Spring Security DSL', 'Spring', 5, 'Learn Spring Security DSL in easy steps');
INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)  VALUES(3, 'Getting Started with Spring Cloud Kubernetes', 'Python', 3, 'Master Spring Boot application deployment with Kubernetes');
