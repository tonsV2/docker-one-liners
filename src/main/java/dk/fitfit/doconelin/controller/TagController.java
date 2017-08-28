package dk.fitfit.doconelin.controller;

import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.service.TagServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/tagsStartingWith")
	public Set<Tag> findTagsStartingWith(@RequestParam String name) {
		return tagService.findTagsStartingWith(name);
	}

	@GetMapping("/tagsByRank")
	public Set<Tag> getTagsByRank() {
		return tagService.findTagsByRank();
	}
}
