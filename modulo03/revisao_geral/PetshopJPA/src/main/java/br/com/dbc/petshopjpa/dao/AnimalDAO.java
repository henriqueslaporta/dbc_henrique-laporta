/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.dao;

import br.com.dbc.petshopjpa.entity.Animal;

/**
 *
 * @author henrique.laporta
 */
public class AnimalDAO extends AbstractDAO<Animal,Long> {

    @Override
    protected Class<Animal> getEntityClass() {
        return Animal.class;
    }
    
}
