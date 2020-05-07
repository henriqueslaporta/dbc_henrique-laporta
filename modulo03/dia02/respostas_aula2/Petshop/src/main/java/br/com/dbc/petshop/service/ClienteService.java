/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshop.service;

import br.com.dbc.petshop.entity.Animal;
import br.com.dbc.petshop.entity.Cliente;
import br.com.dbc.petshop.entity.HibernateUtil;
import br.com.dbc.petshop.entity.PersistenceUtils;
import br.com.dbc.petshop.entity.SexoType;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tiago
 */
public class ClienteService {

    private static final ClienteService instance;

    static {
        instance = new ClienteService();
    }

    public static ClienteService getInstance() {
        return instance;
    }

    private ClienteService() {
    }

    public List<Cliente> findAll() {
        EntityManager em = PersistenceUtils.getEm();
        List<Cliente> animais = em.createQuery("select a from Cliente a").getResultList();
        return animais;
    }

    public List<Cliente> findAllCriteria() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createCriteria(Cliente.class).list();
    }

    public Cliente create(Cliente c) {
        EntityManager em = PersistenceUtils.getEm();
        try {
            em.getTransaction().begin();
            c = em.merge(c);
            em.getTransaction().commit();
            return c;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Cliente createCriteria(Cliente c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(c);
            t.commit();
            return c;
        } catch (Exception e) {
            t.rollback();
            throw e;
        }
    }

    
    public void createDezClientes(){
        // Criar 10 clientes e para cada um 10 animais
        for(int i = 0; i < 10; i++){
            Cliente cliente = this.create(new Cliente(null, "Cliente-"+i , SexoType.M, "profissao"));
            for(int j = 0; j < 10; j++){
                Animal animal = new Animal(null, "Animal-"+j, SexoType.M);
                animal.setIdCliente(cliente);
                cliente.getAnimalList().add(AnimalService.getInstance().create(animal));
            }
        }
    }
}
