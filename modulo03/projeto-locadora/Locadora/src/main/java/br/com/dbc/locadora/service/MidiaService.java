/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.service;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.AluguelBackDTO;
import br.com.dbc.locadora.dto.MidiaDTO;
import br.com.dbc.locadora.dto.MidiaSearchDTO;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.entity.Midia;
import br.com.dbc.locadora.entity.ValorMidia;
import br.com.dbc.locadora.repository.MidiaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author henrique.laporta
 */
@Service
public class MidiaService extends AbstractCrudService<Midia> {
    
    @Autowired
    private MidiaRepository midiaRepository;
    
    @Autowired
    private ValorMidiaService valorMidiaService;
    
    @Autowired
    private FilmeService filmeService;

    @Override
    protected JpaRepository<Midia, Long> getRepository() {
        return midiaRepository;
    }
    
    public void salvaMidiaDTO(MidiaDTO dto, Filme filme){
        for(int i = 0; i < dto.getQuantidade(); i++){
            
            Midia midia = midiaRepository.save(dto.toMidia(filme));
            
            ValorMidia valor = ValorMidia
                                    .builder()
                                        .valor(dto.getValor())
                                        .midia(midia)
                                        .inicioVigencia(LocalDateTime.now())
                                    .build();
            
            valorMidiaService.save(valor); 
        }
    }
    
    public void updateMidiaDTO(MidiaDTO dto, Filme filme){
        // Pegar todas midias que sao do filme e tipo midia do DTO
        List<Midia> midiasAtuais = findByFilme(filme).stream()
                                                    .filter(midia ->
                                                                midia.getTipo().equals(dto.getTipo())
                                                            ).collect(Collectors.toList());
        // d < 0 (Deletar)
        // d > 0 (Adicionar)
        // d = 0 (Atualizar)
        int diferenca = dto.getQuantidade() - midiasAtuais.size();
        
        if(diferenca < 0){
            deleteNMidiasFromList(-1*diferenca, midiasAtuais);
        }
        
        for(Midia m : midiasAtuais){
            valorMidiaService.updateByIdMidia(m, dto.getValor());
        }
        
        if(diferenca > 0){
            dto.setQuantidade(diferenca);
            salvaMidiaDTO( dto, filme );
        }
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public List<Midia> deleteNMidiasFromList(int n, List<Midia> midias){
        List<Midia> midiasLivres = midias.stream().filter(m -> m.getIdAluguel() == null).collect(Collectors.toList());
        for(int i = 0; i < n; i++){
            valorMidiaService.deleteByIdMidia(midiasLivres.get(0));
            delete(midiasLivres.get(0).getId());
            midiasLivres.remove(0);
        }
        return midiasLivres;
    }
    
    public Long countByTipo(TipoMidia tipo){
        return midiaRepository.countByTipo(tipo);
    }
    
    public long countByTipoAndIdFilme (TipoMidia tipo, Filme idFilme){
        return midiaRepository.countByTipoAndIdFilme(tipo, idFilme);
    }
    
    public List<Midia> findByFilme ( Filme filme ){
        return midiaRepository.findByIdFilme(filme);
    }

    public Page< Midia > findBySearch(Pageable pageable,
                                                TipoMidia tipo,
                                                Double valor,
                                                String titulo,
                                                Categoria categoria,
                                                LocalDate lancamentoIni,
                                                LocalDate lancamentoFim){
        List<Midia> midias = new ArrayList<>();

        if(tipo != null){
            midias.addAll(midiaRepository.findByTipo(tipo));
        }
        
        if(titulo != null || categoria != null || lancamentoIni != null || lancamentoFim != null){
            List<Filme> filmes = filmeService.findByTituloOrCategoriaOrLancamento(pageable, titulo, categoria, lancamentoIni, lancamentoFim).getContent();
            filmes.stream().forEach( filme -> { 
                                        midiaRepository.findByIdFilme(filme).stream().forEach( midia -> {
                                            if(!midias.contains( midia )){
                                                midias.add(midia);
                                            }
                                        });  
                                    });
        }

        if(valor != null){
            valorMidiaService.findByValor(valor).stream().forEach( v -> {
                                                    if(!midias.contains( v.getMidia() )){
                                                        midias.add(v.getMidia());
                                                    }
                                                });
            
        }
        
        
        return new PageImpl<Midia>(
                        midias,
                        pageable, 
                        midias.size());
        
    }
    
}
