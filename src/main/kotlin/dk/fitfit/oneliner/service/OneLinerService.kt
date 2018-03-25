package dk.fitfit.oneliner.service

import dk.fitfit.oneliner.domain.OneLiner
import dk.fitfit.oneliner.repository.OneLinerRepository
import org.springframework.stereotype.Service

@Service
class OneLinerService(private val oneLinerRepository: OneLinerRepository) : OneLinerServiceInterface {

    override fun findByAllTags(tags: Set<String>): Set<OneLiner> {
        return oneLinerRepository.findByAllTags(tags, tags.size.toLong())
    }

    override fun findAll(): List<OneLiner> {
        return oneLinerRepository.findAll()
    }

    override fun save(oneLiner: OneLiner): OneLiner {
        return oneLinerRepository.save(oneLiner)
    }

    override fun getOne(id: Long): OneLiner {
        return oneLinerRepository.getOne(id)
    }
}
