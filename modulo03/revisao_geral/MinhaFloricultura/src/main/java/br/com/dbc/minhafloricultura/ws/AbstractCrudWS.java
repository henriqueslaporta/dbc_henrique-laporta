/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.ws;

import br.com.dbc.minhafloricultura.dao.AbstractDAO;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.validation.Valid;

/**
 *
 * @author henrique.laporta
 */
public abstract class AbstractCrudWS< DAO extends AbstractDAO< E > , E > {
    
    public abstract DAO getDAO();
    
    @WebMethod(operationName = "create")
    @Oneway
    public void create(@Valid @WebParam(name = "entity") E entity) {
        getDAO().create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@Valid @WebParam(name = "entity") E entity) {
        getDAO().edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") E entity) {
        getDAO().remove(entity);
    }

    @WebMethod(operationName = "find")
    public E find(@WebParam(name = "id") Long id) {
        return getDAO().find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<E> findAll() {
        return getDAO().findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<E> findRange(@WebParam(name = "range") int[] range) {
        return getDAO().findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return getDAO().count();
    }
    
}
