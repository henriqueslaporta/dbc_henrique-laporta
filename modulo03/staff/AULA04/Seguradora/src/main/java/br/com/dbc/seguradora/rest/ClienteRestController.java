/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.seguradora.rest;

import br.com.dbc.seguradora.entity.Cliente;
import br.com.dbc.seguradora.service.ClienteService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author tiago
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<Page<Cliente>> list(Pageable pageable) {
        return ResponseEntity.ok(clienteService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return clienteService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Cliente input) {
        return ResponseEntity.ok(clienteService.update(id, input));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Cliente input) {
        return ResponseEntity.ok(clienteService.save(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }


}
