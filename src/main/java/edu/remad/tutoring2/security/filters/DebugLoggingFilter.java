package edu.remad.tutoring2.security.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public class DebugLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		System.out.println(request);
//		
//		ServletServerHttpRequest request2 = new ServletServerHttpRequest(request);
//		HttpHeaders header = request2.getHeaders();
//		System.out.println(header);
//		
//		Object tutoring2 = request.getAttribute("tutoring2");

		filterChain.doFilter(request, response);
	}
}
