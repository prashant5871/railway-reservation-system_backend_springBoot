package com.project.railway.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.railway.entities.User;
import com.project.railway.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with provided username " + username);
		}

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();

		return new org.springframework.security.core.userdetails.User(user.getEmail(), "{noop}" + user.getPassword(),
				authorities);
	}

}