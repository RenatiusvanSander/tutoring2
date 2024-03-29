package edu.remad.tutoring2.services;

import java.util.List;

import edu.remad.tutoring2.dto.RegistrationDto;
import edu.remad.tutoring2.dto.UserDto;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;

public interface UserService {
	UserEntity saveUser(RegistrationDto registrationDto);

	boolean isUserExisting(String username, String email);

	List<UserDto> getAllUsers();

	boolean activateUser(String email);

	String[] createRolesArray(List<Role> roles);
}
