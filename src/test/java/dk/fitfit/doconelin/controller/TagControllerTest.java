package dk.fitfit.doconelin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.fitfit.doconelin.DockerOneLinerApplication;
import dk.fitfit.doconelin.domain.Tag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DockerOneLinerApplication.class})
@WebAppConfiguration
public class TagControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void oneLinerController_findByAllTags_ShouldReturnListOfTagsOrderedByRank() throws Exception {
		mockMvc.perform(get("/api/tagsByRank"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(13))
				.andExpect(jsonPath("$.[0].name").value("db"))
				// TODO: Why does order differs from: [tons@localhost docker-one-liner]$ http localhost:8081/api/tagsByRank
				.andExpect(jsonPath("$.[12].name").value("javascript"));
//				.andDo(print());
	}

	@Test
	public void oneLinerController_findTagsStartingWith_ShouldReturnListOfTagsStartingWithTheArgument() throws Exception {
		String argument = "java";

		mockMvc.perform(get("/api/tagsStartingWith?name=" + argument))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(2))
				.andExpect(jsonPath("$.[0].name").value("java"))
				.andExpect(jsonPath("$.[1].name").value("javascript"));
	}

	@Test
	public void oneLinerController_postTags_ShouldReturnPostedTagWithId() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Tag tag = new Tag("name", "description");

		MockHttpServletRequestBuilder postRequest = post("/api/tags")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(tag));

		mockMvc.perform(postRequest)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.name").value("name"))
				.andExpect(jsonPath("$.description").value("description"))
				.andDo(print());
	}

}
