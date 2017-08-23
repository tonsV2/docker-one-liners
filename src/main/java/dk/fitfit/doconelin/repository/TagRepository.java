package dk.fitfit.doconelin.repository;

import dk.fitfit.doconelin.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	Tag findByName(String db);
}
