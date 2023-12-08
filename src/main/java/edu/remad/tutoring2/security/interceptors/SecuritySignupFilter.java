package edu.remad.tutoring2.security.interceptors;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import edu.remad.tutoring2.appconstants.RegexAppConstants;

public class SecuritySignupFilter implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String username = request.getParameter("username");
		String emailAddress = request.getParameter("email");
		String password = request.getParameter("password");

		if (!Pattern.matches(RegexAppConstants.EMAIL_REGEX, emailAddress)
				|| !Pattern.matches(RegexAppConstants.PASSWORD_REGEX, password)
				|| !Pattern.matches(RegexAppConstants.USERNAME_REGEX, username)) {
			throw new Exception("Invalid E-Mail Address, Username or Password. Please try again.");
		}

		return true;
	}
}
