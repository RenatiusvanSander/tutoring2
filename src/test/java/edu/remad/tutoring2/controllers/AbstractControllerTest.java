package edu.remad.tutoring2.controllers;

import java.time.LocalDateTime;
import java.util.List;

import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

public abstract class AbstractControllerTest {
	
	protected TutoringAppointmentEntity createAppointment() {
		TutoringAppointmentEntity appointment = new TutoringAppointmentEntity();
		appointment.setTutoringAppointmentNo(1);
		appointment.setTutoringAppointmentDate(LocalDateTime.of(2024, 3, 14, 20, 0));
		appointment.setTutoringAppointmentStartDateTime(LocalDateTime.of(2024, 3, 14, 20, 0));
		appointment.setTutoringAppointmentEndDateTime(LocalDateTime.of(2024, 3, 14, 21, 0));
		appointment.setTutoringAppointmentUser(createUser());
		
		return appointment;
	}
	
	protected UserEntity createUser() {
		UserEntity user = new UserEntity();
		user.setCellPhone("+4953535343435747");
		user.setCreationDate(LocalDateTime.of(2022, 3, 14, 21, 0));
		user.setEmail("maxmustermann@nirgendwo.de");
		user.setFirstName("Max");
		user.setLastName("Mustermann");
		user.setGender("male");
		user.setUsername("mustermann");
		user.setId(1l);
		user.setPassword("MusterCity");
		List<AddressEntity> address = List.of(createAddress());
		user.setAddresses(address);
		Role role = new Role();
		role.setId(1);
		role.setName("Admin");
		List<Role> roles = List.of(role);
		user.setRoles(roles);
		user.setEnabled(true);
		
		return user;
	}
	
	protected AddressEntity createAddress() {
		AddressEntity address = new AddressEntity();
		address.setAddressHouseNo("24");
		address.setAddressStreet("Hohlenbarg");
		address.setAddressZipCode(createZipCode());
		
		return address;
	}
	
	protected ZipCodeEntity createZipCode() {
		ZipCodeEntity zipCode = new ZipCodeEntity();
		zipCode.setId(1);
		zipCode.setZipCode("76246");
		zipCode.setZipCodeLocation("Hohleburg");
		zipCode.setZipCodeCreationDate(LocalDateTime.of(2024, 3, 10, 21, 0));
		
		return zipCode;
	}
}
