package dk.fitfit.oneliner.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.util.Lists
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class OneLinerControllerTest {
    private var mockMvc: MockMvc? = null

    @Autowired
    private val wac: WebApplicationContext? = null

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac!!).build()
    }

    @Test
    @Throws(Exception::class)
    fun findByAllTags_ShouldReturnOneLinersMatchingTags() {
        val mapper = ObjectMapper()
        val tags = mapper.writeValueAsString(Lists.newArrayList("db", "sql"))

        val postRequest = post("/findByAllTags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tags)

        mockMvc!!.perform(postRequest)
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(2))
                .andExpect(jsonPath("$.[0].line").value("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest"))
                .andExpect(jsonPath("$.[1].line").value("docker run --name postgresql -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6.2-alpine"))
                .andDo(print())
    }

    @Test
    @Throws(Exception::class)
    fun findAll_ShouldReturnListOfOneLiners() {
        mockMvc!!.perform(get("/oneliners"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(17))
                .andExpect(jsonPath("$.[0].line").value("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest"))
                .andExpect(jsonPath("$.[1].line").value("docker run --name mongodb -p 27017:27017 -p 28017:28017 -dt mongo:latest"))
                .andExpect(jsonPath("$.[2].line").value("docker run --name postgresql -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6.2-alpine"))
    }

    @Ignore // Id sequence isn't reset... So id = 1 possible isn't there. Find better way to test...
    @Test
    @Throws(Exception::class)
    fun findOne_ShouldReturnAOneLiner() {
        val id = 1

        mockMvc!!.perform(get("/oneliners/" + id))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(4))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.line").value("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest"))
                .andExpect(jsonPath("$.description").value("User: root\nSource: https://hub.docker.com/_/mysql/\nCommand: mysql --auto-rehash -u root -h 192.168.0.3 -p"))

                .andExpect(jsonPath("length($.tags)").value(4))
                .andExpect(jsonPath("$.tags[0].id").value(1))
                .andExpect(jsonPath("$.tags[0].name").value("db"))
                .andExpect(jsonPath("$.tags[0].description").value("Database related"))
    }

    @Ignore // Id sequence isn't reset... So id = 1 possible isn't there. Find better way to test...
    @Test
    @Throws(Exception::class)
    fun getTags_ShouldReturnAListOfTags() {
        val id = 1

        mockMvc!!.perform(get("/oneliners/$id/tags"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(4))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("db"))
                .andExpect(jsonPath("$[0].description").value("Database related"))
    }

}
