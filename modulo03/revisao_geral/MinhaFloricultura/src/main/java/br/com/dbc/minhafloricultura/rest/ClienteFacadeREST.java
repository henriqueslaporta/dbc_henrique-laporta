/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.rest;

import br.com.dbc.minhafloricultura.dao.ClienteDAO;
import br.com.dbc.minhafloricultura.entity.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author henrique.laporta
 */
@Stateless
@Path("cliente")
public class ClienteFacadeREST extends AbstractCrudREST<Cliente,ClienteDAO> {

    @Inject
    private ClienteDAO clienteDAO;


    @Override
    protected ClienteDAO getDAO() {
        return clienteDAO;
    }
    
}
