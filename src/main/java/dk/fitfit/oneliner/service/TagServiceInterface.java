package dk.fitfit.oneliner.service;

import dk.fitfit.oneliner.domain.Tag;

import java.util.List;
import java.util.Set;

public interface TagServiceInterface {
	Set<Tag> findTagsStartingWith(String name);

	Set<Tag> findTagsByRank();

	Tag save(Tag tag);

	List<Tag> findAll();

	Tag getOne(long id);

	long getRank(long id);
}
