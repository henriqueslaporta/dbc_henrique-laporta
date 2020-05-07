/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.repository;

import br.com.dbc.locadora.entity.Aluguel;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author henrique.laporta
 */
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    @Query("select al from Aluguel al "
            + "where al.previsao = :hoje "
            + "and al.devolucao is null")
    List<Aluguel> findByDevolucaoHoje ( @Param("hoje") LocalDateTime hoje );
    
}
