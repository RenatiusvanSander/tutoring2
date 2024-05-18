package edu.remad.tutoring2.services;

import java.time.LocalDateTime;
import java.util.List;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public interface InvoiceService {

	InvoiceEntity saveInvoice(TutoringAppointmentEntity tutoringAppointment);
	
	InvoiceEntity saveInvoice(InvoiceEntity invoice);
	
	List<InvoiceEntity> getInvoicesByUserId();
	
	InvoiceEntity getInvoicesById(Long Id);
	
	List<InvoiceEntity> getInvoicesByInvoiceDate(LocalDateTime invoiceDate);
	
	List<InvoiceEntity> getInvoices();
	
	List<InvoiceEntity> getPagedInvoicesByUserId();
	
	byte[] loadInvoiceFile(Long id);
	
	List<byte[]> loadInvoices(List<Long> ids);
	
	List<byte[]> loadInvoicesAndMergeToOneFile(List<Long> ids);
	
	byte[] saveInvoiceFile(byte[] invoiceFile);
	
	List<byte[]> saveInvoiceFiles(List<Long> ids);
}
