package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entity.User;
import com.example.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Fetching User from Database
		User user = repository.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Culd not found user");
		}

		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails;

	}

}
