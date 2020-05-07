/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.MyDockerApp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tiago
 */
@RestController
@RequestMapping("/")
public class WorkRestController {
    
    @GetMapping()
    public ResponseEntity<?> index(){
        return ResponseEntity.ok("Hello Word");
    }
    
}
