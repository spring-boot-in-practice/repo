package com.manning.sbip.ch07;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.service.CourseService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
class CourseTrackerApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;

	@Test
	public void testPostCourse() throws Exception {
		Course course = new Course(1L, "Rapid Spring Boot Application Development", "Spring", 5,
				"Rapid Spring Boot Application Development");
		when(courseService.createCourse(any(Course.class))).thenReturn(course);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(post("/courses/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(course))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Rapid Spring Boot Application Development")))
				.andExpect(jsonPath("$.category", is("Spring"))).andExpect(jsonPath("$.rating", is(5)))
				.andExpect(jsonPath("$.description", is("Rapid Spring Boot Application Development")))
				.andDo(document("/create-course", courseRequest(), courseResponse()));

		ArgumentCaptor<Course> captor = ArgumentCaptor.forClass(Course.class);
		verify(courseService).createCourse(captor.capture());
		assertThat(captor.getValue().getId()).isEqualTo(1);
		assertThat(captor.getValue().getName()).isEqualTo("Rapid Spring Boot Application Development");
		assertThat(captor.getValue().getCategory()).isEqualTo("Spring");
		assertThat(captor.getValue().getRating()).isEqualTo(5);
		assertThat(captor.getValue().getDescription()).isEqualTo("Rapid Spring Boot Application Development");
	}

	private RequestFieldsSnippet courseRequest() {
		ConstraintDescriptions constraintDescriptions = new ConstraintDescriptions(Course.class);
		return requestFields(
				fieldWithPath("id").description("The unique id of the course").optional()
						.attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("id"))),
				fieldWithPath("name").description("The name of the course").optional()
						.attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("name"))),
				fieldWithPath("rating").description("The rating of the course")
						.attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("rating"))),
				fieldWithPath("category").description("The category of the course").attributes(
						key("constraints").value(constraintDescriptions.descriptionsForProperty("category"))),
				fieldWithPath("description").description("The course description").attributes(
						key("constraints").value(constraintDescriptions.descriptionsForProperty("description"))));
	}
	
	private ResponseFieldsSnippet courseResponse() {
		return responseFields(fieldWithPath("id").description("The unique identifier of the course"),
				fieldWithPath("name").description("The name of the course"),
				fieldWithPath("rating").description("The rating of the course"),
				fieldWithPath("category").description("The category of the course"),
				fieldWithPath("description").description("The description of the course"));
	}

}
