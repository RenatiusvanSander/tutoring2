package edu.remad.tutoring2.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Override
	public InvoiceEntity saveInvoice(TutoringAppointmentEntity tutoringAppointment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvoiceEntity saveInvoice(InvoiceEntity invoice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceEntity> getInvoicesByUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvoiceEntity getInvoicesById(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceEntity> getInvoicesByInvoiceDate(LocalDateTime invoiceDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceEntity> getInvoices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceEntity> getPagedInvoicesByUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] loadInvoiceFile(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<byte[]> loadInvoices(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<byte[]> loadInvoicesAndMergeToOneFile(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] saveInvoiceFile(byte[] invoiceFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<byte[]> saveInvoiceFiles(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
