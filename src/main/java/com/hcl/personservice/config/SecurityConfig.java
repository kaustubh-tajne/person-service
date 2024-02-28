//package com.hcl.personservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
////@Configuration
////@EnableWebSecurity
//public class SecurityConfig {
//
//
////    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails adminUser = User.withUsername("admin")
//                                    .password(passwordEncoder.encode("admin"))
//                                    .roles("ADMIN")
//                                    .build();
//        UserDetails user1 = User.withUsername("user1")
//                                .password(passwordEncoder.encode("user1"))
//                                .roles("USER")
//                                .build();
//        UserDetails user2 = User.withUsername("user2")
//                                .password(passwordEncoder.encode("user2"))
//                                .roles("USER")
//                                .build();
//        return new InMemoryUserDetailsManager(adminUser, user1, user2);
//    }
//
//
////    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//}
