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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

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
        mockMvc!!.perform(get("/tagsByRank"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(17))
                .andExpect(jsonPath("$.[0].name").value("alias"))
                // TODO: Why does order differs from: [tons@localhost docker-one-liner]$ http localhost:8081/tagsByRank
                .andExpect(jsonPath("$.[12].name").value("NoSql"))
                .andExpect(jsonPath(".rank").value(Lists.newArrayList(14, 13, 8, 6, 5, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1)))
    }

    @Test
    @Throws(Exception::class)
    fun findTagsStartingWith_ShouldReturnListOfTagsStartingWithTheArgument() {
        val argument = "java"

        mockMvc!!.perform(get("/tagsStartingWith?name=" + argument))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(2))
                .andExpect(jsonPath("$.[0].name").value("java"))
                .andExpect(jsonPath("$.[1].name").value("javascript"))
    }

    @Test
    @Throws(Exception::class)
    fun findTagsStartingWithPath_ShouldReturnListOfTagsStartingWithTheArgument() {
        val argument = "java"

        mockMvc!!.perform(get("/tagsStartingWith/" + argument))
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

        val postRequest = post("/tags")
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
        mockMvc!!.perform(get("/tags"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(17))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("mvn"))
                .andExpect(jsonPath("$.[0].description").value("Java build tool"))
    }

    @Test
    @Throws(Exception::class)
    fun getTag_ShouldReturnATag() {
        val id: Long = 13

        mockMvc!!.perform(get("/tags/" + id))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("length($)").value(6))
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value(""))
                .andExpect(jsonPath("$.description").value(""))
    }

    @Test
    @Throws(Exception::class)
    fun getTagRank_ShouldReturnTheTagsRank() {
        val id: Long = 10

        mockMvc!!.perform(get("/tags/$id/rank"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$").value(2))
    }
}
