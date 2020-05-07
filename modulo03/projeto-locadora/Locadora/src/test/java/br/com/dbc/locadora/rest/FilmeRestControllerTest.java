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
import br.com.dbc.locadora.entity.Midia;
import br.com.dbc.locadora.entity.ValorMidia;
import br.com.dbc.locadora.repository.FilmeRepository;
import br.com.dbc.locadora.repository.MidiaRepository;
import br.com.dbc.locadora.repository.ValorMidiaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.util.CollectionUtils.containsAny;

/**
 *
 * @author henrique.laporta
 */
public class FilmeRestControllerTest extends LocadoraApplicationTests {

    @Autowired
    private FilmeRestController filmeRestController;

    @Autowired
    private FilmeRepository filmeRepository;
    
    @Autowired
    private MidiaRepository midiaRepository;
    
    @Autowired
    private ValorMidiaRepository valorMidiaRepository;

    @Override
    protected AbstractRestController getController() {
        return filmeRestController;
    }

    @Before
    public void beforeTest() {
        valorMidiaRepository.deleteAll();
        midiaRepository.deleteAll();
        filmeRepository.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
    
    public FilmeDTO criarFilmeDTOComUmaMidiaDeCada(){
        return FilmeDTO.builder()
                            .titulo("João e o Feijão")
                            .lancamento(LocalDate.of(2018,10,10))
                            .categoria(Categoria.AVENTURA)
                            .midia(criarMidiaDTOComUmaDeCada())
                        .build();
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
    public void filmeCreateTest() throws Exception{

        FilmeDTO filme = criarFilmeDTOComUmaMidiaDeCada();
        
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filme)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value(filme.getTitulo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria").value(filme.getCategoria().name()));
        
        List<Filme> filmes = filmeRepository.findAll();
        Assert.assertEquals(1, filmes.size());
        Assert.assertEquals(filme.getTitulo(), filmes.get(0).getTitulo());
        Assert.assertEquals(filme.getLancamento(), filmes.get(0).getLancamento());
        Assert.assertEquals(filme.getCategoria(), filmes.get(0).getCategoria());
        
        List<Midia> midias = midiaRepository.findAll();
        Assert.assertEquals(3, midias.size());
        Assert.assertEquals(midiasDTO.get(0).getTipo(), midias.get(0).getTipo());
        Assert.assertEquals(filmes.get(0), midias.get(0).getIdFilme());
        
        List<ValorMidia> valorMidias = valorMidiaRepository.findAll();
        Assert.assertEquals(3, valorMidias.size());
        Assert.assertEquals(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss")), valorMidias.get(0).getInicioVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
        Assert.assertNull(valorMidias.get(0).getFinalVigencia());
        Assert.assertEquals(midias.get(0), valorMidias.get(0).getMidia());
        
    }
    
    @Test
    public void filmeUpdateSomenteValorTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)));

        Filme filmeSave = filmeRepository.findAll().get(0);
        
        filmeDTO.setId(filmeSave.getId());
        filmeDTO.getMidia().forEach( midiaDTO -> { midiaDTO.setValor(9.0d); });
        
        restMockMvc.perform(MockMvcRequestBuilders.put("/api/filme/"+filmeDTO.getId()+"/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value(filmeDTO.getTitulo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria").value(filmeDTO.getCategoria().name()));
        
        List<Filme> filmes = filmeRepository.findAll();
        Assert.assertEquals(1, filmes.size());
        Assert.assertEquals(filmeDTO.getTitulo(), filmes.get(0).getTitulo());
        Assert.assertEquals(filmeDTO.getLancamento(), filmes.get(0).getLancamento());
        Assert.assertEquals(filmeDTO.getCategoria(), filmes.get(0).getCategoria());
        
        List<Midia> midias = midiaRepository.findAll();
        Assert.assertEquals(3, midias.size());
        Assert.assertEquals(midiasDTO.get(0).getTipo(), midias.get(0).getTipo());
        Assert.assertEquals(filmes.get(0), midias.get(0).getIdFilme());
        
        List<ValorMidia> valorMidias = valorMidiaRepository.findAll();
        
        Assert.assertEquals(6, valorMidias.size());
        
        List<ValorMidia> valorMidiasAntigos = valorMidias.stream().filter( v -> v.getFinalVigencia() != null).collect(Collectors.toList());
        Assert.assertEquals(3, valorMidiasAntigos.size());
        
        valorMidiasAntigos.stream().forEach( v -> { 
                            Assert.assertEquals(1.9d, v.getValor(), DELTA); 
                            Assert.assertEquals(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss")),
                                                v.getInicioVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
                            Assert.assertEquals(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss")),
                                                v.getFinalVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
            });
        
        List<ValorMidia> valorMidiasNovos = valorMidias.stream().filter( v -> v.getFinalVigencia() == null).collect(Collectors.toList());
        Assert.assertEquals(3, valorMidiasNovos.size());
        
        valorMidiasNovos.stream().forEach( v -> { 
                            Assert.assertEquals(9.0d, v.getValor(), DELTA);
                            Assert.assertEquals(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss")),
                                                v.getInicioVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
                            Assert.assertNull(v.getFinalVigencia()); 
            });

    }
    
    @Test
    public void filmePrecosTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)));

        Filme filmeSave = filmeRepository.findAll().get(0);
              
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/filme/precos/"+filmeSave.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].tipo").value(TipoMidia.VHS.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].tipo").value(TipoMidia.DVD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[2].tipo").value(TipoMidia.BLUE_RAY.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[*].inicioVigencia").value(CoreMatchers.hasItem(LocalDateTime.now().format(ofPattern("dd/MM/yyyy HH:mm:ss"))) ));
    }
    
    @Test
    public void filmeSearchCategoriaTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)));
              
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/filme/search?page=0&size=9&sort=id,desc&categoria={categoria}",filmeDTO.getCategoria().name() )
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].titulo").value("João e o Feijão"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].categoria").value(Categoria.AVENTURA.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy")) ));
    }
    
    @Test
    public void filmeSearchTituloTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)));
              
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/filme/search?page=0&size=9&sort=id,desc&titulo=João e o Feijão" )
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].titulo").value("João e o Feijão"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].categoria").value(Categoria.AVENTURA.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy")) ));
    }
    
    @Test
    public void filmeSearchPorDataTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)));
              
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/filme/search?page=0&size=9&sort=id,desc&lancamentoIni=09/10/2018&lancamentoFim=11/10/2018" )
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].titulo").value("João e o Feijão"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].categoria").value(Categoria.AVENTURA.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy")) ));
    }
    
    /*
    @Test
    public void filmeCountPorTipoDVDTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)));
              
        String returned = restMockMvc.perform(MockMvcRequestBuilders.get("/api/filme/count/1/DVD" ));
        
        System.out.println(returned);
    }
    */
}
