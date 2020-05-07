/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshop.service;

import br.com.dbc.petshop.entity.Animal;
import br.com.dbc.petshop.entity.HibernateUtil;
import br.com.dbc.petshop.entity.PersistenceUtils;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;

/**
 *
 * @author tiago
 */
public class AnimalService {

    private static final AnimalService instance;

    static {
        instance = new AnimalService();
    }

    public static AnimalService getInstance() {
        return instance;
    }

    private AnimalService() {
    }
    
    public List<Animal> findAll(){
        EntityManager em = PersistenceUtils.getEm();
        List<Animal> animais = em.createQuery("select a from Animal a").getResultList();
        return animais;
    }
    
    public List<Animal> findAllCriteria(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createCriteria(Animal.class).list();
    }
    
}
