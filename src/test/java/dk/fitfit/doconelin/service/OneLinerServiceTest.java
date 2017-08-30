package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.OneLiner;
import dk.fitfit.doconelin.repository.OneLinerRepository;
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

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		oneLinerService = new OneLinerService(oneLinerRepository);
	}

	@Test
	public void findByAllTags() throws Exception {
		Set<String> tagNames = Sets.newSet("db", "sql");
		OneLiner oneLiner = new OneLiner(1);
		OneLiner oneLiner2 = new OneLiner(2);
		Set<OneLiner> expected = Sets.newSet(oneLiner, oneLiner2);

		when(oneLinerRepository.findByAllTags(tagNames, 2)).thenReturn(expected);

		Set<OneLiner> actual = oneLinerService.findByAllTags(tagNames);

		assertSame(expected, actual);
		verify(oneLinerRepository).findByAllTags(tagNames, 2);
	}

	@Test
	public void findAll() throws Exception {
		OneLiner oneLiner = new OneLiner(1);
		OneLiner oneLiner2 = new OneLiner(2);
		List<OneLiner> expected = Arrays.asList(oneLiner, oneLiner2);

		when(oneLinerRepository.findAll()).thenReturn(expected);

		List<OneLiner> actual = oneLinerService.findAll();

		assertSame(expected, actual);
		verify(oneLinerRepository).findAll();
	}

	@Test
	public void save() throws Exception {
		long id = 1;
		OneLiner oneLiner = new OneLiner(id);

		when(oneLinerRepository.save(any(OneLiner.class))).thenReturn(oneLiner);

		OneLiner actual = oneLinerService.save(oneLiner);

		assertSame(id, actual.getId());
		verify(oneLinerRepository).save(oneLiner);
	}

	@Test
	public void findOne() throws Exception {
		long id = 1;
		OneLiner oneLiner = new OneLiner(id);

		when(oneLinerRepository.findOne(anyLong())).thenReturn(oneLiner);

		OneLiner actual = oneLinerService.findOne(id);

		assertSame(id, actual.getId());
		verify(oneLinerRepository).findOne(id);
	}

}