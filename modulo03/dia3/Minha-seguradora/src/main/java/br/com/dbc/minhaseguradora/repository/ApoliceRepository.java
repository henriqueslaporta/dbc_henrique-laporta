/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhaseguradora.repository;

import br.com.dbc.minhaseguradora.entity.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author henrique.laporta
 */
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {
    
}
