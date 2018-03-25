package dk.fitfit.oneliner.service

import dk.fitfit.oneliner.domain.Tag
import dk.fitfit.oneliner.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService(private val tagRepository: TagRepository) : TagServiceInterface {

    override fun findTagsStartingWith(name: String): Set<Tag> {
        return tagRepository.findTagsByNameIgnoreCaseStartingWith(name)
    }

/*
    override fun findTagsByRank(): Set<Tag> {
        return tagRepository.findTagsByRank()
    }
*/

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
}
