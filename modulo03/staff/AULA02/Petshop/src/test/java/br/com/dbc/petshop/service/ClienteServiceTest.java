/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshop.service;

import br.com.dbc.petshop.entity.Cliente;
import br.com.dbc.petshop.entity.HibernateUtil;
import br.com.dbc.petshop.entity.SexoType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tiago
 */
public class ClienteServiceTest {
    
    public ClienteServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.createCriteria(Cliente.class).list().forEach(session::delete);
        t.commit();
    }
    
    @After
    public void tearDown() {
    }
    
    private void createClienteNome(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.merge(new Cliente(null, "nome", SexoType.M, "profissao"));
        t.commit();
    }

    /**
     * Test of findAll method, of class ClienteService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        createClienteNome();
        ClienteService instance = ClienteService.getInstance();
        List<Cliente> result = instance.findAll();
        assertEquals(1, result.size());
        assertTrue(result.get(0).getNome().equalsIgnoreCase("nome"));
    }

    /**
     * Test of findAllCriteria method, of class ClienteService.
     */
    @Test
    public void testFindAllCriteria() {
        System.out.println("findAllCriteria");
        createClienteNome();
        ClienteService instance = ClienteService.getInstance();
        List<Cliente> result = instance.findAllCriteria();
        assertEquals(1, result.size());
        assertTrue(result.get(0).getNome().equalsIgnoreCase("nome"));
    }

    /**
     * Test of create method, of class ClienteService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Cliente c = new Cliente(null, "nome", SexoType.M, "profissao");
        ClienteService instance = ClienteService.getInstance();
        Cliente result = instance.create(c);
        assertTrue(result.getId() != null);
    }

    /**
     * Test of createCriteria method, of class ClienteService.
     */
    @Test
    public void testCreateCriteria() {
        System.out.println("createCriteria");
        Cliente c = new Cliente(null, "nome", SexoType.M, "profissao");
        ClienteService instance = ClienteService.getInstance();
        Cliente result = instance.createCriteria(c);
        assertTrue(result.getId() != null);
    }
    
}
