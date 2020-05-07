/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.service;

import br.com.dbc.locadora.dto.ValorMidiaDTO;
import br.com.dbc.locadora.entity.Filme;
import br.com.dbc.locadora.entity.Midia;
import br.com.dbc.locadora.entity.ValorMidia;
import br.com.dbc.locadora.repository.ValorMidiaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class ValorMidiaService extends AbstractCrudService<ValorMidia> {
    
    @Autowired
    private ValorMidiaRepository valorMidiaRepository;
    
    @Autowired
    private MidiaService midiaService;

    @Override
    protected JpaRepository<ValorMidia, Long> getRepository() {
        return valorMidiaRepository;
    }
    
    @Autowired
    private DateService dateService;
    
    public Page<ValorMidiaDTO> findByFilme (Pageable pageable, Filme filme ){
        
        Page<Midia> midias = midiaService.findByFilmeId(pageable, filme.getId());
        
        List<ValorMidiaDTO> valoresDTO = new ArrayList<ValorMidiaDTO>();
        
        ValorMidiaDTO valorMidiaDTO = ValorMidiaDTO.builder().build();
        
        for(Midia m : midias.getContent()){
            List<ValorMidia> valoresDaMidia = valorMidiaRepository.findByMidiaId(m.getId());
            valoresDTO.addAll( valorMidiaDTO.listValorMidiaToDTO(valoresDaMidia) );
        }
        
        return new PageImpl<ValorMidiaDTO>(
                        valoresDTO,
                        pageable, 
                        valoresDTO.size());
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteByIdMidia(Midia idMidia) {
        valorMidiaRepository.deleteByMidiaId(idMidia.getId());
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateByIdMidia(Midia idMidia, Double valor) {
        Optional<ValorMidia> optionalMidia = valorMidiaRepository.findByMidiaId(idMidia.getId()).stream()
                                                    .filter( v -> v.getFinalVigencia() == null).findFirst();        
        if( !optionalMidia.isPresent() ) return;
        
        ValorMidia vMidia = optionalMidia.get();
        
        // Valor igual retorna
        if(vMidia.getValor() == valor) return;
        
        // Valor diferente set o final da vigencia atual
        vMidia.setFinalVigencia(dateService.dateTimeNow());
        valorMidiaRepository.save(vMidia);
        
        // Cria um novo valor midia atualizado
        valorMidiaRepository.save(ValorMidia.builder()
                                                .id(null)
                                                .valor(valor)
                                                .inicioVigencia(dateService.dateTimeNow())
                                                .finalVigencia(null)
                                                .midia(idMidia)
                                            .build()
                                    );
    }

    Double getValorMidia(Long id, LocalDateTime retirada) {
        if(retirada == null) return valorMidiaRepository.findByMidiaIdValor(id).getValor();
        return valorMidiaRepository.findByMidiaIdAndInicioVigencia(id, retirada).getValor();
    }
    
    List<ValorMidia> findByValor (Double valor){
        return valorMidiaRepository.findByValor(valor);
    }

}
