/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.LocadoraApplicationTests;
import br.com.dbc.locadora.entity.Cliente;
import br.com.dbc.locadora.repository.ClienteRepository;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author henrique.laporta
 */
public class ClienteRestControllerTest extends LocadoraApplicationTests {

    @Autowired
    private ClienteRestController clienteRestController;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected AbstractRestController getController() {
        return clienteRestController;
    }

    @Before
    public void beforeTest() {
        clienteRepository.deleteAll();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void clienteCreateTest() throws Exception {
        Cliente c = Cliente.builder().nome("nome").endereco("endereco").telefone("9999").build();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/cliente")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(c)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(c.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value(c.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value(c.getEndereco()));
        List<Cliente> clientes = clienteRepository.findAll();
        Assert.assertEquals(1, clientes.size());
        Assert.assertEquals(c.getNome(), clientes.get(0).getNome());
        Assert.assertEquals(c.getEndereco(), clientes.get(0).getEndereco());
        Assert.assertEquals(c.getTelefone(), clientes.get(0).getTelefone());
    }
    
    @Test
    public void clienteUpdateTest() throws Exception {
        Cliente c = Cliente.builder().nome("nome").endereco("endereco").telefone("9999").build();
        c = clienteRepository.save(c);
        
        c.setNome("novo");
        c.setEndereco("Rua");
        c.setTelefone("0000");
        
        String url = "/api/cliente/" + c.getId();
        restMockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(c)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(c.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value(c.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value(c.getEndereco()));
        List<Cliente> clientes = clienteRepository.findAll();
        Assert.assertEquals(1, clientes.size());
        Assert.assertEquals(c.getNome(), clientes.get(0).getNome());
        Assert.assertEquals(c.getEndereco(), clientes.get(0).getEndereco());
        Assert.assertEquals(c.getTelefone(), clientes.get(0).getTelefone());
    }
    
    @Test
    public void clienteFindAllTest() throws Exception {
        Cliente c1 = clienteRepository.save(Cliente.builder().nome("nome").endereco("endereco").telefone("9999").build());
        Cliente c2 = clienteRepository.save(Cliente.builder().nome("novo").endereco("rua").telefone("0000").build());

        restMockMvc.perform(MockMvcRequestBuilders.get("/api/cliente?page=0&size=10&sort=id,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].nome").value(c1.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].telefone").value(c1.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].endereco").value(c1.getEndereco()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].nome").value(c2.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].telefone").value(c2.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].endereco").value(c2.getEndereco()));
    }
    
    @Test
    public void clienteFindByIdTest() throws Exception {
        Cliente c1 = clienteRepository.save(Cliente.builder().nome("nome").endereco("endereco").telefone("9999").build());
        
        String url = "/api/cliente/" + c1.getId();
        restMockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(c1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(c1.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value(c1.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value(c1.getEndereco()));
    }
}
