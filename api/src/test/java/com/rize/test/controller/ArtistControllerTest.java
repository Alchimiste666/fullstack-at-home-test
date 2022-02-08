package com.rize.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rize.test.model.Category;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.JsonPathResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getArtistsTest() throws Exception {
		mockMvc
			.perform(get("/artists")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))));
	}
	
	@Test
	public void getArtistByIdTest() throws Exception {
		mockMvc
			.perform(get("/artists/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstName", is("Dwayne")))
			.andExpect(jsonPath("$.middleName", is("The Rock")))
			.andExpect(jsonPath("$.lastName", is("Johnson")))
			.andExpect(jsonPath("$.category", is(Category.ACTOR.toString())))
			.andExpect(jsonPath("$.birthday", is("1980-01-01T08:00:00.000+00:00")))
			.andExpect(jsonPath("$.email", is("dwayne.johnson@hollywood.org")))
			.andExpect(jsonPath("$.notes", nullValue()))
			.andExpect(jsonPath("$.fullName", is("dwayne johnson")))
			.andExpect(jsonPath("$.birthdayMonth", is("january")));
	}
	
	@Test
	public void createArtistTest() throws Exception {
		String json = "{\r\n"
			+ "	\"firstName\": \"Younes\",\r\n"
			+ "	\"middleName\": null,\r\n"
			+ "	\"lastName\": \"puppet\",\r\n"
			+ "	\"category\": \"PAINTER\",\r\n"
			+ "	\"birthday\": \"1990-01-01T08:00:00.000+00:00\",\r\n"
			+ "	\"email\": \"diego.puppet@test.org\",\r\n"
			+ "	\"notes\": null\r\n"
			+ "}";
		
		mockMvc
			.perform(
				post("/artists")
					.content(json)
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isCreated());
	}
	
	@Test
	public void delteArtistByIdTest() throws Exception {
		mockMvc
			.perform(delete("/artists/2").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
}
