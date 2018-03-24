package dk.fitfit.oneliner.controller;

import dk.fitfit.oneliner.domain.OneLiner;
import dk.fitfit.oneliner.domain.Tag;
import dk.fitfit.oneliner.service.OneLinerServiceInterface;
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
	// query for each oneliners tags... Probably ok
	@GetMapping("/oneliners")
	public List<OneLiner> getOneLiners() {
		return oneLinerService.findAll();
	}

	@GetMapping("/oneliners/{id}")
	public OneLiner getOneLiner(@PathVariable long id) {
		return oneLinerService.getOne(id);
	}

	@GetMapping("/oneliners/{id}/tags")
	public List<Tag> getTags(@PathVariable long id) {
		return oneLinerService.getOne(id).getTags();
	}

	// TODO: Shouldn't be post and shouldn't be @RequestBody
	@PostMapping("/findByAllTags")
	public Set<OneLiner> findByAllTags(@RequestBody Set<String> tags) {
		return oneLinerService.findByAllTags(tags);
	}
}
