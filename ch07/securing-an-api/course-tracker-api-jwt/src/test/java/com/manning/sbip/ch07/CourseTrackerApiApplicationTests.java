package com.manning.sbip.ch07;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CourseTrackerApiApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void givenThereIsNoTokenWhenGetCoursesThenUnauthorized() throws Exception {
		this.mockMvc.perform(get("/courses/"))
		.andExpect(status().isUnauthorized());
	}

	@Test
	public void givenThereIsNoScopeWhenGetCoursesThenForbidden() throws Exception {
		this.mockMvc.perform(get("/courses/")
				.with(jwt()))
				.andExpect(status().isForbidden());
	}

	@Test
	public void givenThereIsWrongScopeWhenGetCoursesThenForbidden() throws Exception {
		this.mockMvc.perform(get("/courses/")
				.with(jwt()
						.jwt(jwt -> {
							jwt.claim("user_name", "john");
							jwt.claim("scope", "course:read");
						})
						.authorities(new SimpleGrantedAuthority("SCOPE_course:write"))))
				.andExpect(status().isForbidden());
	}
	
	@Test
	public void givenThereIsReadScopeWhenGetCoursesThenAuthorized() throws Exception {
		this.mockMvc.perform(get("/courses/")
				.with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_course:read"))))
				.andExpect(status().isOk());
	}

}
