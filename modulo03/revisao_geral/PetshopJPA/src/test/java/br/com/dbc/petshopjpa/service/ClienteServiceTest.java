/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.service;

import br.com.dbc.petshopjpa.dao.ClienteDAO;
import br.com.dbc.petshopjpa.dao.PersistenceUtils;
import br.com.dbc.petshopjpa.entity.Animal;
import br.com.dbc.petshopjpa.entity.Cliente;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.internal.util.reflection.Whitebox;

/**
 *
 * @author henrique.laporta
 */
public class ClienteServiceTest {
    
    private EntityManager em = PersistenceUtils.getEntityManager();

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
        em.getTransaction().begin();
        em.createQuery("delete from Cliente");
        em.getTransaction().commit();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindAll(){
        System.out.println("findAll()");
        
        Cliente c = Cliente.builder()
                            .nome("John")
                            .animalList(Arrays.asList(Animal
                                    .builder()
                                    .nome("Snow")
                                    .build() ))
                            .build();
        
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        
        ClienteService instance = new ClienteService();
        List<Cliente> clientes = instance.findAll();
        
        Assert.assertEquals( 1, clientes.size() );
        
        Assert.assertEquals( c.getId(), 
                        clientes.stream().findFirst().get().getId() );
        
        Assert.assertEquals( c.getAnimalList().size(), 
                        clientes.stream().findFirst().get().getAnimalList().size());
        
        Assert.assertEquals( c.getAnimalList().get(0).getId(), 
                        clientes.stream().findFirst().get().getAnimalList().get(0).getId());
                            
    }
    
    @Test
    public void testFindOneMocked(){
        ClienteService clienteService = new ClienteService();
        ClienteDAO clienteDAO = Mockito.mock(ClienteDAO.class);
        // insere um valor fixo na variavel clienteDAO
        Whitebox.setInternalState(clienteService, "clienteDAO", clienteDAO);
        
        Cliente cliente1 = Cliente.builder().id(1l).nome("John").animalList(Arrays.asList(Animal.builder().id(1l).nome("S").build())).build();
        
        Mockito.doReturn(cliente1).when(clienteDAO).findOne(cliente1.getId());
        
        Cliente retorno = clienteService.findOne(cliente1.getId());
        
        Assert.assertEquals(cliente1.getId(), retorno.getId());
        
        verify(clienteDAO, times(1)).findOne(cliente1.getId());
    }
    
    @Test
    public void testDeleteMocked(){
        ClienteService clienteService = new ClienteService();
        ClienteDAO clienteDAO = Mockito.mock(ClienteDAO.class);
        
        // insere um valor fixo na variavel clienteDAO
        Whitebox.setInternalState(clienteService, "clienteDAO", clienteDAO);
        
        Cliente cliente1 = Cliente.builder().id(1l).nome("John").animalList(Arrays.asList(Animal.builder().id(1l).nome("S").build())).build();
        
        Mockito.doNothing().when(clienteDAO).delete(cliente1.getId());
        
        clienteService.delete(cliente1.getId());

        verify(clienteDAO, times(1)).delete(cliente1.getId());
    }
    
}
