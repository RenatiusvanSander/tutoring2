package edu.remad.tutoring2.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.remad.tutoring2.dto.RegistrationDto;
import edu.remad.tutoring2.dto.UserDto;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;
import edu.remad.tutoring2.repositories.RoleRepository;
import edu.remad.tutoring2.repositories.UserEntityRepository;
import edu.remad.tutoring2.services.UserService;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class UserServiceImpl implements UserService {

	private UserEntityRepository userEntityRepository;

	private RoleRepository roleRepository;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserEntityRepository userRepository, RoleRepository rolesRepository,
			PasswordEncoder passwordEncoder) {
		this.userEntityRepository = userRepository;
		this.roleRepository = rolesRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserEntity saveUser(RegistrationDto registrationDto) {
		Role role = roleRepository.findByName("USER");
		List<Role> roles = Arrays.asList(role);

		AddressEntity address = new AddressEntity();
		address.setAddressStreet(registrationDto.getUsername());
		address.setAddressHouseNo(registrationDto.getAddressHouseNo());
		
		ZipCodeEntity zipCode = new ZipCodeEntity();
		zipCode.setZipCode(registrationDto.getZipCode());
		zipCode.setZipCodeLocation(registrationDto.getZipCodeLocation());
		zipCode.setZipCodeCreationDate(registrationDto.getZipCodeCreationDate());
		address.setAddressZipCode(zipCode);
		
		List<AddressEntity> addresses = Arrays.asList(address);
		UserEntity user = UserEntity.builder().username(registrationDto.getUsername()).email(registrationDto.getEmail())
				.password(passwordEncoder.encode(registrationDto.getPassword())).enabled(false).roles(roles)
				.firstName(registrationDto.getFirstName()).lastName(registrationDto.getLastName())
				.gender(registrationDto.getGender()).cellPhone(registrationDto.getCellPhone()).addresses(addresses).creationDate(registrationDto.getZipCodeCreationDate())
				.build();

		System.out.println("#### UserService versucht zu speichern : " + user);

		UserEntity savedUser = userEntityRepository.saveAndFlush(user);
		
		System.out.println("#### UserService speicherte : " + savedUser);
		savedUser.getAddresses().get(0);
		savedUser.getRoles().get(0);
		
		return savedUser;
	}

	@Override
	public boolean isUserExisting(String username, String email) {
		UserEntity resultUser = userEntityRepository.findByUsername(username);
		UserEntity resultUserByEmail = userEntityRepository.findByEmail(email);

		return (resultUser != null) && (resultUserByEmail != null);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userEntityRepository.findAll().stream().map(user -> new UserDto()).collect(Collectors.toList());
	}

	@Override
	public String[] createRolesArray(List<Role> roles) {
		return roles.stream().map(Role::getName).toArray(String[]::new);
	}

	@Override
	public boolean activateUser(String email) {
		UserEntity user = userEntityRepository.findByEmail(email);

		if (!user.getEnabled()) {
			user.setEnabled(true);
			userEntityRepository.saveAndFlush(user);
			return true;
		}

		return false;
	}
}
