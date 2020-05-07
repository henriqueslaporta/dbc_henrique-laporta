/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.dto;

import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.entity.Midia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author henrique.laporta
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MidiaDTO {
    private TipoMidia tipo;
    private int quantidade;
    private double valor;
    
    public Midia toMidia(Filme filme){
        return Midia.builder()
                .tipo(tipo)
                .idFilme(filme)
                .idAluguel(null)
                .build();
    }
}
