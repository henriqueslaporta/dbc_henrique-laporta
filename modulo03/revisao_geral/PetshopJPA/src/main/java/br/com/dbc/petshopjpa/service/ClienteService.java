/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.service;

import br.com.dbc.petshopjpa.dao.ClienteDAO;
import br.com.dbc.petshopjpa.entity.Cliente;

/**
 * @author henrique.laporta
 */
public class ClienteService extends AbstractCrudService< Cliente, Long, ClienteDAO > {

    private final ClienteDAO clienteDAO;
    
    {
        clienteDAO = new ClienteDAO();
    }
    
    @Override
    protected ClienteDAO getDao() {
        return clienteDAO;
    }
    
}
