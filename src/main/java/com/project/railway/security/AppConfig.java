package com.project.railway.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email, password, enabled FROM user WHERE email = ?");
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//				"SELECT u.email,r.role FROM role r JOIN user_roles ur ON ur.roles_id = r.id "
//						+ "JOIN user u ON ur.user_id = u.id WHERE u.email = ?");
		// simplified query to understand what are we doing!
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select u.email,r.role from user u,role r,user_roles ur where u.id = ur.user_id and r.id = ur.role_id and u.email = ?");

		return jdbcUserDetailsManager;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserDetailsManager userDetailsManager) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsManager);

		// No password encoder for plain text (for testing purposes only, not
		// recommended)
//		authProvider.setPasswordEncoder(null); // This will store passwords as plain text

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,
			DaoAuthenticationProvider authenticationProvider) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.authenticationProvider(authenticationProvider);
		return auth.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/home/**").hasRole("USER")
				.requestMatchers(HttpMethod.GET,"api/testing/**").hasRole("TESTER")
				.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable());

		return http.build();
	}
}
