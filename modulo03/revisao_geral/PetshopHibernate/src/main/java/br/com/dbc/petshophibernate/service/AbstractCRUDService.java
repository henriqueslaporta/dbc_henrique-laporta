/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshophibernate.service;

import br.com.dbc.petshophibernate.dao.AbstractDAO;
import br.com.dbc.petshophibernate.dao.ClienteDAO;
import br.com.dbc.petshophibernate.entity.AbstractEntity;
import br.com.dbc.petshophibernate.entity.Cliente;
import java.util.List;
import javassist.NotFoundException;

/**
 *
 * @author henrique.laporta
 */
public abstract class AbstractCRUDService< ENTITY extends AbstractEntity<ID>, 
                                           ID,
                                           DAO extends AbstractDAO<ENTITY,ID > > {
    
    protected abstract DAO getDao();
    
    public List<ENTITY> findAll(){
        return getDao().findAll();
    }
    
    public ENTITY findOne(ID id){
        return getDao().findById(id);
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
    
    public void delete(ID id) throws NotFoundException{
        if(id == null){
            throw new IllegalArgumentException("Deletar um Cliente não pode ter ID NULL");
        }
        ENTITY entity = findOne(id);
        if(entity == null){
            throw new NotFoundException("");
        }
        getDao().delete(entity);
    }
    
}
