package edu.remad.tutoring2.security;

import javax.servlet.http.Cookie;

public class Tutoring2Cookie extends org.springframework.boot.web.server.Cookie {

	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Cookie toJavaxHttpCookie() {
		Cookie cookie = new Cookie(getName(), getValue());
		cookie.setComment("Test");
		cookie.setPath("/");
		cookie.setMaxAge(getMaxAge().hashCode());
		
		return cookie;
	}

	@Override
	public String toString() {
		return getName() + "=" + getValue();
	}
}
