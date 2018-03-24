package dk.fitfit.oneliner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OneLinerControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void findByAllTags_ShouldReturnOneLinersMatchingTags() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String tags = mapper.writeValueAsString(Lists.newArrayList("db", "sql"));

		RequestBuilder postRequest = post("/api/findByAllTags")
				.contentType(MediaType.APPLICATION_JSON)
				.content(tags);

		mockMvc.perform(postRequest)
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(2))
				.andExpect(jsonPath("$.[0].line").value("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest"))
				.andExpect(jsonPath("$.[1].line").value("docker run --name postgresql -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6.2-alpine"))
				.andDo(print());
	}

	@Test
	public void findAll_ShouldReturnListOfOneLiners() throws Exception {
		mockMvc.perform(get("/api/oneliners"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(8))
				.andExpect(jsonPath("$.[0].line").value("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest"))
				.andExpect(jsonPath("$.[1].line").value("docker run --name mongodb -p 27017:27017 -p 28017:28017 -dt mongo:latest"))
				.andExpect(jsonPath("$.[2].line").value("docker run --name postgresql -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6.2-alpine"));
	}

	@Ignore // Id sequence isn't reset... So id = 1 possible isn't there. Find better way to test...
	@Test
	public void findOne_ShouldReturnAOneLiner() throws Exception {
		int id = 1;

		mockMvc.perform(get("/api/oneliners/" + id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(4))
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.line").value("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest"))
				.andExpect(jsonPath("$.description").value("User: root\nSource: https://hub.docker.com/_/mysql/\nCommand: mysql --auto-rehash -u root -h 192.168.0.3 -p"))

				.andExpect(jsonPath("length($.tags)").value(4))
				.andExpect(jsonPath("$.tags[0].id").value(1))
				.andExpect(jsonPath("$.tags[0].name").value("db"))
				.andExpect(jsonPath("$.tags[0].description").value("Database related"));
	}

	@Ignore // Id sequence isn't reset... So id = 1 possible isn't there. Find better way to test...
	@Test
	public void getTags_ShouldReturnAListOfTags() throws Exception {
		int id = 1;

		mockMvc.perform(get("/api/oneliners/" + id + "/tags"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("length($)").value(4))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("db"))
				.andExpect(jsonPath("$[0].description").value("Database related"));
	}

}
