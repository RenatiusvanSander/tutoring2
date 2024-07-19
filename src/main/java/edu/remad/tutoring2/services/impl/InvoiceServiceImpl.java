package edu.remad.tutoring2.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.globalexceptions.HttpStatus404Exception;
import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.InvoiceEntityRepository;
import edu.remad.tutoring2.services.AbstractTutoringService;
import edu.remad.tutoring2.services.InvoiceService;
import edu.remad.tutoring2.services.PriceService;

@Service
public class InvoiceServiceImpl extends AbstractTutoringService<InvoiceEntity> implements InvoiceService {

	private final InvoiceEntityRepository invoiceEntityRepository;

	private final PriceService priceService;

	public InvoiceServiceImpl(InvoiceEntityRepository invoiceEntityRepository, PriceService priceService) {
		this.invoiceEntityRepository = invoiceEntityRepository;
		this.priceService = priceService;
	}

	@Override
	public InvoiceEntity saveInvoice(TutoringAppointmentEntity tutoringAppointment) {
		PriceEntity price = null;
		try {
			price = priceService.loadPriceByUserAndServiceContract(tutoringAppointment.getTutoringAppointmentUser(),
					tutoringAppointment.getServiceContractEntity());
		} catch (JpaObjectRetrievalFailureException e) {
			throw new HttpStatus404Exception("price was not found", null, null);
		}

		InvoiceEntity unsavedNewInvoice = new InvoiceEntity();
		unsavedNewInvoice.setInvoiceCreationDate(LocalDateTime.now());
		unsavedNewInvoice.setInvoiceDate(LocalDate.now().atStartOfDay());
		unsavedNewInvoice.setInvoiceServiceContract(tutoringAppointment.getServiceContractEntity());
		unsavedNewInvoice.setInvoiceTutoringDate(tutoringAppointment.getTutoringAppointmentDate());
		unsavedNewInvoice.setInvoiceTutoringHours(tutoringAppointment.getTutoringAppointmentStartDateTime()
				.until(tutoringAppointment.getTutoringAppointmentEndDateTime(), ChronoUnit.HOURS));
		unsavedNewInvoice.setInvoiceUser(tutoringAppointment.getTutoringAppointmentUser());
		unsavedNewInvoice.setPrice(price);

		InvoiceEntity loadedInvoice = invoiceEntityRepository.saveAndFlush(unsavedNewInvoice);
		deProxy(loadedInvoice);

		return loadedInvoice;
	}

	@Override
	public InvoiceEntity saveInvoice(InvoiceEntity invoice) {
		InvoiceEntity savedInvoice = invoiceEntityRepository.saveAndFlush(invoice);
		deProxy(savedInvoice);

		return savedInvoice;
	}

	@Override
	public List<InvoiceEntity> getInvoicesByUserId(UserEntity user) {
		InvoiceEntity invoiceProbe = new InvoiceEntity();
		invoiceProbe.setInvoiceUser(user);

		Example<InvoiceEntity> example = Example.of(invoiceProbe);
		List<InvoiceEntity> usersInvoices = invoiceEntityRepository.findAll(example);
		if (!usersInvoices.isEmpty()) {
			deProxy(usersInvoices.get(0));
		}

		return usersInvoices;
	}

	@Override
	public InvoiceEntity getInvoiceById(Long Id) {
		InvoiceEntity loadeInvoice = invoiceEntityRepository.getReferenceById(Id);
		deProxy(loadeInvoice);
		
		return loadeInvoice;
	}

	@Override
	public List<InvoiceEntity> getInvoicesByInvoiceDate(LocalDateTime invoiceDate) {
		return null;
	}

	@Override
	public List<InvoiceEntity> getInvoices() {
		List<InvoiceEntity> loadedInvoices = invoiceEntityRepository.findAll();
		
		if(!loadedInvoices.isEmpty()) {
			deProxy(loadedInvoices.get(0));
		}
		
		return loadedInvoices;
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

	@Override
	protected void deProxy(InvoiceEntity invoice) {
		invoice.getInvoiceServiceContract();
		invoice.getInvoiceUser();
		invoice.getPrice();
	}

	@Override
	public List<InvoiceEntity> getInvoicesByInvoiceDate(UserEntity user, LocalDateTime invoiceDate) {
		List<InvoiceEntity> loadedInvoices = invoiceEntityRepository.findByUserAndInvoiceDate(user, invoiceDate);
		
		if(!loadedInvoices.isEmpty()) {
			deProxy(loadedInvoices.get(0));
		}
		
		return loadedInvoices;
	}

}
