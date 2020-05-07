/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.LocadoraApplicationTests;
import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.AluguelFrontDTO;
import br.com.dbc.locadora.dto.FilmeDTO;
import br.com.dbc.locadora.dto.MidiaDTO;
import br.com.dbc.locadora.entity.Aluguel;
import br.com.dbc.locadora.entity.Cliente;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.entity.Midia;
import br.com.dbc.locadora.entity.ValorMidia;
import br.com.dbc.locadora.repository.AluguelRepository;
import br.com.dbc.locadora.repository.ClienteRepository;
import br.com.dbc.locadora.repository.FilmeRepository;
import br.com.dbc.locadora.repository.MidiaRepository;
import br.com.dbc.locadora.repository.ValorMidiaRepository;
import br.com.dbc.locadora.service.FilmeService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.nullValue;
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
 * @author Camila e Henrique
 */
public class AluguelRestControllerTest extends LocadoraApplicationTests {

    private final double DELTA = 0.01d;
    
    @Autowired
    private AluguelRestController aluguelRestController;

    @Autowired
    private AluguelRepository aluguelRepository;
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private MidiaRepository midiaRepository;
    
    @Autowired
    private ValorMidiaRepository valorMidiaRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected AbstractRestController getController() {
        return aluguelRestController;
    }

