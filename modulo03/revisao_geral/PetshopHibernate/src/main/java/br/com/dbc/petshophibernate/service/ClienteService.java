/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshophibernate.service;

import br.com.dbc.petshophibernate.dao.ClienteDAO;
import br.com.dbc.petshophibernate.entity.Cliente;
import java.util.List;
import javassist.NotFoundException;

/**
 *
 * @author henrique.laporta
 */
public class ClienteService extends AbstractCRUDService< Cliente, Long, ClienteDAO > {
    
    private static ClienteService instance;
    
    static{
        instance = new ClienteService();
    }
    
    public static ClienteService getInstance(){
        return instance;
    }
    
    @Override
    protected ClienteDAO getDao() {
        return ClienteDAO.getInstance();
    }
   
}
