/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.repository;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.entity.Midia;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author henrique.laporta
 */
public interface MidiaRepository extends JpaRepository<Midia, Long> {
    
    long countByTipo(TipoMidia tipo);
    
    long countByTipoAndIdFilme (TipoMidia tipo, Filme idFilme);
    
    List<Midia> findByIdFilme (Filme idFilme);
    
    List<Midia> findByTipo (TipoMidia tipo);
    
}
