/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.dto;

import java.time.LocalDateTime;
import java.util.List;
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
public class AluguelFrontDTO {
    private Long idCliente;
    private List<Long> midias;
}
