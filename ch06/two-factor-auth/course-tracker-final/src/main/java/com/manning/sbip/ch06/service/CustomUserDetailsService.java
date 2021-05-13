package com.manning.sbip.ch06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manning.sbip.ch06.model.CustomUser;
import com.manning.sbip.ch06.model.User;
import com.manning.sbip.ch06.repository.UserRepository;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		SimpleGrantedAuthority simpleGrantedAuthority = null;
		if(user.isTotpEnabled()) {
			simpleGrantedAuthority = new SimpleGrantedAuthority("TOTP_AUTH_AUTHORITY");
		}
		else {
			simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
		}
		CustomUser customUser = new CustomUser(user.getUsername(), user.getPassword(), user.isVerified(), true, true, true, Arrays.asList(simpleGrantedAuthority));
		customUser.setSecurityPin(user.getSecurityPin());
		customUser.setTotpEnabled(user.isTotpEnabled());
		/*return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
				.password(user.getPassword()).roles("USER").disabled(false).build();*/
		return customUser;
	}

}
