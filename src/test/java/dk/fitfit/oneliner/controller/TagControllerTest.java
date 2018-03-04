package dk.fitfit.oneliner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.fitfit.oneliner.domain.Tag;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
@SpringBootTest
public class TagControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void findByAllTags_ShouldReturnListOfTagsOrderedByRank() throws Exception {
		mockMvc.perform(get("/api/tagsByRank"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(15))
				.andExpect(jsonPath("$.[0].name").value("db"))
				// TODO: Why does order differs from: [tons@localhost docker-one-liner]$ http localhost:8081/api/tagsByRank
				.andExpect(jsonPath("$.[12].name").value("apache"))
				.andExpect(jsonPath(".rank").value(Lists.newArrayList(3, 2, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0)));
	}

	@Test
	public void findTagsStartingWith_ShouldReturnListOfTagsStartingWithTheArgument() throws Exception {
		String argument = "java";

		mockMvc.perform(get("/api/tagsStartingWith?name=" + argument))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(2))
				.andExpect(jsonPath("$.[0].name").value("java"))
				.andExpect(jsonPath("$.[1].name").value("javascript"));
	}

	@Test
	public void postTags_ShouldReturnPostedTagWithId() throws Exception {
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

	@Test
	public void getTags_ShouldReturnListOfTags() throws Exception {
		mockMvc.perform(get("/api/tags"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(15))
				.andExpect(jsonPath("$.[0].id").value(10))
				.andExpect(jsonPath("$.[0].name").value("spring"))
				.andExpect(jsonPath("$.[0].description").value("Java framework"));
	}

	@Test
	public void getTag_ShouldReturnATag() throws Exception {
		long id = 10;

		mockMvc.perform(get("/api/tags/" + id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(4))
				.andExpect(jsonPath("$.id").value(10))
				.andExpect(jsonPath("$.name").value("spring"))
				.andExpect(jsonPath("$.description").value("Java framework"));
	}
	@Test
	public void getTagRank_ShouldReturnTheTagsRank() throws Exception {
		long id = 10;

		mockMvc.perform(get("/api/tags/" + id + "/rank"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(0));
	}
}
