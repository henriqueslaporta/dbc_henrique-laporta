/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.dao;

import br.com.dbc.petshopjpa.entity.Cliente;

/**
 *
 * @author henrique.laporta
 */
public class ClienteDAO extends AbstractDAO<Cliente,Long>{

    @Override
    public Class<Cliente> getEntityClass() {
        return Cliente.class;
    }
 
}
