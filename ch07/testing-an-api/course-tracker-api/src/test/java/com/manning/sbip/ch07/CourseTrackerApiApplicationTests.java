package com.manning.sbip.ch07;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.service.CourseService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CourseTrackerApiApplicationTests {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testPostCourse() throws Exception {
        Course course = Course.builder()
        		.name("Rapid Spring Boot Application Development")
        		.category("Spring")
        		.rating(5)
        		.description("Rapid Spring Boot Application Development").build();
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/courses/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(course)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Rapid Spring Boot Application Development"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(status().isCreated()).andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        assertNotNull(courseService.getCourseById(id));

    }
	
	@Test
    public void testRetrieveCourse() throws Exception {
		 Course course = Course.builder()
	        		.name("Rapid Spring Boot Application Development")
	        		.category("Spring")
	        		.rating(5)
	        		.description("Rapid Spring Boot Application Development").build();
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/courses/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(course)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Rapid Spring Boot Application Development"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(status().isCreated()).andReturn().getResponse();
        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");

        mockMvc.perform(get("/courses/{id}",id))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Rapid Spring Boot Application Development"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(status().isOk());

    }
	
	@Test
	public void testInvalidCouseId() throws Exception {
		mockMvc.perform(get("/courses/{id}",100))
        .andDo(print())
        .andExpect(status().isNotFound());
	}
	
	@Test
    public void testUpdateCourse() throws Exception {
		 Course course = Course.builder()
	        		.name("Rapid Spring Boot Application Development")
	        		.category("Spring")
	        		.rating(3)
	        		.description("Rapid Spring Boot Application Development").build();
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/courses/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(course)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value("Rapid Spring Boot Application Development"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(3))
                .andExpect(status().isCreated()).andReturn().getResponse();
        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");

        Course updatedCourse = Course.builder()
        		.name("Rapid Spring Boot Application Development")
        		.category("Spring")
        		.rating(5)
        		.description("Rapid Spring Boot Application Development").build();
        
        mockMvc.perform(put("/courses/{id}", id)
        		.contentType("application/json")
        		.content(objectMapper.writeValueAsString(updatedCourse)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Rapid Spring Boot Application Development"))
                .andExpect(jsonPath("$.category").value("Spring"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(status().isOk());

    }
	
	@Test
	public void testDeleteCourse() throws Exception {
		 Course course = Course.builder()
	        		.name("Rapid Spring Boot Application Development")
	        		.category("Spring")
	        		.rating(5)
	        		.description("Rapid Spring Boot Application Development").build();
       ObjectMapper objectMapper = new ObjectMapper();

       MockHttpServletResponse response = mockMvc.perform(post("/courses/")
               .contentType("application/json")
               .content(objectMapper.writeValueAsString(course)))
               .andDo(print())
               .andExpect(jsonPath("$.*", hasSize(5)))
               .andExpect(jsonPath("$.id", greaterThan(0)))
               .andExpect(jsonPath("$.name").value("Rapid Spring Boot Application Development"))
               .andExpect(jsonPath("$.category").value("Spring"))
               .andExpect(jsonPath("$.rating").value(5))
               .andExpect(status().isCreated()).andReturn().getResponse();
       Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");

       mockMvc.perform(delete("/courses/{id}", id))
               .andDo(print())
               .andExpect(status().isOk());

   }
	    

}
