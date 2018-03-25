package dk.fitfit.oneliner.controller

import com.fasterxml.jackson.databind.ObjectMapper
import dk.fitfit.oneliner.domain.Tag
import org.assertj.core.util.Lists
import org.junit.Before
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
class TagControllerTest {
    private var mockMvc: MockMvc? = null

    @Autowired
    private val wac: WebApplicationContext? = null

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac!!).build()
    }

    @Test
    @Throws(Exception::class)
    fun findByAllTags_ShouldReturnListOfTagsOrderedByRank() {
        mockMvc!!.perform(get("/api/tagsByRank"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(18))
                .andExpect(jsonPath("$.[0].name").value("alias"))
                // TODO: Why does order differs from: [tons@localhost docker-one-liner]$ http localhost:8081/api/tagsByRank
                .andExpect(jsonPath("$.[12].name").value("javascript"))
                .andExpect(jsonPath(".rank").value(Lists.newArrayList(5, 4, 4, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0)))
    }

    @Test
    @Throws(Exception::class)
    fun findTagsStartingWith_ShouldReturnListOfTagsStartingWithTheArgument() {
        val argument = "java"

        mockMvc!!.perform(get("/api/tagsStartingWith?name=" + argument))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(2))
                .andExpect(jsonPath("$.[0].name").value("java"))
                .andExpect(jsonPath("$.[1].name").value("javascript"))
    }

    @Test
    @Throws(Exception::class)
    fun findTagsStartingWithPath_ShouldReturnListOfTagsStartingWithTheArgument() {
        val argument = "java"

        mockMvc!!.perform(get("/api/tagsStartingWith/" + argument))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(2))
                .andExpect(jsonPath("$.[0].name").value("java"))
                .andExpect(jsonPath("$.[1].name").value("javascript"))
    }

    @Test
    @Throws(Exception::class)
    fun postTags_ShouldReturnPostedTagWithId() {
        val mapper = ObjectMapper()
        val tag = Tag("name", "description")

        val postRequest = post("/api/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(tag))

        mockMvc!!.perform(postRequest)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id").isNumber)
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.description").value("description"))
                .andDo(print())
    }

    @Test
    @Throws(Exception::class)
    fun getTags_ShouldReturnListOfTags() {
        mockMvc!!.perform(get("/api/tags"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(18))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("mvn"))
                .andExpect(jsonPath("$.[0].description").value("Java build tool"))
    }

    @Test
    @Throws(Exception::class)
    fun getTag_ShouldReturnATag() {
        val id: Long = 10

        mockMvc!!.perform(get("/api/tags/" + id))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(4))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.name").value("NoSql"))
                .andExpect(jsonPath("$.description").value("NoSql database..."))
    }

    @Test
    @Throws(Exception::class)
    fun getTagRank_ShouldReturnTheTagsRank() {
        val id: Long = 10

        mockMvc!!.perform(get("/api/tags/$id/rank"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$").value(1))
    }
}
