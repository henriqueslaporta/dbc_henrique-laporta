/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.repository;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.entity.Filme;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author henrique.laporta
 */
public interface FilmeRepository extends JpaRepository<Filme, Long> {
 
    public Filme findByTitulo (String titulo);
    
    public List<Filme> findByLancamento (LocalDate lacamento);
    
    @Query("select f from Filme f where "
            + "lower(f.titulo) like lower(CONCAT('%',:titulo, '%')) "
            + "or f.categoria like :categoria "
            + "or (f.lancamento >= :ini and f.lancamento <= :fim) ")
    public Page< Filme > findByTituloOrCategoriaOrLancamentoBetweenIniAndFim(Pageable pageable, 
                                                            @Param("titulo") String titulo, 
                                                            @Param("categoria") Categoria categoria, 
                                                            @Param("ini") LocalDate ini,
                                                            @Param("fim") LocalDate fim);
}
