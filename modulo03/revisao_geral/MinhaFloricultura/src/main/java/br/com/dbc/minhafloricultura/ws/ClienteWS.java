/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.ws;

import br.com.dbc.minhafloricultura.dao.ClienteDAO;
import br.com.dbc.minhafloricultura.entity.Cliente;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author henrique.laporta
 */
@Stateless
@WebService(serviceName = "ClienteWS")
public class ClienteWS extends AbstractCrudWS <ClienteDAO, Cliente>{
    @EJB
    private ClienteDAO clienteDAO;    

    @Override
    @WebMethod(exclude = true)
    public ClienteDAO getDAO() {
        return clienteDAO;
    }
}
