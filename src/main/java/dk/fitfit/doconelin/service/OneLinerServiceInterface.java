package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.OneLiner;

import java.util.List;

public interface OneLinerServiceInterface {
	List<OneLiner> findByTagNames(List<String> tags);

	List<OneLiner> findAll();
}
