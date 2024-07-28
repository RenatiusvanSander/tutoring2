package edu.remad.tutoring2.services;

import java.time.LocalDateTime;
import java.util.List;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

public interface InvoiceService {

	InvoiceEntity saveInvoice(TutoringAppointmentEntity tutoringAppointment);
	
	InvoiceEntity saveInvoice(InvoiceEntity invoice);
	
	InvoiceEntity getInvoiceById(Long Id);
	
	List<InvoiceEntity> getInvoicesByInvoiceDate(LocalDateTime invoiceDate);
	
	List<InvoiceEntity> getInvoicesByInvoiceDate(UserEntity user, LocalDateTime invoiceDate);
	
	List<InvoiceEntity> getInvoices();
	
	List<InvoiceEntity> getPagedInvoicesByUserId(int page, int size);
	
	byte[] loadInvoiceFile(Long id);
	
	List<byte[]> loadInvoiceFiles(List<Long> ids);
	
	List<byte[]> loadInvoicesAndMergeToOneFile(List<Long> ids);
	
	byte[] saveInvoiceFile(byte[] invoiceFile, long invoiceNo);
	
	List<byte[]> saveInvoiceFiles(List<Long> ids);

	List<InvoiceEntity> getInvoicesByUserId(UserEntity user);
}
