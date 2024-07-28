package edu.remad.tutoring2.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		List<InvoiceEntity> loadedInvoices = invoiceEntityRepository.findAllByInvoiceDate(invoiceDate);
		
		if(!loadedInvoices.isEmpty()) {
			deProxy(loadedInvoices.get(0));
		}
		
		return loadedInvoices;
	}

	@Override
	public List<InvoiceEntity> getInvoices() {
		List<InvoiceEntity> loadedInvoices = invoiceEntityRepository.findAll();

		if (!loadedInvoices.isEmpty()) {
			deProxy(loadedInvoices.get(0));
		}

		return loadedInvoices;
	}

	@Override
	public List<InvoiceEntity> getPagedInvoicesByUserId(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<InvoiceEntity> pageResult = invoiceEntityRepository.findAll(pageable);

		List<InvoiceEntity> pagedInvoices = pageResult.getContent();
		deProxy(pagedInvoices.get(0));

		return pagedInvoices;
	}

	@Override
	public byte[] loadInvoiceFile(Long id) {
		InvoiceEntity loadedInvoice = invoiceEntityRepository.getReferenceById(id);

		deProxy(loadedInvoice);

		byte[] defensiveInvoiceFileCopy = null;
		System.arraycopy(loadedInvoice.getInvoiceFile(), 0, defensiveInvoiceFileCopy, 0,
				loadedInvoice.getInvoiceFile().length);

		return defensiveInvoiceFileCopy;
	}

	@Override
	public List<byte[]> loadInvoiceFiles(List<Long> ids) {
		List<InvoiceEntity> loadedInvoices = invoiceEntityRepository.findAllById(ids);

		List<byte[]> invoiceFiles = new ArrayList<>();
		if (!loadedInvoices.isEmpty()) {
			deProxy(loadedInvoices.get(0));

			for (InvoiceEntity invoice : loadedInvoices) {
				byte[] currentFile = invoice.getInvoiceFile();
				if (currentFile.length > 1) {
					invoiceFiles.add(currentFile);
				}
			}
		}

		return invoiceFiles;
	}

	@Override
	public List<byte[]> loadInvoicesAndMergeToOneFile(List<Long> ids) {
		// PDF-Service
		return null;
	}

	@Override
	public byte[] saveInvoiceFile(byte[] invoiceFile, long invoiceNo) {
		InvoiceEntity loadedInvoice = getInvoiceById(invoiceNo);

		if (loadedInvoice != null) {
			loadedInvoice.setInvoiceFile(invoiceFile);
			loadedInvoice = saveInvoice(loadedInvoice);
		}
		
		return loadedInvoice.getInvoiceFile();
	}

	@Override
	public List<byte[]> saveInvoiceFiles(List<Long> ids) {
		// PDF-Service
		return null;
	}

	@Override
	protected void deProxy(InvoiceEntity invoice) {
		invoice.getInvoiceServiceContract();
		invoice.getInvoiceUser();
		invoice.getPrice();
		invoice.getInvoiceFile();
	}

	@Override
	public List<InvoiceEntity> getInvoicesByInvoiceDate(UserEntity user, LocalDateTime invoiceDate) {
		List<InvoiceEntity> loadedInvoices = invoiceEntityRepository.findByInvoiceUserAndInvoiceDate(user, invoiceDate);

		if (!loadedInvoices.isEmpty()) {
			deProxy(loadedInvoices.get(0));
		}

		return loadedInvoices;
	}

}
