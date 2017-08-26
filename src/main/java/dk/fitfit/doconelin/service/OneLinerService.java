package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.OneLiner;
import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.repository.OneLinerRepository;
import dk.fitfit.doconelin.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OneLinerService implements OneLinerServiceInterface {
	private final OneLinerRepository oneLinerRepository;
	private final TagRepository tagRepository;

	public OneLinerService(OneLinerRepository oneLinerRepository, TagRepository tagRepository) {
		this.oneLinerRepository = oneLinerRepository;
		this.tagRepository = tagRepository;
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
	public Tag save(Tag tag) {
		return tagRepository.saveAndFlush(tag);
	}
}
