package dk.fitfit.oneliner.repository;

import dk.fitfit.oneliner.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
//	List<Tag> findTagsByNameIgnoreCaseContaining(String name);
	Set<Tag> findTagsByNameIgnoreCaseStartingWith(String name);

/**
SQL: SELECT tag.*, COUNT(tag_id) AS rank FROM tag LEFT JOIN one_liner_tag ON tag.id = one_liner_tag.tag_id GROUP BY tag.id ORDER BY rank
*/
//	@Query("select t, count(tag_id) as rank from Tag t left join t.oneLiners group by t.id order by rank")
//	@Query("select new map(t.id as id, t.name as name, t.description as description, count(tag_id) as rank) from Tag t left join t.oneLiners group by t.id order by rank desc")
// Inspiration: http://winterbe.com/posts/2009/08/14/query-several-columns-with-hibernate/
	@Query("select new Tag(t.id, t.name, t.description, count(tag_id) as rank) from Tag t left join t.oneLiners group by t.id order by rank desc")
	Set<Tag> findTagsByRank();

	@Query("select size(t.oneLiners) from Tag t where t.id = :id")
	long getRank(@Param("id") long id);
}
