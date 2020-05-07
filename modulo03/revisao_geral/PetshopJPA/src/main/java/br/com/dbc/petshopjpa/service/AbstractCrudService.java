/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.service;

import br.com.dbc.petshopjpa.dao.AbstractDAO;
import br.com.dbc.petshopjpa.entity.AbstractEntity;
import java.util.List;

/**
 *
 * @author henrique.laporta
 */
public abstract class AbstractCrudService < ENTITY extends AbstractEntity< ID >, ID, DAO extends AbstractDAO< ENTITY, ID > > {
    
    protected abstract DAO getDao();
    
    public List<ENTITY> findAll(){
        return getDao().findAll();
    }
    
    public ENTITY findOne(ID id){
        return getDao().findOne(id);
    }
    
    public void create(ENTITY entity){
        if(entity.getId() != null){
            throw new IllegalArgumentException("Criação de Cliente não pode ter ID");
        }
        getDao().createOrUpdate(entity);
    }
    
    public void update(ENTITY entity){
        if(entity.getId() == null){
            throw new IllegalArgumentException("Update de Cliente não pode ter ID NULL");
        }
        getDao().createOrUpdate(entity);
    }
    
    public void delete(ID id){
        if(id == null){
            throw new IllegalArgumentException("Deletar um Cliente não pode ter ID NULL");
        }
        getDao().delete(id);
    }
    
}
