/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.dto;

import br.com.dbc.locadora.entity.Aluguel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@AllArgsConstructor
@NoArgsConstructor
public class AluguelBackDTO {
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime retirada;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime previsao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime devolucao;
    
    private double multa;
    
    private double valor;
    
    public void setDTOfromAluguel(Aluguel aluguel){
        this.setId(aluguel.getId());
        this.setRetirada(aluguel.getRetirada());
        this.setPrevisao(aluguel.getPrevisao());
        this.setDevolucao(aluguel.getDevolucao());
    }
    
    public List<AluguelBackDTO> listDTOfromListAluguel (List<Aluguel> alugueis){
        List<AluguelBackDTO> dto = new ArrayList<>();
        
        for(Aluguel a : alugueis){

            dto.add( AluguelBackDTO.builder()
                                        .id(a.getId())
                                        .retirada(a.getRetirada())
                                        .previsao(a.getPrevisao())
                                        .devolucao(a.getDevolucao())
                                        .multa(0d)
                                   .build()
            );
        }
        
        return dto;
    }
}
