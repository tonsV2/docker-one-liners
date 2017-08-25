package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements TagServiceInterface {
	private final TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public List<Tag> search(String name) {
		return tagRepository.findTagsByNameIgnoreCaseContaining(name);
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}
}
