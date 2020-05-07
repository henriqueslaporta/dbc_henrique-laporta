/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.entity.Cliente;
import br.com.dbc.locadora.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author henrique.laporta
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController extends AbstractRestController<Cliente, ClienteService> {

    @Autowired
    private ClienteService clienteService;

    @Override
    protected ClienteService getService() {
        return clienteService;
    }
    
    @GetMapping("/find/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable String nome){
        return getService().findByNome(nome).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
