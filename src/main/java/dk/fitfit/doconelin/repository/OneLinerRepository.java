package dk.fitfit.doconelin.repository;

import dk.fitfit.doconelin.domain.OneLiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneLinerRepository extends JpaRepository<OneLiner, Long> {
	@Query("select distinct o from OneLiner o inner join o.tags tag where tag.name in :tags")
	List<OneLiner> findByTagsName(@Param("tags") List<String> tags);
}
