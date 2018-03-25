package dk.fitfit.oneliner.service

import dk.fitfit.oneliner.domain.OneLiner
import dk.fitfit.oneliner.repository.OneLinerRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.util.collections.Sets
import org.mockito.runners.MockitoJUnitRunner

import java.util.Arrays

import org.junit.Assert.assertSame
import org.mockito.Matchers.any
import org.mockito.Matchers.anyLong
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
/*
@RunWith(MockitoJUnitRunner::class)
class OneLinerServiceTest {
    private var oneLinerService: OneLinerServiceInterface? = null
    @Mock
    private val oneLinerRepository: OneLinerRepository? = null

    @Mock
    private val oneLiner1: OneLiner? = null
    @Mock
    private val oneLiner2: OneLiner? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        oneLinerService = OneLinerService(oneLinerRepository)
    }

    @Test
    @Throws(Exception::class)
    fun findByAllTags() {
        val tagNames = Sets.newSet("db", "sql")
        val expected = Sets.newSet<OneLiner>(oneLiner2, oneLiner2)

        `when`(oneLinerRepository!!.findByAllTags(tagNames, 2)).thenReturn(expected)

        val actual = oneLinerService!!.findByAllTags(tagNames)

        assertSame(expected, actual)
        verify(oneLinerRepository).findByAllTags(tagNames, 2)
    }

    @Test
    @Throws(Exception::class)
    fun findAll() {
        val expected = Arrays.asList<OneLiner>(oneLiner1, oneLiner2)

        `when`(oneLinerRepository!!.findAll()).thenReturn(expected)

        val actual = oneLinerService!!.findAll()

        assertSame(expected, actual)
        verify(oneLinerRepository).findAll()
    }

    @Test
    @Throws(Exception::class)
    fun save() {
        val id = 1L
        `when`(oneLiner1!!.id).thenReturn(id)
        `when`(oneLinerRepository!!.save<OneLiner>(ArgumentMatchers.any(OneLiner::class.java!!))).thenReturn(oneLiner1)

        val actual = oneLinerService!!.save(oneLiner1)

        assertSame(id, actual.id)
        verify(oneLinerRepository).save(oneLiner1)
    }

    @Test
    @Throws(Exception::class)
    fun findOne() {
        val id: Long = 1
        `when`(oneLiner1!!.id).thenReturn(id)
        `when`(oneLinerRepository!!.getOne(ArgumentMatchers.anyLong())).thenReturn(oneLiner1)

        val actual = oneLinerService!!.getOne(id)

        assertSame(id, actual.id)
        verify(oneLinerRepository).getOne(id)
    }

}
*/