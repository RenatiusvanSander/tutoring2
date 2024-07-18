package edu.remad.tutoring2.services.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.PriceEntityRepository;
import edu.remad.tutoring2.services.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	private final PriceEntityRepository priceEntityRepository;
	
	public PriceServiceImpl(PriceEntityRepository priceEntityRepository) {
		this.priceEntityRepository = priceEntityRepository;
	}
	
	@Override
	public PriceEntity save(UserEntity user, BigDecimal price, ServiceContractEntity serviceContract) {
		PriceEntity unsavedPrice = new PriceEntity(1L, user, price, serviceContract);
		PriceEntity savedPrice = priceEntityRepository.saveAndFlush(unsavedPrice);
		
		return savedPrice;
	}

	@Override
	public PriceEntity loadPriceByUserAndServiceContract(UserEntity user, ServiceContractEntity serviceContract) {
		PriceEntity loadedPrice = priceEntityRepository.findByUserAndServiceContract(user, serviceContract);
		
		return loadedPrice;
	}

	@Override
	public PriceEntity updatePrice(PriceEntity price) {
		PriceEntity updatedPrice = priceEntityRepository.saveAndFlush(price);
		
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
		
		return loadedPrice;
	}
}
