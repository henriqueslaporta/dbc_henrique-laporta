/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshop.entity;

import br.com.dbc.petshop.service.AnimalService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tiago
 */
public class AnimalTest {
    
    public AnimalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class Animal.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        AnimalService instance = AnimalService.getInstance();
        List<Animal> result = instance.findAll();
        assertEquals(1, result.size());
        Animal a = result.get(0);
        assertEquals("Thor", a.getNome());
        assertEquals(SexoType.M, a.getSexo());
        assertEquals("Odin", a.getIdCliente().getNome());
        
    }
    
}
