package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private CustomAuthenticationProvider customAuthenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  
    
    // CustomAuthenticationProviderの注入
    http.authenticationProvider(customAuthenticationProvider);
    
    // 認可の設定
    http.authorizeHttpRequests((requests) -> requests
    .requestMatchers("/","/toInsert","/insert","/error**").permitAll()
    .requestMatchers("/css/**","/js/**","/img/**","/static/**").permitAll()
    .anyRequest().authenticated()
    );

    // ログイン設定
    http.formLogin(login -> login
    .loginProcessingUrl("/login")
    .loginPage("/")
    .defaultSuccessUrl("/employee/showList")
    .failureUrl("/")
    .usernameParameter("mailAddress")
    .passwordParameter("password")
    );

    // ログアウト設定
    http.logout(logout -> logout
    .logoutSuccessUrl("/")
    .logoutUrl("/logout")
    // .logoutRequestMatcher((RequestMatcher) new AntPathMatcher("/logout**"))
    .permitAll()
    );
    
    return http.build();
	}
}
