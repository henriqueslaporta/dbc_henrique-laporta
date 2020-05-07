/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.dto;

import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.entity.Filme;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author henrique.laporta
 */
@Builder
@Getter
@Setter
public class MidiaSearchDTO {
    private Long id;
    private TipoMidia tipo;
    private Double valor;
    private Filme filme;
}
