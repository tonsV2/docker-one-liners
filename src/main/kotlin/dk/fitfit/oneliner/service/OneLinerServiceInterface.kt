package dk.fitfit.oneliner.service

import dk.fitfit.oneliner.domain.OneLiner

interface OneLinerServiceInterface {
    fun findByAllTags(tags: Set<String>): Set<OneLiner>

    fun findAll(): List<OneLiner>

    fun save(oneLiner: OneLiner): OneLiner

    fun getOne(id: Long): OneLiner
}
