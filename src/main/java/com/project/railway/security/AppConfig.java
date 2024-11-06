package com.project.railway.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorizeRequests -> authorizeRequests.requestMatchers(HttpMethod.GET, "/api/home/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/home/**").permitAll().requestMatchers("/api/admin/**")
						.authenticated().requestMatchers(HttpMethod.GET,"/api/user/**").hasRole("USER").anyRequest()
						.authenticated())
				.httpBasic(Customizer.withDefaults());

		http.csrf(csrf -> csrf.disable());

		return http.build();
	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(configurer -> configurer.anyRequest().permitAll());
//
//		// use HTTP Basic authentication
//		http.httpBasic(Customizer.withDefaults());
//
//		// disable Cross Site Request Forgery (CSRF)
//		// in general, not required for stateless REST APIs that use POST, PUT, DELETE
//		// and/or PATCH
//		http.csrf(csrf -> csrf.disable());
//
//		return http.build();
//
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
