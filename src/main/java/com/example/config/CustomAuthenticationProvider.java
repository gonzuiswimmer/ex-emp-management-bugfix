package com.example.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.service.CustomUserDetailsService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final CustomUserDetailsService customUserDetailsService;
  private final PasswordEncoder passwordEncoder;

  public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
    this.customUserDetailsService = customUserDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String inputPassword = (String) authentication.getCredentials();

    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

    System.out.println("authenticateメソッドの通過を確認");
    if (passwordEncoder.matches(inputPassword, userDetails.getPassword())) {
      System.out.println("認証成功");
      return new UsernamePasswordAuthenticationToken(username, inputPassword, null);
    } else {
        throw new BadCredentialsException("Authentication failed");
    }
    // throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
    // throw new UnsupportedOperationException("Unimplemented method 'supports'");
  }
  

  
}
