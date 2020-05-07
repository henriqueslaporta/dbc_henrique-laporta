/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.floricultura.ws;

import br.com.dbc.floricultura.bean.ProdutoFacade;
import br.com.dbc.floricultura.entity.Produto;
import br.com.dbc.floricultura.type.UnidadeMedidaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author tiago
 */
public class ProdutoWSTest {

    private ProdutoWS produtoWS;

    private final Produto produto = new Produto(1l, "teste", UnidadeMedidaType.UN, BigDecimal.TEN);

    public ProdutoWSTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        produtoWS = new ProdutoWS();
        ProdutoFacade produtoFacade = Mockito.mock(ProdutoFacade.class);
        produtoWS.setEjbRef(produtoFacade);
        List<Produto> produtos = new ArrayList<Produto>();
        produtos.add(produto);
        Mockito.when(produtoFacade.findAll()).thenReturn(produtos);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class ProdutoWS.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        List<Produto> result = produtoWS.findAll();
        assertThat(result, hasItem(produto));
    }

}
