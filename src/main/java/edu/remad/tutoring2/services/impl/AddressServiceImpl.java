package edu.remad.tutoring2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.dto.AddressDto;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.AddressRepository;
import edu.remad.tutoring2.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}

	@Override
	public AddressEntity loadAddress(UserEntity user) {
		return null;
	}

	@Override
	public boolean saveAddress(AddressDto addressDto) {
		AddressEntity address = new AddressEntity();
		address.setAddressStreet(addressDto.getAddressStreet());
		address.setAddressHouseNo(addressDto.getAddressHouseNo());
		
		this.addressRepository.saveAndFlush(address);
		
		return true;
	}

	@Override
	public boolean updateAddress() {
		return false;
	}

	@Override
	public boolean deleteAddress(Long addressId) {
		return false;
	}

	@Override
	public boolean deleteAddress(AddressDto addressDto) {
		return false;
	}

	@Override
	public boolean deleteAddress(AddressEntity addressEntity) {
		return false;
	}

}
