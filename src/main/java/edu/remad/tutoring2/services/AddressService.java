package edu.remad.tutoring2.services;

import edu.remad.tutoring2.dto.AddressDto;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.UserEntity;

public interface AddressService {

	AddressEntity loadAddress(UserEntity user);
	
	boolean saveAddress(AddressDto addressDto);
	
	boolean updateAddress();
	
	boolean deleteAddress(Long addressId);
	
	boolean deleteAddress(AddressDto addressDto);
	
	boolean deleteAddress(AddressEntity addressEntity);
}
