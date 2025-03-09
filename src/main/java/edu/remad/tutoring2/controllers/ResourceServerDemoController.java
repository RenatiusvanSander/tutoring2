package edu.remad.tutoring2.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceServerDemoController {
	
	@PreAuthorize("hasRole('client_user')")
	@GetMapping("/v1/demo")
	public String answerDemo() {
		return "Spring 6 and Keycloak works";
	}
	
	@PreAuthorize("hasRole('client_admin')")
	@GetMapping("/v1/demoAdmin")
	public String answerDemoAdmin() {
		return "Spring 6 and Keycloaks Admin";
	}
}
