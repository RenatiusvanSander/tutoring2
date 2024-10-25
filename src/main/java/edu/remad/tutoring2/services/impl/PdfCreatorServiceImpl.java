package edu.remad.tutoring2.services.impl;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.services.PdfCreatorService;
import edu.remad.tutoring2.services.impl.exceptions.PdfCreatorServiceException;
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
			throw new PdfCreatorServiceException("PdfCreatorServiceException: No invoices data were given.");
		}

		try {
			List<ContentLayoutData> contentLayoutDatas = PdfUtilities.createContentLayoutDatas(invoices);
			invoicesPdfs = new PDFCreationBuilder().contentLayoutData(contentLayoutDatas).paperFormat(PDRectangle.A4)
					.buildAsByteArray();
			
			if(invoicesPdfs.length == 0) {
				throw new PdfCreatorServiceException("PdfCreatorServiceException: Invoices PDFs are not created into one file.");
			}
			
			return invoicesPdfs;
						
		} catch (IOException | PdfCreatorServiceException e) {
			throw new PdfCreatorServiceException("PdfCreatorServiceException: Create one PDF from multiple invoices does not work.", e); // own exception
		}
	}
}
