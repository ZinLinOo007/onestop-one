package com.jdc.one.traders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdc.one.traders.security.JwtTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class TradersApiSecurityConfig {
	
	@Autowired
	private JwtTokenAuthenticationFilter authenticationFilter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
		return http
					.cors().and()
					.csrf().disable()
					.authorizeRequests()
						.mvcMatchers("").permitAll()
						.mvcMatchers("/admin/**").hasAnyAuthority("Admin")
						.anyRequest().authenticated().and()
						.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
					.build();
	}
		
	
}
