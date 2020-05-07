/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.dao;

import br.com.dbc.minhafloricultura.entity.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author henrique.laporta
 */
@Stateless
public class ClienteDAO extends AbstractDAO<Cliente> {

    @PersistenceContext(unitName = "minha_floricultura_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteDAO() {
        super(Cliente.class);
    }
    
}
