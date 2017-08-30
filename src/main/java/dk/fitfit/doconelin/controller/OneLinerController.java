package dk.fitfit.doconelin.controller;

import dk.fitfit.doconelin.domain.OneLiner;
import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.service.OneLinerServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class OneLinerController {
	private final OneLinerServiceInterface oneLinerService;

	public OneLinerController(OneLinerServiceInterface oneLinerService) {
		this.oneLinerService = oneLinerService;
	}

	// TODO: n+1?
	// TODO: n+1?
	// TODO: n+1?
	@GetMapping("/oneliners")
	public List<OneLiner> getOneLiners() {
		return oneLinerService.findAll();
	}

	@GetMapping("/oneliners/{id}")
	public OneLiner getOneLiner(@PathVariable long id) {
		return oneLinerService.findOne(id);
	}

	@GetMapping("/oneliners/{id}/tags")
	public List<Tag> getTags(@PathVariable long id) {
		return oneLinerService.findOne(id).getTags();
	}

	// TODO: Shouldn't be post and shouldn't be @RequestBody
	@PostMapping("/findByAllTags")
	public Set<OneLiner> findByAllTags(@RequestBody Set<String> tags) {
		return oneLinerService.findByAllTags(tags);
	}
}
