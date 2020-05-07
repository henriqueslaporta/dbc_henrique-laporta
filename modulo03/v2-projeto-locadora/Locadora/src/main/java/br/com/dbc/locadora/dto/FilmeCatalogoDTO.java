/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.dto;

import br.com.dbc.locadora.constantes.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author henrique.laporta
 */
@Builder
@Data
public class FilmeCatalogoDTO {
    private String titulo;
    private Categoria categoria;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate lancamento;
    private List<MidiaCatalogoDTO> midias;
    
}
