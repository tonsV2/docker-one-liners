package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.Tag;

import java.util.List;

public interface TagServiceInterface {
	List<Tag> search(String name);

	List<Tag> findAll();
}
