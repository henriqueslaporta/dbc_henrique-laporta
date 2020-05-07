/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.dia22.service;

import br.com.dbc.dia22.entity.Pais;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henrique.laporta
 */
public class PaisServiceTest {
    
    public PaisServiceTest() {
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
     * Test of buscarPaisPorNome method, of class PaisService.
     */
    @Test
    public void testBuscarPaisPorNome() {
        System.out.println("buscarPaisPorNome");
        String nome = "Brasil";
        PaisService instance = PaisService.getInstance();
        String expResult = "Brasil";
        List<Pais> result = instance.buscarPaisPorNome(nome);

        assertEquals(expResult, result.get(0).getNome());
    }
    
    @Test
    public void testBuscarPaisPorSigla() {
        System.out.println("buscarPaisPorSigla");
        String nome = "BR";
        PaisService instance = PaisService.getInstance();
        
        Pais expResult = new Pais(new Long(1),"Brasil","BR");
        
        List<Pais> result = instance.buscarPaisPorSigla(nome);
        assertTrue( result.contains(expResult) );  
    }
    
    @Test
    public void testBuscarPaisPorSiglaQueTemB() {
        System.out.println("buscarPaisPorSiglaB");
        String nome = "%B%";
        PaisService instance = PaisService.getInstance();

        Pais expResult = new Pais(new Long(1),"Brasil","BR");
        
        List<Pais> result = instance.buscarPaisPorSigla(nome);
        assertTrue( result.contains(expResult) );  
        
    }
    
}
