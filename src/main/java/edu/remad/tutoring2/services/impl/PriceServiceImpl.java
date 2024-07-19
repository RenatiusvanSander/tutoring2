package edu.remad.tutoring2.services.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.PriceEntityRepository;
import edu.remad.tutoring2.services.AbstractTutoringService;
import edu.remad.tutoring2.services.PriceService;

@Service
public class PriceServiceImpl extends AbstractTutoringService<PriceEntity> implements PriceService {

	private final PriceEntityRepository priceEntityRepository;
	
	@Autowired
	public PriceServiceImpl(PriceEntityRepository priceEntityRepository) {
		this.priceEntityRepository = priceEntityRepository;
	}
	
	@Override
	public PriceEntity save(UserEntity user, BigDecimal price, ServiceContractEntity serviceContract) {
		PriceEntity unsavedPrice = new PriceEntity(1L, user, price, serviceContract);
		PriceEntity savedPrice = priceEntityRepository.saveAndFlush(unsavedPrice);
		
		deProxy(savedPrice);
		
		return savedPrice;
	}

	@Override
	public PriceEntity loadPriceByUserAndServiceContract(UserEntity user, ServiceContractEntity serviceContract) {
		PriceEntity loadedPrice = priceEntityRepository.findByUserAndServiceContract(user, serviceContract);
		deProxy(loadedPrice);
		
		return loadedPrice;
	}

	@Override
	public PriceEntity updatePrice(PriceEntity price) {
		PriceEntity updatedPrice = priceEntityRepository.saveAndFlush(price);
		deProxy(updatedPrice);
		
		return updatedPrice;
	}

	@Override
	public void deletePrice(long id) {
		priceEntityRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		priceEntityRepository.deleteAll();
	}

	@Override
	public PriceEntity loadPriceByUserAndServiceContract(long id) {
		PriceEntity loadedPrice = priceEntityRepository.getReferenceById(id);
		deProxy(loadedPrice);
		
		return loadedPrice;
	}
	
	@Override
	protected void deProxy(PriceEntity price) {
		price.getUser();
		price.getServiceContract();
	}
}
