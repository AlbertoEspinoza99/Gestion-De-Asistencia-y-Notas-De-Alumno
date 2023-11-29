package com.colegio.services;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.colegio.dao.PersonaRepository;
import com.colegio.entity.Cargo;
import com.colegio.entity.Persona;

@Service
public class UserService implements UserDetailsService{

	
	
	@Autowired
	private PersonaRepository repoPer;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		 System.out.println("In the user details service");

	        return repoPer.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
	}

}
