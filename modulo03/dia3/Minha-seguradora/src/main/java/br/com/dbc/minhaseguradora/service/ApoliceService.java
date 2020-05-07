/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhaseguradora.service;

import br.com.dbc.minhaseguradora.entity.Apolice;
import br.com.dbc.minhaseguradora.repository.ApoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author henrique.laporta
 */
@Service
public class ApoliceService extends AbstractCrudService<Apolice> {
    
    @Autowired
    private ApoliceRepository apoliceRepository;

    @Override
    protected JpaRepository<Apolice, Long> getRepository() {
        return apoliceRepository;
    }
    
}
