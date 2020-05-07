/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.ws;

import br.com.dbc.minhafloricultura.dao.VendaDAO;
import br.com.dbc.minhafloricultura.entity.Venda;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author henrique.laporta
 */
@Stateless
@WebService(serviceName = "VendaWS")
public class VendaWS extends AbstractCrudWS < VendaDAO, Venda > {
    @EJB
    private VendaDAO vendaDAO;    

    @Override
    @WebMethod(exclude = true)
    public VendaDAO getDAO() {
        return vendaDAO;
    }
}
