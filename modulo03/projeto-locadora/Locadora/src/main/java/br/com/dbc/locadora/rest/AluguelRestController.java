/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.dto.AluguelBackDTO;
import br.com.dbc.locadora.dto.AluguelFrontDTO;
import br.com.dbc.locadora.entity.Aluguel;
import br.com.dbc.locadora.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author henrique.laporta
 */
@RestController
@RequestMapping("/api/aluguel")
public class AluguelRestController extends AbstractRestController<Aluguel, AluguelService> {

    @Autowired
    private AluguelService aluguelService;

    @Override
    protected AluguelService getService() {
        return aluguelService;
    }
    
    @PostMapping("/retirada")
    public ResponseEntity< AluguelBackDTO > retiradaAluguel(@RequestBody AluguelFrontDTO dto){
        
        return ResponseEntity.ok( aluguelService.retirada(dto) );
        
    }
    
    @PostMapping("/devolucao")
    public ResponseEntity< AluguelBackDTO > devolucaoAluguel(@RequestBody AluguelFrontDTO dto){
        
        return ResponseEntity.ok( aluguelService.devolucao(dto) );
        
    }
    
    @GetMapping("/devolucao")
    public ResponseEntity< Page<AluguelBackDTO> > devolucaoAluguelHoje (Pageable pageable){
        
        return ResponseEntity.ok( aluguelService.devolucaoAluguelHoje(pageable) );
        
    }
}