    @Before
    public void beforeTest() {
        valorMidiaRepository.deleteAll();
        midiaRepository.deleteAll();
        filmeRepository.deleteAll();
        aluguelRepository.deleteAll();
        clienteRepository.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
    
    public void criarCliente(){
        Cliente cliente = Cliente.builder().nome("Henrique").endereco("Rua 2").telefone("51987654321").build();
        clienteRepository.save(cliente);
    }
    
    public void criarFilmeComUmaMidiaDeCadaTipo(){
        filmeService.salvarComMidia(FilmeDTO.builder()
                            .titulo("João e o Feijão")
                            .lancamento(LocalDate.of(2018,10,10))
                            .categoria(Categoria.AVENTURA)
                            .midia(criarMidiaDTOComUmaDeCada())
                        .build());
    }
    
    public List<MidiaDTO> criarMidiaDTOComUmaDeCada(){
        List<MidiaDTO> midiasDTO = Arrays.asList(
                                    MidiaDTO.builder().tipo(TipoMidia.VHS).quantidade(1).valor(1.9d).build(),
                                    MidiaDTO.builder().tipo(TipoMidia.DVD).quantidade(1).valor(1.9d).build(),
                                    MidiaDTO.builder().tipo(TipoMidia.BLUE_RAY).quantidade(1).valor(1.9d).build()                                    
                                );
        return midiasDTO;
    }
    
    @Test
    public void aluguelRetiradaTest() throws Exception{
        criarCliente();
        criarFilmeComUmaMidiaDeCadaTipo();
        
        List<Cliente> clientes = clienteRepository.findAll();
        List<Midia> midias = midiaRepository.findAll();
        
        AluguelFrontDTO retirada = AluguelFrontDTO.builder()
                                                        .idCliente(clientes.get(0).getId())
                                                        .midias( Arrays.asList(midias.get(0).getId()) )
                                                    .build();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/aluguel/retirada")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(retirada)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.retirada")
                        .value(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.previsao")
                        .value(LocalDate.now().plusDays(retirada.getMidias().size()).atStartOfDay().plusHours(16)
                                .format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.devolucao").value(nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(1.9d));
                
        List<Aluguel> alugueis = aluguelRepository.findAll();
        Assert.assertEquals(LocalDateTime.now().format(ofPattern("dd/MM/yyyy  HH:mm"))
                                                ,alugueis.get(0).getRetirada().format(ofPattern("dd/MM/yyyy  HH:mm")));
        Assert.assertEquals(LocalDate.now().plusDays(1).atStartOfDay().plusHours(16).format(ofPattern("dd/MM/yyyy HH:mm:ss"))
                                ,alugueis.get(0).getPrevisao().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
        Assert.assertNull(alugueis.get(0).getDevolucao());
        Assert.assertEquals(0.0d, alugueis.get(0).getMulta(), DELTA);
        Assert.assertEquals(clientes.get(0), alugueis.get(0).getIdCliente());
        
        midias = midiaRepository.findAll();
        
        Assert.assertEquals(midias.get(0).getIdAluguel(), alugueis.get(0));
        Assert.assertNull(midias.get(1).getIdAluguel());
        
    }
    
    @Test
    public void aluguelDevolucaoSemMultaTest() throws Exception{
        criarCliente();
        criarFilmeComUmaMidiaDeCadaTipo();
        
        List<Cliente> clientes = clienteRepository.findAll();
        List<Midia> midias = midiaRepository.findAll();
        
        AluguelFrontDTO retirada = AluguelFrontDTO.builder()
                                                        .idCliente(clientes.get(0).getId())
                                                        .midias( Arrays.asList(midias.get(0).getId()) )
                                                    .build();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/aluguel/retirada")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(retirada)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        AluguelFrontDTO devolucao = AluguelFrontDTO.builder()
                                                        .midias( Arrays.asList(midias.get(0).getId()) )
                                                    .build();
        
        List<Aluguel> alugueis = aluguelRepository.findAll();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/aluguel/devolucao")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(devolucao)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(alugueis.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.retirada").value(alugueis.get(0).getRetirada().format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.previsao").value(alugueis.get(0).getPrevisao().format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.devolucao")
                        .value(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.multa").value(0d));
        
        alugueis = aluguelRepository.findAll();
        
        Assert.assertEquals(LocalDateTime.now().format(ofPattern("dd/MM/yyyy  HH:mm:ss"))
                                                ,alugueis.get(0).getDevolucao().format(ofPattern("dd/MM/yyyy  HH:mm:ss")));

        Assert.assertEquals(0.0d, alugueis.get(0).getMulta(), DELTA);
        Assert.assertEquals(clientes.get(0), alugueis.get(0).getIdCliente());
        
        midias = midiaRepository.findAll();
        
        Assert.assertNull(midias.get(0).getIdAluguel());
        Assert.assertNull(midias.get(1).getIdAluguel());
    }
    
    @Test
    public void aluguelDevolucaoComMultaTest() throws Exception{
        Cliente cliente = clienteRepository.save(Cliente.builder().nome("Henrique").endereco("Rua 2").telefone("51987654321").build());
        
        Filme filme = filmeRepository.save(Filme.builder()
                                        .titulo("João e o Feijão")
                                        .lancamento(LocalDate.of(2018,10,10))
                                        .categoria(Categoria.AVENTURA)
                                    .build());
        
        Aluguel aluguel = aluguelRepository.save(Aluguel.builder()
                                                            .retirada(LocalDate.of(2018,10,30).atTime(10, 0))
                                                            .previsao(LocalDate.of(2018,10,31).atTime(16, 0))
                                                            .idCliente(cliente)
                                                        .build());
        
        Midia midia = midiaRepository.save(Midia.builder().tipo(TipoMidia.VHS).idFilme(filme).idAluguel(aluguel).build());
        
        ValorMidia valor = valorMidiaRepository.save(ValorMidia.builder()
                                                                .valor(1.9d)
                                                                .inicioVigencia(LocalDate.of(2018,10,30).atTime(6, 0))
                                                                .midia(midia)
                                                            .build());
        
        List<Cliente> clientes = clienteRepository.findAll();
        List<Midia> midias = midiaRepository.findAll();
        
        AluguelFrontDTO devolucao = AluguelFrontDTO.builder()
                                                        .midias( Arrays.asList(midias.get(0).getId()) )
                                                    .build();
        
        List<Aluguel> alugueis = aluguelRepository.findAll();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/aluguel/devolucao")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(devolucao)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(alugueis.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.retirada").value(alugueis.get(0).getRetirada().format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.previsao").value(alugueis.get(0).getPrevisao().format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.devolucao")
                        .value(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(valor.getValor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.multa").value(valor.getValor()));
        
    }

}
