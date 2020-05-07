/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.LocadoraApplicationTests;
import br.com.dbc.locadora.dto.CepDTO;
import br.com.dbc.locadora.entity.Cliente;
import br.com.dbc.locadora.repository.ClienteRepository;
import br.com.dbc.locadora.service.CorreiosService;
import br.com.dbc.locadora.service.DateService;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void clienteCreateTest() throws Exception {
        Cliente c = Cliente.builder().nome("nome").rua("rua").bairro("bairro").cidade("cidade").estado("RS").telefone("9999").build();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/cliente")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(c)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(c.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value(c.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value(c.getRua()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value(c.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value(c.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value(c.getEstado()));
        List<Cliente> clientes = clienteRepository.findAll();
        Assert.assertEquals(1, clientes.size());
        Assert.assertEquals(c.getNome(), clientes.get(0).getNome());
        Assert.assertEquals(c.getRua(), clientes.get(0).getRua());
        Assert.assertEquals(c.getBairro(), clientes.get(0).getBairro());
        Assert.assertEquals(c.getCidade(), clientes.get(0).getCidade());
        Assert.assertEquals(c.getEstado(), clientes.get(0).getEstado());
        Assert.assertEquals(c.getTelefone(), clientes.get(0).getTelefone());
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void clienteUpdateTest() throws Exception {
        Cliente c = Cliente.builder().nome("nome").rua("rua").bairro("bairro").cidade("cidade").estado("RS").telefone("9999").build();
        c = clienteRepository.save(c);
        
        c.setNome("novo");
        c.setRua("NovaRua");
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value(c.getRua()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value(c.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value(c.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value(c.getEstado()));
        
        List<Cliente> clientes = clienteRepository.findAll();
        Assert.assertEquals(1, clientes.size());
        Assert.assertEquals(c.getNome(), clientes.get(0).getNome());
        Assert.assertEquals(c.getRua(), clientes.get(0).getRua());
        Assert.assertEquals(c.getBairro(), clientes.get(0).getBairro());
        Assert.assertEquals(c.getCidade(), clientes.get(0).getCidade());
        Assert.assertEquals(c.getEstado(), clientes.get(0).getEstado());
        Assert.assertEquals(c.getTelefone(), clientes.get(0).getTelefone());
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void clienteFindAllTest() throws Exception {
        Cliente c1 = clienteRepository.save(Cliente.builder().nome("nome").rua("rua").bairro("bairro").cidade("cidade").estado("RS").telefone("9999").build());
        Cliente c2 = clienteRepository.save(Cliente.builder().nome("novo").rua("Nrua").bairro("Nbairro").cidade("Ncidade").estado("nRS").telefone("0000").build());

        restMockMvc.perform(MockMvcRequestBuilders.get("/api/cliente?page=0&size=10&sort=id,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].nome").value(c1.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].telefone").value(c1.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].rua").value(c1.getRua()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].bairro").value(c1.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].cidade").value(c1.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].estado").value(c1.getEstado()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].nome").value(c2.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].telefone").value(c2.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].rua").value(c2.getRua()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].bairro").value(c2.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].cidade").value(c2.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].estado").value(c2.getEstado()));
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void clienteFindByIdTest() throws Exception {
        Cliente c1 = clienteRepository.save(Cliente.builder().nome("nome").rua("rua").bairro("bairro").cidade("cidade").estado("RS").telefone("9999").build());
        
        String url = "/api/cliente/" + c1.getId();
        restMockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(c1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(c1.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value(c1.getTelefone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value(c1.getRua()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value(c1.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value(c1.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value(c1.getEstado()));
    }
    
    
    @MockBean
    private CorreiosService correiosService;
    
    @Test
    @WithMockUser(username="john.doe", 
            password = "jwtpass", 
            authorities = {"STANDARD_USER"})
    public void clienteGetCepTest() throws Exception {
        CepDTO cepInfo = CepDTO.builder().rua("Rua Ary Tarrago")
                                        .bairro("Jardim Itu")
                                        .cidade("Porto Alegre")
                                        .estado("RS")
                                .build();
        
        Mockito.when(correiosService.buscarCEP(ArgumentMatchers.anyString())).thenReturn(cepInfo);
        
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/cliente/cep/91225002"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value(cepInfo.getRua()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value(cepInfo.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value(cepInfo.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value(cepInfo.getEstado()));
    }
    
}
