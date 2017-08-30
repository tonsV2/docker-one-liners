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
	public Set<Tag> findTagsStartingWith(String name) {
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

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag findOne(long id) {
		return tagRepository.findOne(id);
	}

	@Override
	public long getRank(long id) {
		return tagRepository.getRank(id);
	}
}
