package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  // @SuppressWarnings("deprecation")
  // @Bean
  // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  //   http.authorizeRequests((requests)->requests.requestMatchers("/login").permitAll().anyRequest().authenticated());
  //   http.addFilterBefore(new AuthorizationFilter(null), UsernamePasswordAuthenticationFilter.class);
	// 	return http.build();
	// }
}
