package dk.fitfit.doconelin.repository;

import dk.fitfit.doconelin.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	List<Tag> findTagsByNameIgnoreCaseContaining(String name);

//	@Query("select t from Tag t order by count()")
//	List<Tag> findTagsByPopularity();
}
