package edu.remad.tutoring2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.services.PdfCreatorService;
import edu.remad.tutoring2.services.pdf.utilities.PdfUtilities;

@Service
public class PdfCreatorServiceImpl implements PdfCreatorService {

	@Override
	public byte[] createInvoicePdf(InvoiceEntity invoice) {
		PdfUtilities.createContentLayoutData2(invoice);
		return null;
	}

	@Override
	public byte[] mergeInvoices(List<byte[]> invoicesToMerge) {
		// TODO Auto-generated method stub
		return null;
	}

}
