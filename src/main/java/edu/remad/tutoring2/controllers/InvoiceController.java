package edu.remad.tutoring2.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.remad.tutoring2.models.InvoiceEntity;

@Controller
@RequestMapping(InvoiceController.REQUEST_MAPPING_INVOICE)
public class InvoiceController {
	
	public static final String REQUEST_MAPPING_INVOICE = "/api/invoices";
	
	@PostMapping(value = "/create-invoice/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void createInvoice(@PathVariable("id") Long tutoringAppointmentId) {
	}
	
	@GetMapping(value = "/get-invoices/by-user-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceEntity>> getInvoicesByUserId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(Collections.emptyList());
	}
	
	@GetMapping(value = "/get-invoices/by-invoice-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceEntity> getInvoicesByInvoiceId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(new InvoiceEntity());
	}
	
	@GetMapping(value = "/get-invoices/by-invoice-date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceEntity>> getInvoicesByInvoiceDate(@PathVariable("date") String date) {
		return ResponseEntity.ok(Collections.emptyList());
	}
	
	@GetMapping(value = "/get-invoices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceEntity>> getInvoices() {
		return ResponseEntity.ok(Collections.emptyList());
	}
	
	@GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadInvoice(@PathVariable("id") Long id) throws IOException {
		InputStream in = new ByteArrayInputStream("aaa".getBytes());
		byte[] content = IOUtils.toByteArray(in);
		HttpHeaders httpHeaders = createHttpHeaders("your-invoice.pdf");
		
		return ResponseEntity
				.ok()
				.contentLength(content.length)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.headers(httpHeaders)
				.body(content);
	}
	
	@GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadInvoices(@PathVariable("id") List<Long> ids) throws IOException {
		InputStream in = new ByteArrayInputStream("aaa".getBytes());
		byte[] content = IOUtils.toByteArray(in);
		HttpHeaders httpHeaders = createHttpHeaders("your-invoice.pdf");
		
		return ResponseEntity
				.ok()
				.contentLength(content.length)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.headers(httpHeaders)
				.body(content);
	}

	private HttpHeaders createHttpHeaders(String fileName) {
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
