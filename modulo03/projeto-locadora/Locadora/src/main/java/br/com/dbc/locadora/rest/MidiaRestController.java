/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.rest;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.MidiaSearchDTO;
import br.com.dbc.locadora.entity.Midia;
import br.com.dbc.locadora.service.MidiaService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author henrique.laporta
 */
@RestController
@RequestMapping("/api/midia")
public class MidiaRestController extends AbstractRestController<Midia, MidiaService> {

    @Autowired
    private MidiaService midiaService;

    @Override
    protected MidiaService getService() {
        return midiaService;
    }
    
    @GetMapping("/count/{tipo}")
    public ResponseEntity<Long> countByTipo(@PathVariable TipoMidia tipo){
        return ResponseEntity.ok(midiaService.countByTipo(tipo));
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<Page<Midia>> findBySearch(
                Pageable pageable,
                @RequestParam(value = "tipo", required = false) TipoMidia tipo,
                @RequestParam(value = "valor", required = false) Double valor,
                @RequestParam(value = "titulo", required = false) String titulo,
                @RequestParam( value = "categoria" , required = false) Categoria categoria,
                @RequestParam(value = "lancamentoIni", required = false) 
                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate lancamentoIni,
                @RequestParam(value = "lancamentoFim", required = false) 
                            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate lancamentoFim) {

       return ResponseEntity.ok(
                midiaService.findBySearch(pageable, tipo, valor, titulo, categoria, lancamentoIni, lancamentoFim)
            ); 
    }
    
}
