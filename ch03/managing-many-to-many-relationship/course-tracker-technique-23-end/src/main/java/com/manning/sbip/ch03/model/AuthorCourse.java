package com.manning.sbip.ch03.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AUTHORS_COURSES")
@Table(name = "AUTHORS_COURSES")
public class AuthorCourse {
    @Id
    @Column(name = "author_id")
    private long authorId;
    @Column(name = "course_id")
    private long courseId;

    public AuthorCourse() {
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "AuthorCourse{" +
                "authorId=" + authorId +
                ", courseId=" + courseId +
                '}';
    }
}
