package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.Tag;

import java.util.Set;

public interface TagServiceInterface {
	Set<Tag> findTagsStartingWith(String name);

	Set<Tag> findTagsByRank();

	Tag save(Tag tag);
}
