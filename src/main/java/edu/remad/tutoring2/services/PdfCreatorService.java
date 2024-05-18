package edu.remad.tutoring2.services;

import java.util.List;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public interface PdfCreatorService {

	byte[] createInvoicePdf(TutoringAppointmentEntity tutoringAppointment, InvoiceEntity invoice);
	
	byte[] mergeInvoices(List<byte[]> invoicesToMerge);
}
