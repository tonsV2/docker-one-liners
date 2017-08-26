package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TagService implements TagServiceInterface {
	private final TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public List<Tag> findTagsStartingWith(String name) {
//		return tagRepository.findTagsByNameIgnoreCaseContaining(name);
		return tagRepository.findTagsByNameIgnoreCaseStartingWith(name);
	}

	@Override
	public Set<Tag> findTagsByRank() {
		return tagRepository.findTagsByRank();
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}
}
