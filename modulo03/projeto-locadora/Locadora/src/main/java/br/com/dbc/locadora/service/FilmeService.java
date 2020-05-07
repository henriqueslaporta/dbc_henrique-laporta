/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.service;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.FilmeDTO;
import br.com.dbc.locadora.dto.MidiaDTO;
import br.com.dbc.locadora.dto.ValorMidiaDTO;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.repository.FilmeRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author henrique.laporta
 */
@Service
public class FilmeService extends AbstractCrudService<Filme> {
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    @Autowired
    private MidiaService midiaService;
    
    @Autowired
    private ValorMidiaService valorMidiaService;

    @Override
    protected JpaRepository<Filme, Long> getRepository() {
        return filmeRepository;
    }
    
    public Page<Filme> findByTituloOrCategoriaOrLancamento(
                Pageable pageable,
                String titulo, 
                Categoria categoria,
                LocalDate lancamentoIni,
                LocalDate lancamentoFim){
       return filmeRepository.findByTituloOrCategoriaOrLancamentoBetweenIniAndFim(pageable, titulo, categoria, lancamentoIni, lancamentoFim);
   }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Filme salvarComMidia(@RequestBody FilmeDTO dto){
        
        Filme filme = getRepository().save(dto.toFilme());
        
        for(MidiaDTO midiaDTO: dto.getMidia()){
            
            midiaService.salvaMidiaDTO(midiaDTO, filme);
            
        }

        return filme;
    }
    
    public long countByTipo (Long id, TipoMidia tipo){
        Filme filme = findById(id).get();
        return midiaService.countByTipoAndIdFilme(tipo, filme);
    }
    
    public Page<ValorMidiaDTO> findValoresByFilme (Pageable pageable, Long id){
        Optional<Filme> retorno = filmeRepository.findById(id);
        Filme filme = retorno.isPresent() ? retorno.get() : Filme.builder().id( new Long(0) ).build();
        return valorMidiaService.findByFilme(pageable,filme);
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Filme updateFilmeAndMidia (Long id, FilmeDTO dto){
        if( !id.equals(dto.getId()) )
            return Filme.builder().build();
        //Salvar o filme
        Filme filme = filmeRepository.save(dto.toFilme());
        
        //Para cada midiaDTO update
        dto.getMidia().stream().forEach(midiaDTO -> midiaService.updateMidiaDTO(midiaDTO, filme));
        
        return filme;
    }
}
