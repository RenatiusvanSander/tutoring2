package edu.remad.tutoring2.controllers;

import java.time.LocalDateTime;
import java.util.List;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.TokenEntity;
import edu.remad.tutoring2.models.TokenType;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

public abstract class AbstractJunit5Test {
	
	protected TutoringAppointmentEntity createAppointment() {
		TutoringAppointmentEntity appointment = new TutoringAppointmentEntity();
		appointment.setTutoringAppointmentNo(1);
		appointment.setTutoringAppointmentDate(LocalDateTime.of(2024, 3, 14, 20, 0));
		appointment.setTutoringAppointmentStartDateTime(LocalDateTime.of(2024, 3, 14, 20, 0));
		appointment.setTutoringAppointmentEndDateTime(LocalDateTime.of(2024, 3, 14, 21, 0));
		appointment.setTutoringAppointmentCreationDate(LocalDateTime.of(2024, 3, 14, 10, 0));
		appointment.setServiceContractEntity(createServiceContractEntity());
		appointment.setTutoringAppointmentUser(createUser());
		
		return appointment;
	}
	
	protected UserEntity createUser(long id) {
		UserEntity user = createUser();
		user.setId(id);
		
		return user;
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
		user.setId(3l);
		user.setPassword("MusterCity");
		List<AddressEntity> address = List.of(createAddress());
		user.setAddresses(address);
		List<Role> roles = List.of(createRole());
		user.setRoles(roles);
		user.setEnabled(true);
		
		return user;
	}

	protected Role createRole() {
		Role role = new Role();
		role.setId(1);
		role.setName("Admin");
		role.setUsers(List.of(new UserEntity()));
		
		return role;
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
	
	protected ServiceContractEntity createServiceContractEntity() {
		ServiceContractEntity serviceContract = new ServiceContractEntity();
		serviceContract.setServiceContractNo(41l);
		serviceContract.setServiceContractName("Elektrotechnik Nachhilfe");
		serviceContract.setServiceContractDescription("Nachhilfe in den Grundlagen der Elektrotechnik");
		serviceContract.setServiceContractCreationDate(LocalDateTime.parse("2024-05-11 00:00", TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		
		return serviceContract;
	}
	
	protected TokenEntity createToken() {
		return new TokenEntity(103L, "token", TokenType.BEARER, false, false, createUser());
	}
}
