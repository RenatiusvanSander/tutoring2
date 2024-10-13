package edu.remad.tutoring2.services.impl;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.services.PdfCreatorService;
import edu.remad.tutoring2.services.pdf.ContentLayoutData;
import edu.remad.tutoring2.services.pdf.PDFComplexInvoiceBuilder;
import edu.remad.tutoring2.services.pdf.PDFCreationBuilder;
import edu.remad.tutoring2.services.pdf.utilities.PdfUtilities;

@Service
public class PdfCreatorServiceImpl implements PdfCreatorService {

	@Override
	public byte[] createInvoicePdf(InvoiceEntity invoice) {
		return new PDFComplexInvoiceBuilder().invoice(invoice).build();
	}

	@Override
	public byte[] mergeInvoices(List<byte[]> invoicesToMerge) {
		return null;
	}

	@Override
	public byte[] createInvoicesPdfs(List<InvoiceEntity> invoices) {
		byte[] invoicesPdfs = new byte[0];

		if (invoices.isEmpty()) {
			return invoicesPdfs; // own exception would be better
		}

		List<ContentLayoutData> contentLayoutDatas = PdfUtilities.createContentLayoutDatas(invoices);
		try {
			return new PDFCreationBuilder().contentLayoutData(contentLayoutDatas).paperFormat(PDRectangle.A4)
					.buildAsByteArray();
		} catch (IOException e) {
			throw new RuntimeException("Create one PDF from multiple invoices does not work."); // own exception
		}
	}
}
