package edu.remad.tutoring2.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.remad.tutoring2.controllers.helper.DownloadUtilities;

@Controller
@RequestMapping("/downloads")
public class DownloadController {

	@GetMapping(value = "/vfdi", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getVfdi() throws IOException {
		InputStream in = getClass().getResourceAsStream("/download/voices_from_deep_inside_26.mp3");

		return IOUtils.toByteArray(in);
	}

	@GetMapping(value = "/vfdi2", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getVfdi2() throws IOException {
		InputStream in = getClass().getResourceAsStream("/download/voices_from_deep_inside_26.mp3");
		byte[] content = IOUtils.toByteArray(in);
		HttpHeaders httpHeaders = DownloadUtilities.createHttpHeaders("voices_from_deep_inside_26.mp3");

		return ResponseEntity
				.ok()
				.contentLength(content.length)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.headers(httpHeaders)
				.body(content);
	}
}
