package com.example.demo.config;


import com.example.demo.service.security.DbUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //     зависимость кодировщика паролей
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
//
//     зависимость нашей имплементаци UserDetailsService
    @Autowired
    public DbUserDetailsService dbUserDetailsService;
//
    @Autowired
    private DataSource dataSource;
//
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(dbUserDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
//
    @Bean
    public UserDetailsManager users(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(dbUserDetailsService)
                .passwordEncoder(encoder())
                .and()
                .authenticationProvider(authenticationProvider())
                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/students", "/webjars/**", "/css/**", "/users/new").permitAll()          // сюда могут попасть все
                        .anyRequest().authenticated())                                // тут только авторизованные
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout().logoutSuccessUrl("/")
                .and().csrf().disable()

        ;
        return http.build();
    }
}
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails user =
////                User.withDefaultPasswordEncoder()
////                        .username("root")
////                        .password("root")
////                        .roles("USER")
////                        .build();
////
////        UserDetails admin =
////                User.withDefaultPasswordEncoder()
////                        .username("admin")
////                        .password("admin")
////                        .roles("ADMIN")
////                        .build();
////
////        return new InMemoryUserDetailsManager(user, admin);
////    }
//
//}
