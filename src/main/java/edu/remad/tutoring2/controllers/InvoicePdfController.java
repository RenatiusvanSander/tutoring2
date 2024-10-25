package edu.remad.tutoring2.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.remad.tutoring2.controllers.helper.DownloadUtilities;
import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.services.InvoiceService;
import edu.remad.tutoring2.services.PdfCreatorService;

public class InvoicePdfController {

	public static final String REQUEST_MAPPING_INVOICE = "/api/pdf-invoices";

	private final InvoiceService invoiceService;

	private final PdfCreatorService pdfCreatorService;

	@Autowired
	public InvoicePdfController(InvoiceService invoiceService, PdfCreatorService pdfCreatoreService) {
		this.invoiceService = invoiceService;
		this.pdfCreatorService = pdfCreatoreService;
	}

	@GetMapping(value = "/getPdfInvoice/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPdfInvoice(@PathVariable("id") long invoiceId) {
		InvoiceEntity loadedInvoice = invoiceService.getInvoiceById(invoiceId);
		byte[] pdfInvoiceFile = pdfCreatorService.createInvoicePdf(loadedInvoice);
		HttpHeaders httpHeaders = DownloadUtilities.createHttpHeaders("invoice-" + invoiceId + ".pdf");

		return ResponseEntity.ok().contentLength(pdfInvoiceFile.length).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.headers(httpHeaders).body(pdfInvoiceFile);
	}

	@GetMapping(value = "/getPdfInvoicesInOneFile/{ids}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPdfInvoices(@PathVariable("ids") List<Long> ids) {
		List<InvoiceEntity> invoiceEntities = invoiceService.getInvoicesByIds(ids);
		byte[] infoicesPfs = pdfCreatorService.createInvoicesPdfs(invoiceEntities);
		String joinedIds = invoiceEntities.stream().map(String::valueOf).collect(Collectors.joining("_"));
		HttpHeaders httpHeaders = DownloadUtilities.createHttpHeaders("invoices-" + joinedIds + ".pdf");

		return ResponseEntity.ok().contentLength(infoicesPfs.length).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.headers(httpHeaders).body(infoicesPfs);
	}
}
