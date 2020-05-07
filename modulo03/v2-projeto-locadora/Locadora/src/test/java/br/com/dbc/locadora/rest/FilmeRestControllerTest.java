/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.LocadoraApplicationTests;
import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.FilmeCatalogoDTO;
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
import br.com.dbc.locadora.service.DateService;
import br.com.dbc.locadora.service.FilmeService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private AluguelRepository aluguelRepository;
    
    @Autowired
    private FilmeService filmeService;
    
    @Override
    protected AbstractRestController getController() {
        return filmeRestController;
    }

    @Before
    public void beforeTest() {
        
        valorMidiaRepository.deleteAll();
        midiaRepository.deleteAll();
        aluguelRepository.deleteAll();
        filmeRepository.deleteAll();
        clienteRepository.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
    
    public Cliente criarCliente(){
        Cliente cliente = Cliente.builder().nome("nome").rua("rua").bairro("bairro").cidade("cidade").estado("RS").telefone("51987654321").build();
        return clienteRepository.save(cliente);
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
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
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
        Assert.assertEquals(dateService.dateTimeNow().format(ofPattern("dd/MM/yyyy HH:mm:ss")), valorMidias.get(0).getInicioVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
        Assert.assertNull(valorMidias.get(0).getFinalVigencia());
        Assert.assertEquals(midias.get(0), valorMidias.get(0).getMidia());
        
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeUpdateSomenteValorTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);

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
                            Assert.assertEquals(dateService.dateTimeNow().format(ofPattern("dd/MM/yyyy HH:mm:ss")),
                                                v.getInicioVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
                            Assert.assertEquals(dateService.dateTimeNow().format(ofPattern("dd/MM/yyyy HH:mm:ss")),
                                                v.getFinalVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
            });
        
        List<ValorMidia> valorMidiasNovos = valorMidias.stream().filter( v -> v.getFinalVigencia() == null).collect(Collectors.toList());
        Assert.assertEquals(3, valorMidiasNovos.size());
        
        valorMidiasNovos.stream().forEach( v -> { 
                            Assert.assertEquals(9.0d, v.getValor(), DELTA);
                            Assert.assertEquals(dateService.dateTimeNow().format(ofPattern("dd/MM/yyyy HH:mm:ss")),
                                                v.getInicioVigencia().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
                            Assert.assertNull(v.getFinalVigencia()); 
            });

    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeUpdateReduzMidiasrTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);

        Filme filmeSave = filmeRepository.findAll().get(0);
        
        filmeDTO.setId(filmeSave.getId());
        filmeDTO.getMidia().forEach( midiaDTO -> { midiaDTO.setQuantidade(0); });
        
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
        Assert.assertEquals(0, midias.size());
        
        List<ValorMidia> valorMidias = valorMidiaRepository.findAll();
        
        Assert.assertEquals(0, valorMidias.size());
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeUpdateAumentarMidiasrTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);

        Filme filmeSave = filmeRepository.findAll().get(0);
        
        filmeDTO.setId(filmeSave.getId());
        filmeDTO.getMidia().forEach( midiaDTO -> { midiaDTO.setQuantidade(2); });
        
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
        Assert.assertEquals(6, midias.size());
        
        List<ValorMidia> valorMidias = valorMidiaRepository.findAll();
        
        Assert.assertEquals(6, valorMidias.size());
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmePrecosTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);

        Filme filmeSave = filmeRepository.findAll().get(0);
              
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/filme/precos/"+filmeSave.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].tipo").value(TipoMidia.VHS.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].tipo").value(TipoMidia.DVD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[2].tipo").value(TipoMidia.BLUE_RAY.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[*].inicioVigencia").value(CoreMatchers.hasItem(dateService.dateTimeNow().format(ofPattern("dd/MM/yyyy HH:mm:ss"))) ));
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeSearchCategoriaTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);
              
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
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeSearchTituloTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);
              
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
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeSearchPorDataTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        List<MidiaDTO> midiasDTO = criarMidiaDTOComUmaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);
              
        restMockMvc.perform(MockMvcRequestBuilders.get("/api/filme/search?page=0&size=9&sort=id,desc&lancamentoIni=09/10/2018&lancamentoFim=11/10/2018" )
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].titulo").value("João e o Feijão"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].categoria").value(Categoria.AVENTURA.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy")) ));
    }
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeCatalogoSearchTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);
              
        FilmeCatalogoDTO filmeCatalogoDTO = FilmeCatalogoDTO.builder().titulo("oão").build();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/search/catalogo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeCatalogoDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].titulo").value("João e o Feijão"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].categoria").value(Categoria.AVENTURA.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy")) ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].tipo").value(TipoMidia.VHS.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].disponivel").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[1].tipo").value(TipoMidia.DVD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[1].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[1].disponivel").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[2].tipo").value(TipoMidia.BLUE_RAY.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[2].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[2].disponivel").isEmpty());
    }
    
    @Test
    @WithMockUser(username="john.doe", 
            password = "jwtpass", 
            authorities = {"STANDARD_USER"})
    public void filmeCatalogoSearchFilmeComUmTipoMidiaTest() throws Exception{
        
        FilmeDTO filmeDTO = FilmeDTO.builder()
                            .titulo("João e o Feijão")
                            .lancamento(LocalDate.of(2018,10,10))
                            .categoria(Categoria.AVENTURA)
                            .midia(Arrays.asList(
                                    MidiaDTO.builder().tipo(TipoMidia.DVD).quantidade(1).valor(1.9d).build()                                   
                                ))
                        .build();

        filmeService.salvarComMidia(filmeDTO);
              
        FilmeCatalogoDTO filmeCatalogoDTO = FilmeCatalogoDTO.builder().titulo("oão").build();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/search/catalogo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeCatalogoDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].titulo").value("João e o Feijão"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].categoria").value(Categoria.AVENTURA.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy")) ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].tipo").value(TipoMidia.DVD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].disponivel").isEmpty());
    }
    
    @Test
    @WithMockUser(username="john.doe", 
            password = "jwtpass", 
            authorities = {"STANDARD_USER"})
    public void filmeCatalogoSearchComMidiaAlugadaTest() throws Exception{
         
        Cliente cliente = criarCliente();
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        
        filmeService.salvarComMidia(filmeDTO);
        
        List<Midia> midias = midiaRepository.findAll();

       midias.get(0).setIdAluguel( aluguelRepository.save( Aluguel.builder()
                                            .idCliente(cliente)
                                            .retirada(dateService.dateTimeNow())
                                            .previsao(dateService.dateNow().atStartOfDay().plusDays(1).plusHours(16))
                                        .build()
                                ));
        midiaRepository.save( midias.get(0) );
              
        FilmeCatalogoDTO filmeCatalogoDTO = FilmeCatalogoDTO.builder().titulo("oão").build();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/search/catalogo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeCatalogoDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].titulo").value("João e o Feijão"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].categoria").value(Categoria.AVENTURA.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].lancamento").value(LocalDate.of(2018,10,10).format(ofPattern("dd/MM/yyyy")) ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].tipo").value(TipoMidia.VHS.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[0].disponivel").value( dateService.dateNow().atStartOfDay().plusDays(1).plusHours(16).format(ofPattern("dd/MM/yyyy HH:mm:ss")) ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[1].tipo").value(TipoMidia.DVD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[1].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[1].disponivel").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[2].tipo").value(TipoMidia.BLUE_RAY.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[2].valor").value(1.9d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].midias.[2].disponivel").isEmpty());
    }
    
    
    @Test
    @WithMockUser(username="admin.admin", 
            password = "jwtpass", 
            authorities = {"ADMIN_USER"})
    public void filmeCountPorTipoDVDTest() throws Exception{
        
        FilmeDTO filmeDTO = criarFilmeDTOComUmaMidiaDeCada();
        
        restMockMvc.perform(MockMvcRequestBuilders.post("/api/filme/midia")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(filmeDTO)));
        
        List<Filme> filmes = filmeRepository.findAll();
        
        String url = "/api/filme/count/" + filmes.get(0).getId()+"/DVD";
        
        restMockMvc.perform(MockMvcRequestBuilders.get(url ))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andExpect(MockMvcResultMatchers.content().string("1"));
       
       
    }
    
}
