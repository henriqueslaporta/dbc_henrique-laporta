/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.ws;

import br.com.dbc.minhafloricultura.dao.ProdutoDAO;
import br.com.dbc.minhafloricultura.entity.Produto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author henrique.laporta
 */
@Stateless
@WebService(serviceName = "ProdutoWS")
public class ProdutoWS extends AbstractCrudWS < ProdutoDAO, Produto >{

    @EJB
    private ProdutoDAO produtoDAO;    

    @Override
    @WebMethod(exclude = true)
    public ProdutoDAO getDAO() {
        return produtoDAO;
    }
   
    @WebMethod(operationName = "findDescricao")
    public List<Produto> findDescricao(String descricao) {
        return getDAO().findDescricao(descricao);
    }
    
}
