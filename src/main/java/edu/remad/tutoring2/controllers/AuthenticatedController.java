package edu.remad.tutoring2.controllers;

import edu.remad.tutoring2.globalexceptions.HttpStatus401Exception;
import edu.remad.tutoring2.security.interfaces.IAuthenticationFacade;

public abstract class AuthenticatedController {
	
	protected final IAuthenticationFacade authenticationFacade;
	
	public AuthenticatedController(IAuthenticationFacade authenticationFacade) {
		this.authenticationFacade = authenticationFacade;
	}
	
	protected void checkAuthenticated() {
		if(!authenticationFacade.isAuthentoicated()) {
			throw new HttpStatus401Exception(null, null, null);
		}
	}

}
