package edu.remad.tutoring2.services;

import java.io.IOException;
import java.util.List;

import edu.remad.tutoring2.models.InvoiceEntity;

public interface PdfCreatorService {

	byte[] createInvoicePdf(InvoiceEntity invoice);
	
	byte[] createInvoicesPdfs(List<InvoiceEntity> invoices);
	
	byte[] mergeInvoices(List<byte[]> invoicesToMerge);
}
