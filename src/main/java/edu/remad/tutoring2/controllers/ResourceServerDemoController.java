package edu.remad.tutoring2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceServerDemoController {
	
	@GetMapping("/v1/demo")
	public String answerDemo() {
		return "Spring 6 and Keycloak works";
	}
	
	@GetMapping("/v1/demoAdmin")
	public String answerDemoAdmin() {
		return "Spring 6 and Keycloaks Admin";
	}
}
