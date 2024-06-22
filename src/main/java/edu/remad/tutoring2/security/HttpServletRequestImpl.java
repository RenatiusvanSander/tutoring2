package edu.remad.tutoring2.security;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

public class HttpServletRequestImpl extends HttpServletRequestWrapper {
	
	@Nullable
	private HttpHeaders headers;
	
	private List<Cookie> cookies;

	public HttpServletRequestImpl(HttpServletRequest request) {
		super(request);
		cookies = new ArrayList<>();
		populateCookies(request);
	}
	
	private void populateCookies(HttpServletRequest request) {
		for(Cookie requestCookie : request.getCookies()) {
			cookies.add(requestCookie);
		}
	}

	public HttpHeaders getHeaders() {
		if (this.headers == null) {
			this.headers = new HttpHeaders();

			for (Enumeration<?> names = getHeaderNames(); names.hasMoreElements();) {
				String headerName = (String) names.nextElement();
				for (Enumeration<?> headerValues = getHeaders(headerName);
						headerValues.hasMoreElements();) {
					String headerValue = (String) headerValues.nextElement();
					this.headers.add(headerName, headerValue);
				}
			}

			// HttpServletRequest exposes some headers as properties:
			// we should include those if not already present
			try {
				MediaType contentType = this.headers.getContentType();
				if (contentType == null) {
					String requestContentType = getContentType();
					if (StringUtils.hasLength(requestContentType)) {
						contentType = MediaType.parseMediaType(requestContentType);
						if (contentType.isConcrete()) {
							this.headers.setContentType(contentType);
						}
					}
				}
				if (contentType != null && contentType.getCharset() == null) {
					String requestEncoding = getCharacterEncoding();
					if (StringUtils.hasLength(requestEncoding)) {
						Charset charSet = Charset.forName(requestEncoding);
						Map<String, String> params = new LinkedCaseInsensitiveMap<>();
						params.putAll(contentType.getParameters());
						params.put("charset", charSet.toString());
						MediaType mediaType = new MediaType(contentType.getType(), contentType.getSubtype(), params);
						this.headers.setContentType(mediaType);
					}
				}
			}
			catch (InvalidMediaTypeException ex) {
				// Ignore: simply not exposing an invalid content type in HttpHeaders...
			}

			if (this.headers.getContentLength() < 0) {
				int requestContentLength = getContentLength();
				if (requestContentLength != -1) {
					this.headers.setContentLength(requestContentLength);
				}
			}
		}

		return this.headers;
	}
	
	/**
	 * Add {@link Cookie}
	 * 
	 * @param cookie cookie to add
	 */
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}
	
	@Override
	public Cookie[] getCookies() {
		List<Cookie> tempCookies = new ArrayList<>(cookies);
		
		return tempCookies.toArray(new Cookie[0]);
	}
}
