package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Administrator;
import com.example.repository.CustomAdministratorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  CustomAdministratorRepository customAdministratorRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("CustomUserDetailsServiceの動作確認");
    System.out.println(username);
    Administrator admin = customAdministratorRepository.findByMailAddress(username);
    
    if(admin == null){
      throw new UsernameNotFoundException("User not found: " + username);
    }

    return User.builder().username(admin.getMailAddress()).password(admin.getPassword()).build();
    // throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
  }
}
