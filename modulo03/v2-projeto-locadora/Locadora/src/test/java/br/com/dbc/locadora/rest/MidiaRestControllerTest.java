/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.LocadoraApplicationTests;
import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.FilmeDTO;
import br.com.dbc.locadora.dto.MidiaDTO;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.repository.AluguelRepository;
import br.com.dbc.locadora.repository.ClienteRepository;
import br.com.dbc.locadora.repository.FilmeRepository;
import br.com.dbc.locadora.repository.MidiaRepository;
import br.com.dbc.locadora.repository.ValorMidiaRepository;
import br.com.dbc.locadora.service.DateService;
import br.com.dbc.locadora.service.FilmeService;
import java.time.LocalDate;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author Camila e Henrique
 */
public class MidiaRestControllerTest extends LocadoraApplicationTests {

    @Autowired
    private MidiaRestController midiaRestController;

    @Autowired
    private FilmeRepository filmeRepository;
    
    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private MidiaRepository midiaRepository;
    
    @Autowired
    private ValorMidiaRepository valorMidiaRepository;
    
    @Override
    protected AbstractRestController getController() {
        return midiaRestController;
    }

    
    
    @Before
    public void beforeTest() {
        valorMidiaRepository.deleteAll();
        midiaRepository.deleteAll();
        filmeRepository.deleteAll();
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
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void midiaSearchTipoTest() throws Exception{
        
        criarFilmeComUmaMidiaDeCadaTipo();
        
        Filme filme = filmeRepository.findAll().get(0);
        
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/midia/search?page=0&size=10&sort=id,desc&tipo=DVD")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].tipo").value(TipoMidia.DVD.name()))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].idFilme.titulo").value(filme.getTitulo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].idFilme.categoria").value(filme.getCategoria().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].idFilme.lancamento").value(filme.getLancamento().format(ofPattern("dd/MM/yyyy")) ));
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void midiaSearchCategoriaAndValorTest() throws Exception{
        
        criarFilmeComUmaMidiaDeCadaTipo();
        
        Filme filme = filmeRepository.findAll().get(0);
        
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/midia/search?page=0&size=10&sort=id,desc&categoria=AVENTURA&valor=1.9")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].tipo").value(TipoMidia.VHS.name()))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].idFilme.titulo").value(filme.getTitulo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].idFilme.categoria").value(filme.getCategoria().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].idFilme.lancamento").value(filme.getLancamento().format(ofPattern("dd/MM/yyyy")) ));
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void midiaCountPorTipoDVDTest() throws Exception{
        
        criarFilmeComUmaMidiaDeCadaTipo();
        
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/midia/count/DVD"))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andExpect(MockMvcResultMatchers.content().string("1"));
       
       
    }
}
