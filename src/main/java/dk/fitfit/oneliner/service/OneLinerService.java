package dk.fitfit.oneliner.service;

import dk.fitfit.oneliner.domain.OneLiner;
import dk.fitfit.oneliner.repository.OneLinerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OneLinerService implements OneLinerServiceInterface {
	private final OneLinerRepository oneLinerRepository;

	public OneLinerService(OneLinerRepository oneLinerRepository) {
		this.oneLinerRepository = oneLinerRepository;
	}

	@Override
	public Set<OneLiner> findByAllTags(Set<String> tags) {
		return oneLinerRepository.findByAllTags(tags, tags.size());
	}

	@Override
	public List<OneLiner> findAll() {
		return oneLinerRepository.findAll();
	}

	@Override
	public OneLiner save(OneLiner oneLiner) {
		return oneLinerRepository.save(oneLiner);
	}

	@Override
	public OneLiner getOne(long id) {
		return oneLinerRepository.getOne(id);
	}
}
