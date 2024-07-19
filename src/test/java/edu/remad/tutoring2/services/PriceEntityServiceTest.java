package edu.remad.tutoring2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.UserEntityRepository;

@Transactional
public class PriceEntityServiceTest extends AbstractJunit5ServiceJpaTest {

	@Autowired
	private PriceService priceService;

	@Autowired
	private ServiceContractService serviceContractService;

	@Autowired
	private UserEntityRepository userEntityRepository;

	private UserEntity user;

	@BeforeEach
	public void setUp() {
		user = userEntityRepository.findFirstByUsername("admin");
	}

	@Test
	public void priceServiceNotNulltest() {
		assertNotNull(priceService);
	}

	@Test
	public void savePriceTest() {
		ServiceContractEntity sc = createServiceContractEntity();
		sc.setServiceContractNo(0l);
		ServiceContractEntity sce = serviceContractService.createServiceContract(sc);

		PriceEntity savedPrice = priceService.save(user, BigDecimal.valueOf(15.00), sce);
		assertNotNull(savedPrice);
		assertNotNull(savedPrice.getServiceContract());
		assertNotNull(savedPrice.getUser());
		assertEquals(15.00f, savedPrice.getPrice().floatValue());
	}

	@Test
	public void updatePriceTest() {
		ServiceContractEntity sc = createServiceContractEntity();
		sc.setServiceContractNo(0l);
		ServiceContractEntity sce = serviceContractService.createServiceContract(sc);

		PriceEntity savedPrice = priceService.save(user, BigDecimal.valueOf(15.00), sce);
		savedPrice.setPrice(BigDecimal.valueOf(17.00f));

		PriceEntity loadedPrice = priceService.updatePrice(savedPrice);
		assertEquals(17.00f, loadedPrice.getPrice().floatValue());
	}

	@Test
	public void deletePriceTest() {
		ServiceContractEntity sc = createServiceContractEntity();
		sc.setServiceContractNo(0l);
		ServiceContractEntity sce = serviceContractService.createServiceContract(sc);

		PriceEntity savedPrice = priceService.save(user, BigDecimal.valueOf(15.00), sce);
		priceService.deletePrice(savedPrice.getId());

		try {
			priceService.loadPriceByUserAndServiceContract(savedPrice.getId());
		} catch (JpaObjectRetrievalFailureException e) {
			assertEquals(JpaObjectRetrievalFailureException.class, e.getClass());
		}
	}

	@Test
	public void loadPriceByUserAndServiceContractTest() {
		ServiceContractEntity sc = createServiceContractEntity();
		sc.setServiceContractNo(0l);
		ServiceContractEntity sce = serviceContractService.createServiceContract(sc);
		PriceEntity savedPrice = priceService.save(user, BigDecimal.valueOf(15.00), sce);

		PriceEntity actualPrice = priceService.loadPriceByUserAndServiceContract(user, sce);
		
		assertNotNull(actualPrice);
		assertEquals(savedPrice.getId(), actualPrice.getId());
		assertTrue(Objects.equals(savedPrice.getPrice(), actualPrice.getPrice()));
		assertTrue(Objects.equals(savedPrice, actualPrice));
	}
}
