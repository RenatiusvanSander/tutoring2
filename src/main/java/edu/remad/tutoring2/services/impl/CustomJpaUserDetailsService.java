package edu.remad.tutoring2.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.UserEntityRepository;

@Transactional
@Service
public class CustomJpaUserDetailsService implements UserDetailsService {

	private UserEntityRepository userEntityRepository;

	@Autowired
	public CustomJpaUserDetailsService(UserEntityRepository userEntityRepository) {
		this.userEntityRepository = userEntityRepository;
	}

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userEntityRepository.findFirstByUsername(username);
		user.getRoles().get(0);
		List<Role> userRoles = user.getRoles();

		System.out.println("##### User is " + user);

		if (user == null) {
			throw new UsernameNotFoundException("Username is not found: " + username);
		}

		List<GrantedAuthority> grantedAuthorities = userRoles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		User authUser = new User(user.getUsername(), user.getPassword(), grantedAuthorities);

		return authUser;
	}
}
