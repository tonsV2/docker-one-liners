package dk.fitfit.doconelin.controller;

import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.service.TagServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {
	private final TagServiceInterface tagService;

	public TagController(TagServiceInterface tagService) {
		this.tagService = tagService;
	}

	@PostMapping("/search/findTagsByName")
	public List<Tag> findByAllTags(@RequestParam String name) {
		return tagService.search(name);
	}

	@GetMapping("/tags")
	public List<Tag> getTags() {
		return tagService.findAll();
	}
}
