package com.ashish.Config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashish.Entity.StudentEntity;
import com.ashish.Repository.StudentRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private StudentRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 StudentEntity user=repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
		// TODO Auto-generated method stub
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole().replace("ROLE",""))
				.build();
	}

}
