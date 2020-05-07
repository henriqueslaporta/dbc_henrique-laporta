/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.repository;

import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.entity.Midia;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author henrique.laporta
 */
public interface MidiaRepository extends JpaRepository<Midia, Long> {
    
    Long countByTipo(TipoMidia tipo);
    
    Long countByTipoAndIdFilmeId (TipoMidia tipo, Long id);
    
    List<Midia> findByIdFilmeId (Long idFilme);
    
    Page<Midia> findByIdFilmeId (Pageable pageable, Long idFilme);

    List<Midia> findByIdFilmeIdAndTipo (Long idFilme, TipoMidia tipo);
    
    List<Midia> findByTipo (TipoMidia tipo);
    
    List<Midia> findByIdAluguelId (Long idAluguel);
}
