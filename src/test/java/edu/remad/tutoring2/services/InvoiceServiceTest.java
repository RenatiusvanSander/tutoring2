package edu.remad.tutoring2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.InvoiceEntityRepository;
import edu.remad.tutoring2.repositories.PriceEntityRepository;
import edu.remad.tutoring2.repositories.UserEntityRepository;
import edu.remad.tutoring2.services.impl.InvoiceServiceImpl;

@Transactional
public class InvoiceServiceTest extends AbstractJunit5ServiceJpaTest {

	@Autowired
	private ServiceContractService serviceContractService;
	
	@Autowired
	private InvoiceEntityRepository invoiceEntityRepository;

	@Autowired
	private PriceEntityRepository priceEntityRepository;

	private PriceEntity price;
	
	private UserEntity user;
	
	private ServiceContractEntity sce;
	
	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Autowired
	private PriceService priceService;

	@BeforeEach
	void setUp() {
		ServiceContractEntity sc = createServiceContractEntity();
		sc.setServiceContractNo(0l);
		sce = serviceContractService.createServiceContract(sc);
		
		PriceEntity unsavedPrice = createPrice();
		unsavedPrice.setId(0l);
		unsavedPrice.setServiceContract(sce);
		price = priceEntityRepository.saveAndFlush(unsavedPrice);
		
		user = userEntityRepository.findFirstByUsername("admin");
	}

	@Test
	public void saveInvoiceFileTest() {
		InvoiceService invoiceService = new InvoiceServiceImpl(invoiceEntityRepository, priceService);
		
		LocalDateTime invoiceDate = LocalDate.now().atStartOfDay();
		InvoiceEntity invoice = new InvoiceEntity(0, sce, 1.0f, invoiceDate, invoiceDate, user, price, invoiceDate);
		InvoiceEntity savedInvoice = invoiceEntityRepository.save(invoice);
		
		assertNotNull(savedInvoice);
		
		byte[] savedInvoiceFile = invoiceService.saveInvoiceFile("Something".getBytes(), savedInvoice.getInvoiceNo());
		assertTrue(Arrays.equals("Something".getBytes(), savedInvoiceFile));
	}
	
	@Test
	public void getInvoicesByInvoiceDateTest() {
		InvoiceService invoiceService = new InvoiceServiceImpl(invoiceEntityRepository, priceService);
		LocalDateTime invoiceDate = LocalDateTime.of(2024, 7, 21, 0, 30);
		InvoiceEntity invoice1 = new InvoiceEntity(0, sce, 1.0f, invoiceDate, invoiceDate, user, price, invoiceDate);
		InvoiceEntity invoice2 = new InvoiceEntity(0, sce, 1.0f, invoiceDate.toLocalDate().atStartOfDay().plusDays(1), invoiceDate.toLocalDate().atStartOfDay().plusDays(1), user, price, invoiceDate.toLocalDate().atStartOfDay().plusDays(1));
		InvoiceEntity invoice3 = new InvoiceEntity(0, sce, 1.0f, invoiceDate.plusHours(10), invoiceDate.plusHours(10), user, price, invoiceDate.plusHours(10));
		invoiceEntityRepository.saveAll(List.of(invoice1, invoice2, invoice3));
		
		List<InvoiceEntity> actualInvoices = invoiceService.getInvoicesByInvoiceDate(invoiceDate);
		assertEquals(3, actualInvoices.size());
	}
}
