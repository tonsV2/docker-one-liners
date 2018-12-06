package dk.fitfit.oneliner.controller

import dk.fitfit.oneliner.domain.OneLiner
import dk.fitfit.oneliner.domain.Tag
import dk.fitfit.oneliner.service.OneLinerServiceInterface
import org.springframework.web.bind.annotation.*

@RestController
class OneLinerController(private val oneLinerService: OneLinerServiceInterface) {

    // TODO: n+1?
    // TODO: n+1?
    // TODO: n+1?
    // query for each oneliners tags... Probably ok
    @GetMapping("/oneliners")
    fun oneLiners(): List<OneLiner> = oneLinerService.findAll()

    @GetMapping("/oneliners/{id}")
    fun getOneLiner(@PathVariable id: Long): OneLiner {
        return oneLinerService.getOne(id)
    }

    @GetMapping("/oneliners/{id}/tags")
    fun getTags(@PathVariable id: Long): List<Tag> {
        return oneLinerService.getOne(id).tags
    }

    // TODO: Shouldn't be post and shouldn't be @RequestBody... Why not?
    @PostMapping("/findByAllTags")
    fun findByAllTags(@RequestBody tags: Set<String>): Set<OneLiner> {
        return oneLinerService.findByAllTags(tags)
    }
}
