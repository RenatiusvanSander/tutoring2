package edu.remad.tutoring2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.services.PdfCreatorService;

@Service
public class PdfCreatorServiceImpl implements PdfCreatorService {

	@Override
	public byte[] createInvoicePdf(TutoringAppointmentEntity tutoringAppointment, InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] mergeInvoices(List<byte[]> invoicesToMerge) {
		// TODO Auto-generated method stub
		return null;
	}

}
