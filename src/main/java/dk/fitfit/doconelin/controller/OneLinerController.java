package dk.fitfit.doconelin.controller;

import dk.fitfit.doconelin.domain.OneLiner;
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

	@GetMapping("/oneliners")
	public List<OneLiner> getOneLiners() {
		return oneLinerService.findAll();
	}

	// TODO: Shouldn't be post and shouldn't be @RequestBody
	@PostMapping("/findByAllTags")
	public Set<OneLiner> findByAllTags(@RequestBody Set<String> tags) {
		return oneLinerService.findByAllTags(tags);
	}
}
