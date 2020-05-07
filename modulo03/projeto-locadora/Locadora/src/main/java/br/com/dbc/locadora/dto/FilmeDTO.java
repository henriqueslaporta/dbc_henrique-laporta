/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.dto;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.entity.Filme;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author henrique.laporta
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {
    
    private Long id;
    private String titulo;
    
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate lancamento;
    private Categoria categoria;
    private List<MidiaDTO> midia;
    
    public Filme toFilme(){
        return Filme.builder()
                .id(id)
                .titulo(titulo)
                .lancamento(lancamento)
                .categoria(categoria)
                .build();
    }
    
    
    
}
