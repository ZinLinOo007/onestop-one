package com.jdc.one.traders.model.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.input.SignInDto;
import com.jdc.one.traders.model.dto.input.SignUpDto;
import com.jdc.one.traders.model.dto.output.LoginResultDto;
import com.jdc.one.traders.model.dto.output.LoginUserDto;
import com.jdc.one.traders.model.repo.AccountRepo;
import com.jdc.one.traders.model.service.AccountSecurity;

@Service
public class AccountServiceImpl implements AccountSecurity {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public LoginResultDto signIn(SignInDto dto) {
		var auth = authManager.authenticate(dto.authenticationToken());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return findByEmail(dto.email()).map(LoginUserDto::new).map(LoginResultDto::success)
				.orElse(LoginResultDto.error("There is no account with email. %s".formatted(dto.email())));
	}

	@Override
	public LoginResultDto signUp(SignUpDto dto) {
		
		// Create Account
		var account = dto.account();
		account.setPassword(encoder.encode(dto.password()));
		repo.save(account);
		
		// Authenticate
		var auth = authManager.authenticate(dto.authenticationToken());
		SecurityContextHolder.getContext().setAuthentication(auth);

		return findByEmail(dto.email()).map(LoginUserDto::new).map(LoginResultDto::success)
				.orElse(LoginResultDto.error("There is no account with email. %s".formatted(dto.email())));
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		return repo.findOne((root, query, builder) -> 
				builder.equal(root.get("email"), email));
	}
	
	
}
