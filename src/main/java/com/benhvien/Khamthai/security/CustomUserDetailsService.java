package com.benhvien.Khamthai.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.RoleRepository;
import com.benhvien.Khamthai.response.ResourceNotFoundException;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	AccountRepositoty accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountModel account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		if (account == null) {
			throw new UsernameNotFoundException("User not found with username : " + username);
		}
		return UserPrincipal.create(account);

	}

	@Transactional
	public UserDetails loadUserById(String userId) {
		AccountModel user = accountRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		return UserPrincipal.create(user);
	}
}