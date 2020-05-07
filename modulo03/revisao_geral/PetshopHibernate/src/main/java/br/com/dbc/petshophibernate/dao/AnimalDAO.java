/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshophibernate.dao;

import br.com.dbc.petshophibernate.entity.Animal;

/**
 *
 * @author henrique.laporta
 */
public class AnimalDAO extends  AbstractDAO< Animal , Long >{
    
    private static final AnimalDAO instance;
    
    static{
        instance = new AnimalDAO();
    }
    
    public static AnimalDAO getInstance(){
        return instance;
    }
    
    @Override
    protected Class<Animal> getEntityClass() {
        return Animal.class;
    }

    @Override
    protected String getIdProperty() {
        return "id";
    }

}