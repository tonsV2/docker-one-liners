package dk.fitfit.doconelin.repository;

import dk.fitfit.doconelin.domain.OneLiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneLinerRepository extends JpaRepository<OneLiner, Long> {
	@Query("select o from OneLiner o join o.tags t where t.name in (:tags) group by o having count(t) = :tag_count")
	List<OneLiner> findByAllTags(@Param("tags") List<String> tags, @Param("tag_count") long tagCount);
}
