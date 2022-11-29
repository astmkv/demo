package com.example.demo.service.security;

import com.example.demo.db.entities.User;
import com.example.demo.db.repositories.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DbUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. получить пользователя
        User user = userRepository.findByUsername(username);

        // если пользователь не найден
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        // 2. вернуть объект UserDetails
        return new DbUserDetails(user);
    }
}
