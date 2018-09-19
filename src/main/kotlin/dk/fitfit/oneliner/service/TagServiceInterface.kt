package dk.fitfit.oneliner.service

import dk.fitfit.oneliner.domain.Tag

interface TagServiceInterface {
    fun findTagsStartingWith(name: String): Set<Tag>

    fun findTagsByRank(): Set<Tag>

    fun save(tag: Tag): Tag

    fun findAll(): List<Tag>

    fun getOne(id: Long): Tag

    fun getRank(id: Long): Long

    fun fuzzySearchDescription(searchString: String): MutableList<*>
}
