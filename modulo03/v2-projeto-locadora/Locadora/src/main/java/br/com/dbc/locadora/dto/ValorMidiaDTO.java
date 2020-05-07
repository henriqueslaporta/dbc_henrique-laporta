/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.dto;

import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.entity.ValorMidia;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Camila e Henrique
 */
@Builder
@AllArgsConstructor
@Getter
public class ValorMidiaDTO {
    private Long id;
    private TipoMidia tipo;
    private double valor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime inicioVigencia;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime finalVigencia;
    
    public List<ValorMidiaDTO> listValorMidiaToDTO (List<ValorMidia> valores){
        
        List<ValorMidiaDTO> dtos = new ArrayList<ValorMidiaDTO>();
        
        valores.stream().forEach(
                                valor -> dtos.add(valorMidiaToDTO(valor))                  
                            );
        return dtos;
    }
    
    public ValorMidiaDTO valorMidiaToDTO (ValorMidia valorMidia){
        return ValorMidiaDTO.builder()
                                .id( valorMidia.getMidia().getId() )
                                .tipo( valorMidia.getMidia().getTipo() )
                                .valor( valorMidia.getValor() )
                                .inicioVigencia( valorMidia.getInicioVigencia() )
                                .finalVigencia( valorMidia.getFinalVigencia() )
                            .build();
    }
}
