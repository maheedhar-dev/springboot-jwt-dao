package com.sprindao.jwt.service;

import com.sprindao.jwt.entity.Role;
import com.sprindao.jwt.entity.User;
import com.sprindao.jwt.exception.BusinessException;
import com.sprindao.jwt.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(user -> {
            return org.springframework.security.core.userdetails.User.builder()
                  .username(user.getUsername())
                  .password(user.getPassword())
                  .disabled(!user.isEnabled())
                  .authorities(user.getRoles().stream().map(Role::getName).toArray(String[]::new)).build();
                }
        ).orElseThrow(() -> new BusinessException("User not found"));
    }

}
