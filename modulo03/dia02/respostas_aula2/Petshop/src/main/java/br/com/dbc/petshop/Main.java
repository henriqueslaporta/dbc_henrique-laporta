package br.com.dbc.petshop;

import br.com.dbc.petshop.entity.Cliente;
import br.com.dbc.petshop.entity.HibernateUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tiago
 */
public class Main {

    public static void main(String... args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("petshop");
        EntityManager em = factory.createEntityManager();
        List<Cliente> clientes = em
                .createQuery("select c from Cliente c where lower(c.nome) like :nome")
                .setParameter("nome", "%alessandro%").
                getResultList();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        clientes = session
                .createCriteria(Cliente.class)
                .add(Restrictions.or(Restrictions.eq("id", 1), 
                        Restrictions.eq("nome", "alessandro").ignoreCase()))
                .addOrder(Order.asc("id"))
                .list();
        
        
    }

}
