package edu.remad.tutoring2.security.filters;

import java.io.IOException;
import java.time.Duration;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalUnit;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.server.Cookie;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public class HttpHeadersFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		Cookie tutoring2Cookie = new Cookie();
//		tutoring2Cookie.setName("tutoring2");
//		tutoring2Cookie.setMaxAge(Duration.ofDays(30l));
//		tutoring2Cookie.setSameSite(SameSite.NONE);
//		tutoring2Cookie.setPath("/");
//
//		ServletServerHttpRequest request2 = new ServletServerHttpRequest(request);
//		HttpHeaders header = request2.getHeaders();
//		List<String> cookies = header.get("cookie");
//		
//		if(cookies != null && !cookies.isEmpty()) {
//			cookies.add(tutoring2Cookie.toString());
//			request2.getHeaders().put("cookie", cookies);
//		}
//		request.setAttribute("tutoring2", tutoring2Cookie);
//		
//		System.out.println(header);

		filterChain.doFilter(request, response);
	}
}
