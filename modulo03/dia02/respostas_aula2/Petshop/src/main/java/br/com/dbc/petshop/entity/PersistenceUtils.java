/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshop.entity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author tiago
 */
public class PersistenceUtils {

    private static EntityManager em;

    static {
        em = Persistence
                .createEntityManagerFactory("petshop")
                .createEntityManager();
    }

    public static EntityManager getEm() {
        return em;
    }

}
