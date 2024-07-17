package edu.remad.tutoring2.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class SimpleControllerTest extends AbstractControllerTest {
	
	@Test
	public void simpleTest( ) throws Exception {
		mockMvc.perform(null);
		
		assertNotNull(mockMvc);
	}

}
