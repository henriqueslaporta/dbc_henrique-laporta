/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.rest;

import br.com.dbc.minhafloricultura.dao.ProdutoDAO;
import br.com.dbc.minhafloricultura.entity.Produto;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author henrique.laporta
 */
@Stateless
@Path("produto")
public class ProdutoFacadeREST extends AbstractCrudREST<Produto,ProdutoDAO> {

    @Inject
    private ProdutoDAO produtoDAO;

    @Override
    protected ProdutoDAO getDAO() {
        return produtoDAO;
    }
 
    @GET
    @Path("descricao/{descricao}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("descricao") String descricao) {

        return Response.ok( getDAO().findDescricao(descricao) ).build();
    }
}
