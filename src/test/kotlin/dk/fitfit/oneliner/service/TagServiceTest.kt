package dk.fitfit.oneliner.service

import dk.fitfit.oneliner.domain.Tag
import dk.fitfit.oneliner.repository.TagRepository
import org.assertj.core.util.Lists
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.util.collections.Sets
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import java.util.ArrayList

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@RunWith(SpringJUnit4ClassRunner::class)
class TagServiceTest {
    private var tagService: TagServiceInterface? = null
    @Mock
    private lateinit var tagRepository: TagRepository
    @Mock
    private val tag1: Tag = Tag()
    @Mock
    private val tag2: Tag = Tag()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tagService = TagService(tagRepository)
    }

    @Test
    @Throws(Exception::class)
    fun findTagsStartingWith() {
        val name = "s"
        val expected = Sets.newSet<Tag>(tag1, tag2)

        `when`(tagRepository.findTagsByNameIgnoreCaseStartingWith(name)).thenReturn(expected)

        val actual = tagService!!.findTagsStartingWith(name)

        assertSame(expected, actual)
        verify(tagRepository).findTagsByNameIgnoreCaseStartingWith(name)
    }

    @Test
    @Throws(Exception::class)
    fun findTagsByRank() {
        `when`(tag1.rank).thenReturn(1L)
        `when`(tag2.rank).thenReturn(2L)
        val expected = Sets.newSet(tag1, tag2)
        `when`(tagRepository.findTagsByRank()).thenReturn(expected)

        val actual = ArrayList(tagService!!.findTagsByRank())

        assertEquals(1, actual[0].rank)
        assertEquals(2, actual[1].rank)
        verify(tagRepository).findTagsByRank()
    }

    @Test
    fun save() {
        `when`(tagRepository.save(tag1!!)).thenReturn(tag1)

        val actual = tagService!!.save(tag1)

        assertSame(tag1, actual)
        verify(tagRepository).save(tag1)
    }

    @Test
    @Throws(Exception::class)
    fun findAll() {
        val expected = Lists.newArrayList<Tag>(tag1, tag2)

        `when`(tagRepository.findAll()).thenReturn(expected)

        val actual = tagService!!.findAll()

        assertSame(expected, actual)
        verify(tagRepository).findAll()
    }

    @Test
    @Throws(Exception::class)
    fun findOne() {
        val tagId = 1L
        `when`(tagRepository.getOne(tagId)).thenReturn(tag1)

        val actual = tagService!!.getOne(1L)

        assertSame(tag1, actual)
        verify(tagRepository).getOne(tagId)
    }

    @Test
    @Throws(Exception::class)
    fun getRank() {
        val tagId = 1L
        val rank = 2L
        `when`(tagRepository.getRank(tagId)).thenReturn(rank)

        val actual = tagService!!.getRank(tagId)

        assertSame(rank, actual)
        verify(tagRepository).getRank(tagId)
    }

}
