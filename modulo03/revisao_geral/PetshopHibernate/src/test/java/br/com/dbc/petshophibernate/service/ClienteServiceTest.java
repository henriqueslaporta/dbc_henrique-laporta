/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshophibernate.service;

import br.com.dbc.petshophibernate.dao.ClienteDAO;
import br.com.dbc.petshophibernate.dao.HibernateUtil;
import br.com.dbc.petshophibernate.entity.Animal;
import br.com.dbc.petshophibernate.entity.Cliente;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javassist.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author henrique.laporta
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        Cliente cliente1 = Cliente.builder()
                                .nome("Cliente 1")
                                .animalList(
                                        Arrays.asList(Animal.builder()
                                                    .nome("Animal 1")
                                                    .build()))
                                .build();
        
        ClienteService.getInstance().create(cliente1);
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> clientes = session.createCriteria(Cliente.class).list();
        
        Assert.assertEquals("Quantidade de clientes errada", 1, clientes.size());
        
        Cliente result = clientes.stream().findAny().get();
        Assert.assertEquals("Cliente diferente", cliente1.getId(), result.getId());
        
        Assert.assertEquals("Quantidade de animais diferente",
                cliente1.getAnimalList().size(),
                result.getAnimalList().size()); 
        
        Assert.assertEquals("Animais diferentes",
                cliente1.getAnimalList().stream().findAny().get().getId(),
                result.getAnimalList().stream().findAny().get().getId());
        
        session.close();
    }

    @Test
    public void testCreateMocked(){
        Cliente cliente1 = Cliente.builder()
                        .nome("Cliente 1")
                        .animalList(
                                Arrays.asList(Animal.builder()
                                            .nome("Animal 1")
                                            .build()))
                        .build();
        
        // Cria uma classe ClienteDAO mockada (evitando executar as ações no banco)
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        
        // Cria a ação a ser executada quando tentarem utilziar o método createOrUpdate()
        Mockito.doNothing().when(daoMock).createOrUpdate(cliente1);
        
        // Spy - Permite que a classe utilize os método REAL implmentado
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        // retorna o dao mokado criado anteriormente, quando for chamando o método getDao()
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
        
        // Executa o mnétodo de adicionar a classe
        clienteService.create(cliente1);
        
        // Usa o verify para verificar quantas vezes foi chamado o daoMock ( ClienteDao )
        verify(daoMock, times(1)).createOrUpdate(cliente1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateException() {
        System.out.println("Create exception");
        ClienteService.getInstance()
                .create(
                        Cliente.builder().id(1l).build()
                );
    }
    
    @Test
    public void testUpdateMocked(){
        Cliente cliente1 = Cliente.builder()
                        .nome("Cliente 1")
                        .id(1l)
                        .animalList(
                                Arrays.asList(Animal.builder()
                                            .nome("Animal 1")
                                            .build()))
                        .build();
        
        // Cria uma classe ClienteDAO mockada (evitando executar as ações no banco)
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        
        // Cria a ação a ser executada quando tentarem utilziar o método createOrUpdate()
        Mockito.doNothing().when(daoMock).createOrUpdate(cliente1);
        
        // Spy - Permite que a classe utilize os método REAL implmentado
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        // retorna o dao mokado criado anteriormente, quando for chamando o método getDao()
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
        
        // Executa o mnétodo de atualizar a classe
        clienteService.update(cliente1);
        
        // Usa o verify para verificar quantas vezes foi chamado o daoMock ( ClienteDao )
        verify(daoMock, times(1)).createOrUpdate(cliente1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateException() {
        System.out.println("Update exception");
        ClienteService.getInstance()
                .update(
                        Cliente.builder().build()
                );
    }
    
    @Test(expected = HibernateException.class)
    public void testUpdateHibernateExecpitionMocked(){
        Cliente cliente1 = Cliente.builder()
                        .nome("Cliente 1")
                        .id(1l)
                        .animalList(
                                Arrays.asList(Animal.builder()
                                            .nome("Animal 1")
                                            .build()))
                        .build();
        
        // Cria uma classe ClienteDAO mockada (evitando executar as ações no banco)
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        
        // Cria a ação a ser executada quando tentarem utilziar o método createOrUpdate()
        Mockito.doThrow(new HibernateException("")).when(daoMock).createOrUpdate(cliente1);
        
        // Spy - Permite que a classe utilize os método REAL implmentado
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        // retorna o dao mokado criado anteriormente, quando for chamando o método getDao()
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
        
        // Executa o mnétodo de atualizar a classe
        clienteService.update(cliente1);
    }
    
    @Test
    public void testDeleteMocked() throws NotFoundException{
        Cliente cliente1 = Cliente.builder()
                        .nome("Cliente 1")
                        .id(1l)
                        .animalList(
                                Arrays.asList(Animal.builder()
                                            .nome("Animal 1")
                                            .build()))
                        .build();
        
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        Mockito.doNothing().when(daoMock).delete(cliente1);
        
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
        
        Mockito.when(clienteService.findOne(cliente1.getId())).thenReturn(cliente1);

        clienteService.delete(cliente1.getId());
        
        verify(daoMock, times(1)).findById(cliente1.getId());
        verify(daoMock, times(1)).delete(cliente1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteException() throws NotFoundException {
        System.out.println("Delete exception");
        ClienteService.getInstance()
                .delete(null);
    }
    
    @Test(expected = NotFoundException.class)
    public void testDeleteExecptionMocked() throws NotFoundException{
         
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        Mockito.doReturn(null).when(clienteService).findOne(any());

        clienteService.delete(1l);
    }
    
    @Test(expected = HibernateException.class)
    public void testDeleteHibernateExecpitionMocked() throws NotFoundException{
        Cliente cliente1 = Cliente.builder()
                        .nome("Cliente 1")
                        .id(1l)
                        .animalList(
                                Arrays.asList(Animal.builder()
                                            .nome("Animal 1")
                                            .build()))
                        .build();
        
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        Mockito.doThrow(new HibernateException("")).when(daoMock).delete(cliente1);
        
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
        
        Mockito.when(clienteService.findOne(cliente1.getId())).thenReturn(cliente1);

        clienteService.delete(cliente1.getId());
    }
    
    @Test
    public void testFindOneMocked(){
        Cliente cliente1 = Cliente.builder()
                        .nome("Cliente 1")
                        .id(1l)
                        .animalList(
                                Arrays.asList(Animal.builder()
                                            .nome("Animal 1")
                                            .build()))
                        .build();
        
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        
        Mockito.doReturn(cliente1).when(daoMock).findById(cliente1.getId());
        
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
 
        clienteService.findOne(cliente1.getId());
        
        verify(daoMock, times(1)).findById(cliente1.getId());
    }
    
    @Test(expected = HibernateException.class)
    public void testFindOneHibernateExcepitionMocked(){
        
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        
        Mockito.doThrow(new HibernateException("")).when(daoMock).findById(1l);
        
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
 
        clienteService.findOne(1l);
        
    }
    
    @Test
    public void testFindAllMocked(){
        List<Cliente> listaCliente = Arrays.asList(Cliente.builder()
                        .nome("Cliente 1")
                        .id(1l)
                        .animalList(
                                Arrays.asList(Animal.builder()
                                            .nome("Animal 1")
                                            .build()))
                        .build() );
        
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        
        Mockito.doReturn(listaCliente).when(daoMock).findAll();
        
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
 
        List<Cliente> retorno = clienteService.findAll();
        
        verify(daoMock, times(1)).findAll();
        assertEquals(listaCliente.size(), retorno.size());
    }
    
    @Test(expected = HibernateException.class)
    public void testFindAllHibernateExceptionMocked(){
       
        ClienteDAO daoMock = Mockito.mock(ClienteDAO.class);
        
        Mockito.doThrow(new HibernateException("")).when(daoMock).findAll();
        
        ClienteService clienteService = Mockito.spy(ClienteService.class);
        
        Mockito.when(clienteService.getDao()).thenReturn(daoMock);
 
        clienteService.findAll();

    }

    /**
     * Test of getInstance method, of class ClienteService.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ClienteService expResult = null;
        ClienteService result = ClienteService.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDao method, of class ClienteService.
     */
    @Test
    public void testGetDao() {
        System.out.println("getDao");
        ClienteService instance = new ClienteService();
        ClienteDAO expResult = null;
        ClienteDAO result = instance.getDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
