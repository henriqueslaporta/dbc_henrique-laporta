/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 *
 * @author tiago
 */
public class SoapConnector extends WebServiceGatewaySupport {
 
    public Object callWebService(Object request){
        return getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
