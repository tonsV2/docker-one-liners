package dk.fitfit.oneliner.service;

import dk.fitfit.oneliner.domain.OneLiner;

import java.util.List;
import java.util.Set;

public interface OneLinerServiceInterface {
	Set<OneLiner> findByAllTags(Set<String> tags);

	List<OneLiner> findAll();

	OneLiner save(OneLiner oneLiner);

	OneLiner findOne(long id);
}
