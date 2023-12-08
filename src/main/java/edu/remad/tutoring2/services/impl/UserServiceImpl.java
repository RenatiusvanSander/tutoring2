package edu.remad.tutoring2.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.dto.RegistrationDto;
import edu.remad.tutoring2.dto.UserDto;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.RoleRepository;
import edu.remad.tutoring2.repositories.UserEntityRepository;
import edu.remad.tutoring2.services.UserService;

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
	public void saveUser(RegistrationDto registrationDto) {
		Role role = roleRepository.findByName("USER");
		UserEntity user = UserEntity.builder().username(registrationDto.getUsername()).email(registrationDto.getEmail())
				.password(passwordEncoder.encode(registrationDto.getPassword())).enabled(false)
				.roles(Arrays.asList(role)).build();

		System.out.println("#### UserService versucht zu speichern : " + user);

		userEntityRepository.save(user);
		userEntityRepository.flush();
	}

	@Override
	public boolean isUserExisting(String username) {
		UserEntity resultUser = userEntityRepository.findByUsername(username);

		return resultUser != null;
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
