/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshophibernate.service;

import br.com.dbc.petshophibernate.dao.AnimalDAO;
import br.com.dbc.petshophibernate.entity.Animal;

/**
 *
 * @author henrique.laporta
 */
public class AnimalService extends AbstractCRUDService< Animal, Long, AnimalDAO > {
        private static AnimalService instance;
    
    static{
        instance = new AnimalService();
    }
    
    public static AnimalService getInstance(){
        return instance;
    }
    
    @Override
    protected AnimalDAO getDao() {
        return AnimalDAO.getInstance();
    }
}
