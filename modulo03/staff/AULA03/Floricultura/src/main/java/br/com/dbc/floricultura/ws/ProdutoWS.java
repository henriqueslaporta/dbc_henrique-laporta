/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.floricultura.ws;

import br.com.dbc.floricultura.bean.ProdutoFacade;
import br.com.dbc.floricultura.entity.Produto;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tiago
 */
@Stateless()
@WebService(serviceName = "ProdutoWS")
public class ProdutoWS {

    @EJB
    private ProdutoFacade ejbRef;

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Produto entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Produto entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "find")
    public Produto find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Produto> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Produto> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Produto entity) {
        ejbRef.remove(entity);
    }

    public void setEjbRef(ProdutoFacade ejbRef) {
        this.ejbRef = ejbRef;
    }
    
    
    
}
