package dk.fitfit.oneliner.service;

import dk.fitfit.oneliner.domain.OneLiner;
import dk.fitfit.oneliner.repository.OneLinerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OneLinerServiceTest {
	private OneLinerServiceInterface oneLinerService;
	@Mock
	private OneLinerRepository oneLinerRepository;

	@Mock
	private OneLiner oneLiner1;
	@Mock
	private OneLiner oneLiner2;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		oneLinerService = new OneLinerService(oneLinerRepository);
	}

	@Test
	public void findByAllTags() throws Exception {
		Set<String> tagNames = Sets.newSet("db", "sql");
		Set<OneLiner> expected = Sets.newSet(oneLiner2, oneLiner2);

		when(oneLinerRepository.findByAllTags(tagNames, 2)).thenReturn(expected);

		Set<OneLiner> actual = oneLinerService.findByAllTags(tagNames);

		assertSame(expected, actual);
		verify(oneLinerRepository).findByAllTags(tagNames, 2);
	}

	@Test
	public void findAll() throws Exception {
		List<OneLiner> expected = Arrays.asList(oneLiner1, oneLiner2);

		when(oneLinerRepository.findAll()).thenReturn(expected);

		List<OneLiner> actual = oneLinerService.findAll();

		assertSame(expected, actual);
		verify(oneLinerRepository).findAll();
	}

	@Test
	public void save() throws Exception {
		long id = 1L;
		when(oneLiner1.getId()).thenReturn(id);
		when(oneLinerRepository.save(any(OneLiner.class))).thenReturn(oneLiner1);

		OneLiner actual = oneLinerService.save(oneLiner1);

		assertSame(id, actual.getId());
		verify(oneLinerRepository).save(oneLiner1);
	}

	@Test
	public void findOne() throws Exception {
		long id = 1;
		when(oneLiner1.getId()).thenReturn(id);
		when(oneLinerRepository.findOne(anyLong())).thenReturn(oneLiner1);

		OneLiner actual = oneLinerService.findOne(id);

		assertSame(id, actual.getId());
		verify(oneLinerRepository).findOne(id);
	}

}
