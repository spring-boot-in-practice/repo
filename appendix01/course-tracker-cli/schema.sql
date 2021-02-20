create table COURSES (
    COURSE_ID identity not null,
    COURSE_NAME varchar(100) not null,
    COURSE_CATEGORY varchar(10) not null,
    COURSE_RATING tinyint not null,
    COURSE_DESCRIPTION varchar(500) not null
);
