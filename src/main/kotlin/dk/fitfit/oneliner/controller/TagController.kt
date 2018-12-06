package dk.fitfit.oneliner.controller

import dk.fitfit.oneliner.controller.validator.TagValidator
import dk.fitfit.oneliner.domain.Tag
import dk.fitfit.oneliner.service.TagServiceInterface
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class TagController(private val tagService: TagServiceInterface) {
    @InitBinder("tag")
    fun tagInitBinder(dataBinder: WebDataBinder) {
        dataBinder.validator = TagValidator()
    }

    val tags: List<Tag>
        @GetMapping("/tags")
        get() = tagService.findAll()

    val tagsByRank: Set<Tag>
        @GetMapping("/tagsByRank")
        get() = tagService.findTagsByRank()

    @PostMapping("/tags")
    fun postTag(@Valid @RequestBody tag: Tag): ResponseEntity<Tag> {
        return ResponseEntity(tagService.save(tag), HttpStatus.CREATED)
    }

    @GetMapping("/tags/{id}")
    fun getTag(@PathVariable id: Long): Tag {
        return tagService.getOne(id)
    }

    @GetMapping("/tags/{id}/rank")
    fun getTagRank(@PathVariable id: Long): Long {
        return tagService.getRank(id)
    }

    @GetMapping("/tagsStartingWith")
    fun getTagsStartingWith(@RequestParam name: String): Set<Tag> {
        return tagService.findTagsStartingWith(name)
    }

    @GetMapping("/tagsStartingWith/{name}")
    fun getTagsStartingWithPath(@PathVariable name: String): Set<Tag> {
        return tagService.findTagsStartingWith(name)
    }
}
