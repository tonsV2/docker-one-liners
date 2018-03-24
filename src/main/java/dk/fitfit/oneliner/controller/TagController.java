package dk.fitfit.oneliner.controller;

import dk.fitfit.oneliner.domain.Tag;
import dk.fitfit.oneliner.service.TagServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TagController {
	private final TagServiceInterface tagService;

	public TagController(TagServiceInterface tagService) {
		this.tagService = tagService;
	}

	@PostMapping("/tags")
	public ResponseEntity<Tag> postTag(@RequestBody Tag tag) {
		return new ResponseEntity<>(tagService.save(tag), HttpStatus.CREATED);
	}

	@GetMapping("/tags")
	public List<Tag> getTags() {
		return tagService.findAll();
	}

	@GetMapping("/tags/{id}")
	public Tag getTag(@PathVariable long id) {
		return tagService.getOne(id);
	}

	@GetMapping("/tags/{id}/rank")
	public long getTagRank(@PathVariable long id) {
		return tagService.getRank(id);
	}

	@GetMapping("/tagsStartingWith")
	public Set<Tag> getTagsStartingWith(@RequestParam String name) {
		return tagService.findTagsStartingWith(name);
	}

	@GetMapping("/tagsStartingWith/{name}")
	public Set<Tag> getTagsStartingWithPath(@PathVariable String name) {
		return tagService.findTagsStartingWith(name);
	}

	@GetMapping("/tagsByRank")
	public Set<Tag> getTagsByRank() {
		return tagService.findTagsByRank();
	}
}
