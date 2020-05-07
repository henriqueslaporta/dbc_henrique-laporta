/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.dia22.service;

import br.com.dbc.dia22.entity.Pais;
import br.com.dbc.dia22.entity.PersistenceUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author henrique.laporta
 */
public class PaisService {
    private static EntityManager em;
    private static PaisService instance;
    
    public static PaisService getInstance(){
        return instance == null ? new PaisService() : instance;
    }

    private PaisService(){}
 
    public void persist(Pais pais) {
        EntityManager em = PersistenceUtils.getEm();
        try {
            em.getTransaction().begin();
            em.persist(pais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    
    public List<Pais> buscarPaisPorNome(String nome){

        return PersistenceUtils.getEm()
                .createQuery("SELECT p FROM Pais p WHERE p.nome like :nome", Pais.class)
                .setParameter("nome", nome)
                .getResultList();
    }
    
    public List<Pais> buscarPaisPorSigla(String sigla){
        sigla = StringUtils.upperCase(sigla);

        return PersistenceUtils.getEm()
                .createQuery("SELECT p FROM Pais p WHERE p.sigla like :sigla", Pais.class)
                .setParameter("sigla", sigla)
                .getResultList();
    }
}

