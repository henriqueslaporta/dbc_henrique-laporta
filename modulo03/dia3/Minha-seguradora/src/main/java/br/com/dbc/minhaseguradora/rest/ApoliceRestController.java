/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhaseguradora.rest;

import br.com.dbc.minhaseguradora.entity.Apolice;
import br.com.dbc.minhaseguradora.service.ApoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author henrique.laporta
 */
@RestController
@RequestMapping("/api/apolice")
public class ApoliceRestController extends AbstractRestController<Apolice, ApoliceService> {

    @Autowired
    private ApoliceService apoliceService;

    @Override
    protected ApoliceService getService() {
        return apoliceService;
    }
    
}
