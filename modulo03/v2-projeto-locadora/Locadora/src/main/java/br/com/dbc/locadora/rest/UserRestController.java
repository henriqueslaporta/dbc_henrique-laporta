/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.entity.User;
import br.com.dbc.locadora.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Camila e Henrique
 */

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAuthority('ADMIN_USER')")
public class UserRestController {
    
    @Autowired
    private AppUserDetailsService userService;
    
    @PostMapping
    public  ResponseEntity<?> createNewUser(@RequestBody User user){
        User resultado = userService.createNewUser(user);
        if(resultado == null){
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body("{ \"error\" : \"O username: "+ user.getUsername() +" já está sendo utilizado!\" }");
        }
        return  ResponseEntity.ok(resultado);
    }
    
    @PostMapping("/password")
    public  ResponseEntity<?> changingUserPassword(@RequestBody User user){
        User resultado = userService.changingUserPassword(user);
        if(resultado == null){
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body("{ \"error\" : \"O username: "+ user.getUsername() +" não existe ou você não possue permissão!\" }");
        }
        return  ResponseEntity.ok(resultado);
    }
}
