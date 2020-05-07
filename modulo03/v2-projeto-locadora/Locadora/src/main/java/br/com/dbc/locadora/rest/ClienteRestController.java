/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.entity.Cliente;
import br.com.dbc.locadora.service.ClienteService;
import br.com.dbc.locadora.service.CorreiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author henrique.laporta
 */
@PreAuthorize("hasAuthority('ADMIN_USER')")
@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController extends AbstractRestController<Cliente, ClienteService> {

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private CorreiosService correiosService;

    @Override
    protected ClienteService getService() {
        return clienteService;
    }
    
    @GetMapping("/cep/{cep}")
    @PreAuthorize("hasAuthority('STANDARD_USER') or hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> getCep(@PathVariable String cep){
        return ResponseEntity.ok(correiosService.buscarCEP(cep));
    }
 
}
