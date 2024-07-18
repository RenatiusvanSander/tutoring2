package edu.remad.tutoring2.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.repositories.InvoiceEntityRepository;
import edu.remad.tutoring2.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private final InvoiceEntityRepository invoiceEntityRepository;
	
	public InvoiceServiceImpl(InvoiceEntityRepository invoiceEntityRepository) {
		this.invoiceEntityRepository = invoiceEntityRepository;
	}
	
	@Override
	public InvoiceEntity saveInvoice(TutoringAppointmentEntity tutoringAppointment) {
		InvoiceEntity unsavedNewInvoice = new InvoiceEntity();
		unsavedNewInvoice.setInvoiceCreationDate(LocalDateTime.now());
		unsavedNewInvoice.setInvoiceDate(LocalDate.now().atStartOfDay());
		unsavedNewInvoice.setInvoiceServiceContract(tutoringAppointment.getServiceContractEntity());
		unsavedNewInvoice.setInvoiceTutoringDate(tutoringAppointment.getTutoringAppointmentDate());
		unsavedNewInvoice.setInvoiceTutoringHours(tutoringAppointment.getTutoringAppointmentStartDateTime().until(tutoringAppointment.getTutoringAppointmentEndDateTime(), ChronoUnit.HOURS));
		unsavedNewInvoice.setInvoiceUser(tutoringAppointment.getTutoringAppointmentUser());
		//unsavedNewInvoice.setPrice(tutoringAppointment.);
		
		InvoiceEntity loadedInvoice = invoiceEntityRepository.saveAndFlush(unsavedNewInvoice);
		
		return loadedInvoice;
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
