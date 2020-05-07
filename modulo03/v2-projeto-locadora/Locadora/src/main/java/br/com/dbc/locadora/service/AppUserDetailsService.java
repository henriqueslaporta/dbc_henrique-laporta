package br.com.dbc.locadora.service;

import br.com.dbc.locadora.entity.User;
import br.com.dbc.locadora.repository.RoleRepository;
import br.com.dbc.locadora.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Camila e Henrique
 */
@Component
public class AppUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public User createNewUser(User user){
        if(userRepository.findByUsername(user.getUsername()) != null) return null;
        user.setRoles(Arrays.asList( roleRepository.findByRoleName("STANDARD_USER") ));
        System.out.println("\n User: " + user.getFirstName() + " " + user.getLastName() + " " + user.getUsername()
        + " " + user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User changingUserPassword(User user){
        User atual = userRepository.findByUsername(user.getUsername());
        if(atual == null) return null;
        atual.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(atual);
    }
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }
}
