package dk.fitfit.oneliner.repository;

import dk.fitfit.oneliner.domain.OneLiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OneLinerRepository extends JpaRepository<OneLiner, Long> {

// Inspiration: http://www.sergiy.ca/how-to-write-many-to-many-search-queries-in-mysql-and-hibernate/
/* SQL:
SELECT o.*
FROM one_liner o
  INNER JOIN (SELECT ot.one_liner_id
              FROM one_liner_tag ot
                INNER JOIN one_liner o
                  ON o.id = ot.one_liner_id
                INNER JOIN tag t
                  ON t.id = ot.tag_id
              WHERE t.name IN ('db', 'sql')
              GROUP BY ot.one_liner_id
              HAVING Count(ot.one_liner_id) = 2) aa
    ON o.id = aa.one_liner_id
*/
	@Query("select o from OneLiner o join o.tags t where t.name in (:tags) group by o having count(t) = :tag_count")
	Set<OneLiner> findByAllTags(@Param("tags") Set<String> tags, @Param("tag_count") long tagCount);
}
