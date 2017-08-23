package dk.fitfit.doconelin.controller;

import dk.fitfit.doconelin.domain.OneLiner;
import dk.fitfit.doconelin.service.OneLinerServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@PostMapping("/oneliners")
	public List<OneLiner> searchByTags(@RequestBody List<String> tags) {
		return oneLinerService.findByTagNames(tags);
	}
}
