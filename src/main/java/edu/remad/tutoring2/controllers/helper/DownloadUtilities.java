package edu.remad.tutoring2.controllers.helper;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;

public final class DownloadUtilities {

	private DownloadUtilities() {
		//
	}
	
	public static HttpHeaders createHttpHeaders(String fileName) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders
		.setContentDisposition(
				ContentDisposition
				.builder("attachment")
				.filename(fileName)
				.build());
		
		return httpHeaders;
	}
}
