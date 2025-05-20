package com.example.billing.securityconfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.billing.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private AuthenticationService authenticationService;

	
	  @Bean 
	  public BCryptPasswordEncoder passwordEncoder() { 
		  return new
	 	BCryptPasswordEncoder(); 
	  }
	 

	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	      http.csrf(csrf -> csrf.disable()) 
	          .authorizeHttpRequests(auth -> auth
	              .requestMatchers("/auth/SignUp", "/auth/Signin").permitAll() 
	              .anyRequest().authenticated() // secure all other requests
	          );

	      return http.build();
	  }

}


