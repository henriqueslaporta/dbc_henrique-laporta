/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.service;

import br.com.dbc.locadora.constantes.Categoria;
import br.com.dbc.locadora.constantes.TipoMidia;
import br.com.dbc.locadora.dto.MidiaCatalogoDTO;
import br.com.dbc.locadora.dto.MidiaDTO;
import br.com.dbc.locadora.entity.Aluguel;
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
    
    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private DateService dateService;
    
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
                                        .inicioVigencia(dateService.dateTimeNow())
                                    .build();
            
            valorMidiaService.save(valor); 
        }
    }
    
    public void updateMidiaDTO(MidiaDTO dto, Filme filme){
        // Pegar todas midias que sao do filme e tipo midia do DTO
        List<Midia> midiasAtuais = findByFilmeId(filme.getId()).stream()
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
    
    public Long countByTipoAndIdFilme (TipoMidia tipo, Long idFilme){
        return midiaRepository.countByTipoAndIdFilmeId(tipo, idFilme);
    }
    
    public List<Midia> findByFilmeId ( Long idFilme ){
        return midiaRepository.findByIdFilmeId(idFilme);
    }
    
    public List<Midia> findByAluguelId ( Long idAluguel ){
        return midiaRepository.findByIdAluguelId(idAluguel);
    }
    
    public Page<Midia> findByFilmeId (Pageable pageable, Long idFilme ){
        return midiaRepository.findByIdFilmeId(pageable, idFilme);
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
                                        midiaRepository.findByIdFilmeId(filme.getId()).stream().forEach( midia -> {
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
    
    public List<MidiaCatalogoDTO> midiaCatalogoListFromFilme(Filme filme){
        List<MidiaCatalogoDTO> listMidias = new ArrayList<>();
        
        for(TipoMidia tipo : TipoMidia.values()){
            MidiaCatalogoDTO midiaCat = midiaCatalogoFromFilmeAndTipo(filme,tipo);
            if(midiaCat != null){
               listMidias.add(midiaCat);
            }
        }
        
        return listMidias;
    }
    
    public MidiaCatalogoDTO midiaCatalogoFromFilmeAndTipo(Filme filme, TipoMidia tipo){
                
        List<Midia> midias = midiaRepository.findByIdFilmeIdAndTipo(filme.getId(), tipo); 

        if(midias.isEmpty()){
            return null;
        }
        MidiaCatalogoDTO midiaCatalogo = MidiaCatalogoDTO.builder().tipo(tipo).build();
        
        midiaCatalogo.setValor( 
                valorMidiaService.getValorMidia( midias.get(0).getId(), null) 
            );
        
        if(midias.stream().filter( m -> m.getIdAluguel() == null).collect(Collectors.toList()).isEmpty()){
            
            LocalDateTime menorPrevisao = dateService.dateNow().atStartOfDay();
            
            for(Midia m : midias.stream().filter( m -> m.getIdAluguel() != null).collect(Collectors.toList()) ){
                Aluguel aluguel = aluguelService.findById( m.getIdAluguel().getId() ).get();

                menorPrevisao = ( menorPrevisao.isBefore( aluguel.getPrevisao() ) 
                                    || menorPrevisao.isEqual( aluguel.getPrevisao() ) )
                                    ? aluguel.getPrevisao() 
                                    : menorPrevisao ;
            }
        
            midiaCatalogo.setDisponivel( menorPrevisao );
            
            return midiaCatalogo;
        }
        
        midiaCatalogo.setDisponivel( null );
        
        return midiaCatalogo;
    }
    
}
