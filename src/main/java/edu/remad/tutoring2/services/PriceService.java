package edu.remad.tutoring2.services;

import java.math.BigDecimal;

import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;

public interface PriceService {
	
	PriceEntity save(UserEntity user, BigDecimal price, ServiceContractEntity serviceContract);
	
	PriceEntity loadPriceByUserAndServiceContract(UserEntity user, ServiceContractEntity serviceContract);
	
	PriceEntity loadPriceByUserAndServiceContract(long id);
	
	PriceEntity updatePrice(PriceEntity price);
	
	void deletePrice(long id);

	void deleteAll();
}
