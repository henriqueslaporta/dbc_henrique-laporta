/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshophibernate.dao;

import br.com.dbc.petshophibernate.entity.Cliente;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author henrique.laporta
 */
public class ClienteDAO extends AbstractDAO< Cliente , Long >{
    
    private static final ClienteDAO instance;
    
    static{
        instance = new ClienteDAO();
    }
    
    public static ClienteDAO getInstance(){
        return instance;
    }
    
    @Override
    protected Class<Cliente> getEntityClass() {
        return Cliente.class;
    }

    @Override
    protected String getIdProperty() {
        return "id";
    }

}
