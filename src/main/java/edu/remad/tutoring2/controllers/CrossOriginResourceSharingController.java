package edu.remad.tutoring2.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.remad.tutoring2.appconstants.CORSAppConstants;

@RequestMapping("/cors")
public class CrossOriginResourceSharingController {

	@CrossOrigin(origins = CORSAppConstants.CORS_LOCALHOST_PORT_8080)
	@GetMapping("/corsorigin")
	public String getCorsOrigin() {
		return "corsorigin";
	}
	
	@GetMapping("/greeting-javaconfig")
	public String greetingWithJavaconfig(@RequestParam(required = false, defaultValue = "World") String name) {
		return "corsorigin";
	}
}
