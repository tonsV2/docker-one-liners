package dk.fitfit.oneliner.service

import dk.fitfit.oneliner.domain.Tag
import dk.fitfit.oneliner.repository.TagRepository
import org.hibernate.search.jpa.Search
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class TagService(private val entityManager: EntityManager, private val tagRepository: TagRepository) : TagServiceInterface {

    override fun findTagsStartingWith(name: String): Set<Tag> {
        return tagRepository.findTagsByNameIgnoreCaseStartingWith(name)
    }

    override fun findTagsByRank(): Set<Tag> {
        return tagRepository.findTagsByRank()
    }

    override fun save(tag: Tag): Tag {
        return tagRepository.save(tag)
    }

    override fun findAll(): List<Tag> {
        return tagRepository.findAll()
    }

    override fun getOne(id: Long): Tag {
        return tagRepository.getOne(id)
    }

    override fun getRank(id: Long): Long {
        return tagRepository.getRank(id)
    }

    // Inspiration: https://www.baeldung.com/hibernate-search
    override fun fuzzySearchDescription(searchString: String): MutableList<*> {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)

        val queryBuilder = fullTextEntityManager.searchFactory
                .buildQueryBuilder()
                .forEntity(Tag::class.java)
                .get()

/*
        val query = queryBuilder
                .keyword()
                .wildcard()
                .onField("description")
                .matching("*${searchString.toLowerCase()}*")
                .createQuery()
*/
        val query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("description")
                .matching(searchString)
                .createQuery()

        val jpaQuery = fullTextEntityManager.createFullTextQuery(query, Tag::class.java)
        return jpaQuery.resultList as MutableList<*>
    }
}
