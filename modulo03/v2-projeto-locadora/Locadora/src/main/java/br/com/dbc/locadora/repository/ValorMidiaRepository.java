/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.repository;

import br.com.dbc.locadora.entity.ValorMidia;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author henrique.laporta
 */
public interface ValorMidiaRepository extends JpaRepository<ValorMidia, Long> {

    public List<ValorMidia> findByMidiaId(Long idMidia);
    
    void deleteByMidiaId(Long idMidia);
    
    @Query("select vl from ValorMidia vl "
            + "where vl.midia.id = :idMidia and vl.inicioVigencia <= :retirada "
            + "and (vl.finalVigencia >= :retirada or vl.finalVigencia is null)")
    ValorMidia findByMidiaIdAndInicioVigencia(@Param("idMidia") Long idMidia, @Param("retirada") LocalDateTime retirada);
    
    @Query("select vl from ValorMidia vl "
            + "where vl.midia.id = :idMidia "
            + "and vl.finalVigencia is null")
    ValorMidia findByMidiaIdValor(@Param("idMidia") Long idMidia);
    
    List<ValorMidia> findByValor (Double valor);

}
