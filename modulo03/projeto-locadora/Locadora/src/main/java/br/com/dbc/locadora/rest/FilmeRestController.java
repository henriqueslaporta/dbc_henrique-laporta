/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.FilmeDTO;
import br.com.dbc.locadora.dto.ValorMidiaDTO;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.service.FilmeService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author henrique.laporta
 */
@RestController
@RequestMapping("/api/filme")
public class FilmeRestController extends AbstractRestController<Filme, FilmeService> {

    @Autowired
    private FilmeService filmeService;

    @Override
    protected FilmeService getService() {
        return filmeService;
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<Page<Filme>> findByTituloOrCategoriaOrLancamento(
                Pageable pageable,
                @RequestParam(value = "titulo", required = false) String titulo,
                @RequestParam( value = "categoria" , required = false) Categoria categoria,
                @RequestParam(value = "lancamentoIni", required = false) 
                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate lancamentoIni,
                @RequestParam(value = "lancamentoFim", required = false) 
                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate lancamentoFim) {

       return ResponseEntity.ok(
                getService().findByTituloOrCategoriaOrLancamento(pageable, titulo, categoria, lancamentoIni, lancamentoFim)
            ); 
    }
    
    @PostMapping("/midia")
    public ResponseEntity<?> salvarComMidia(@RequestBody FilmeDTO dto) {
        
        return ResponseEntity.ok(getService().salvarComMidia(dto));
    }
    
    @GetMapping(value = "/count/{id}/{tipo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> countbyTipo(@PathVariable Long id,@PathVariable TipoMidia tipo) {
        
        return ResponseEntity.ok( getService().countByTipo(id,tipo) );
    }
    
    @GetMapping("/precos/{id}")
    public ResponseEntity< Page<ValorMidiaDTO> > findValoresByFilmes(Pageable pageable, @PathVariable Long id) {
        
        return ResponseEntity.ok( getService().findValoresByFilme(pageable, id) );
    }
    
    @PutMapping("/{id}/midia")
    public ResponseEntity<?> updateFilmeAndMidias(@PathVariable Long id, @RequestBody FilmeDTO dto) {
        
        return ResponseEntity.ok(getService().updateFilmeAndMidia(id, dto));
    }
    
}
