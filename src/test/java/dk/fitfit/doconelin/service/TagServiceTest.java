package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.repository.TagRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TagServiceTest {
	private TagServiceInterface tagService;

	@Mock
	private TagRepository tagRepository;
	@Mock
	Tag tag1, tag2;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		tagService = new TagService(tagRepository);
	}

	@Test
	public void findTagsStartingWith() throws Exception {
		String name = "s";
		Set<Tag> expected = Sets.newSet(tag1, tag2);

		when(tagRepository.findTagsByNameIgnoreCaseStartingWith(name)).thenReturn(expected);

		Set<Tag> actual = tagService.findTagsStartingWith(name);

		assertSame(expected, actual);
		verify(tagRepository).findTagsByNameIgnoreCaseStartingWith(name);
	}

	@Test
	public void findTagsByRank() throws Exception {
		when(tag1.getRank()).thenReturn(1L);
		when(tag2.getRank()).thenReturn(2L);
		Set<Tag> expected = Sets.newSet(tag1, tag2);
		when(tagRepository.findTagsByRank()).thenReturn(expected);

		List<Tag> actual = new ArrayList<>(tagService.findTagsByRank());

		assertEquals(1, actual.get(0).getRank());
		assertEquals(2, actual.get(1).getRank());
		verify(tagRepository).findTagsByRank();
	}

	@Test
	public void save() {
		when(tagRepository.save(tag1)).thenReturn(tag1);

		Tag actual = tagService.save(tag1);

		assertSame(tag1, actual);
		verify(tagRepository).save(tag1);
	}

	@Test
	public void findAll() throws Exception {
		List<Tag> expected = Lists.newArrayList(tag1, tag2);

		when(tagRepository.findAll()).thenReturn(expected);

		List<Tag> actual = tagService.findAll();

		assertSame(expected, actual);
		verify(tagRepository).findAll();
	}

	@Test
	public void findOne() throws Exception {
		long tagId = 1L;
		when(tagRepository.findOne(tagId)).thenReturn(tag1);

		Tag actual = tagService.findOne(1L);

		assertSame(tag1, actual);
		verify(tagRepository).findOne(tagId);
	}

	@Test
	public void getRank() throws Exception {
		long tagId = 1L;
		long rank = 2L;
		when(tagRepository.getRank(tagId)).thenReturn(rank);

		long actual = tagService.getRank(tagId);

		assertSame(rank, actual);
		verify(tagRepository).getRank(tagId);
	}

}
