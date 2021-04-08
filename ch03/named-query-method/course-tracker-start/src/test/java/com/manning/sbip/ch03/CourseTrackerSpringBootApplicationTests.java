package com.manning.sbip.ch03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void whenCountAllCoursesThenExpectFiveCourses() {
        List<Map<String, Object>>  results = jdbcTemplate.queryForList("SELECT COUNT(1) FROM COURSES");
        assertThat(results.get(0).get("COUNT(1)")).isEqualTo(5L);
    }
}
