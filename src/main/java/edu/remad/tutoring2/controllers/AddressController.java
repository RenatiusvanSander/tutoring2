package edu.remad.tutoring2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.remad.tutoring2.dto.AddressDto;
import edu.remad.tutoring2.dto.SignupDto;
import edu.remad.tutoring2.services.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@GetMapping("/add-signup-address")
	public String addSignupAddress(@ModelAttribute("signupDto") SignupDto signupDto) {
		AddressDto address = AddressDto.builder().addressStreet(signupDto.getAddressStreet())
				.addressHouseNo(signupDto.getAddressHouseNo()).build();
		addressService.saveAddress(address);

		return "redirect:/api/zipcode/add-signup-zipcode";
	}
}
