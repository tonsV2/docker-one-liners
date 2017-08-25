package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.OneLiner;
import dk.fitfit.doconelin.domain.Tag;

import java.util.List;

public interface OneLinerServiceInterface {
	List<OneLiner> findByAllTags(List<String> tags);

	List<OneLiner> findAll();

	OneLiner save(OneLiner oneLiner);

	Tag save(Tag tag);
}
