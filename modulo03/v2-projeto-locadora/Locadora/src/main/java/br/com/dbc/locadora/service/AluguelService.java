/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.service;

import br.com.dbc.locadora.dto.AluguelBackDTO;
import br.com.dbc.locadora.dto.AluguelFrontDTO;
import br.com.dbc.locadora.entity.Aluguel;
import br.com.dbc.locadora.entity.Cliente;
import br.com.dbc.locadora.entity.Midia;
import br.com.dbc.locadora.repository.AluguelRepository;
import java.time.LocalDateTime;
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
public class AluguelService extends AbstractCrudService<Aluguel> {
    
    @Autowired
    private AluguelRepository aluguelRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private MidiaService midiaService;
    
    @Autowired
    private ValorMidiaService valorMidiaService;

    @Autowired
    private DateService dateService;
    
    @Override
    protected JpaRepository<Aluguel, Long> getRepository() {
        return aluguelRepository;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AluguelBackDTO retirada(AluguelFrontDTO dto) {
        
        AluguelBackDTO retornoAluguel = AluguelBackDTO.builder().build();
        
        // Busca cliente pelo id
        Optional<Cliente> cliente = clienteService.findById(dto.getIdCliente());
        if(!cliente.isPresent()){
            return retornoAluguel;
        }
        
        LocalDateTime dataPrevisao = dateService.dateNow().plusDays(dto.getMidias().size()).atStartOfDay().plusHours(16);
        
        // Cria o Aluguel
        Aluguel aluguel = aluguelRepository.save(
                                Aluguel.builder().retirada(dateService.dateTimeNow())
                                        .previsao(dataPrevisao)
                                        .multa(0.0)
                                        .idCliente(cliente.get())
                                .build());
        
        // Vai em cada mídia e seta o idAluguel
        Double valor = 0d;
        
        for(Long id : dto.getMidias()){
            Optional<Midia> midia = midiaService.findById(id);
            if( midia.isPresent() ){
                midia.get().setIdAluguel(aluguel);
                valor +=  valorMidiaService.getValorMidia(midia.get().getId(), null);
            }
            midiaService.save(midia.get());
        }
        
        retornoAluguel.setDTOfromAluguel(aluguel);
        retornoAluguel.setValor(valor);
        
        return retornoAluguel;
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AluguelBackDTO devolucao(AluguelFrontDTO dto) {
        Double valor = 0d;
         Double multa = 0d;
        AluguelBackDTO aluguelDTO = AluguelBackDTO.builder().build();

        Aluguel aluguel = Aluguel.builder().build();

        for( Long id : dto.getMidias() ){
            Optional<Midia> midia = midiaService.findById(id);
            if( midia.isPresent() ){
                if( aluguelDTO.getId() == null ){ 
                    aluguelDTO.setId( midia.get().getIdAluguel().getId());
                    aluguel = aluguelRepository.findById(aluguelDTO.getId()).get();
                }
                midia.get().setIdAluguel(null);
            }
            midiaService.save(midia.get());
            valor += valorMidiaService.getValorMidia(id, aluguel.getRetirada());
        }
        
        aluguel.setDevolucao(dateService.dateTimeNow());

        if(dateService.dateTimeNow().isAfter(aluguel.getPrevisao())){
            multa = valor;  
        }
        
        aluguelDTO.setDTOfromAluguel(aluguel);
        aluguelDTO.setValor(valor);
        aluguelDTO.setMulta(multa);
        
        return aluguelDTO;
    }
    
    public Page< AluguelBackDTO > devolucaoAluguelHoje(Pageable pageable){
        Page<Aluguel> devolucoes = aluguelRepository.findByDevolucaoHoje(pageable,  dateService.dateNow().atTime(16, 0, 0) );
        
        List<AluguelBackDTO> devolucoesDTO = AluguelBackDTO.builder().build().listDTOfromListAluguel(devolucoes.getContent());
        
        for(AluguelBackDTO a : devolucoesDTO){
            Double valor = 0.0d;
            List<Midia> midias = midiaService.findByAluguelId(a.getId());
            for(Midia m : midias){
                valor += valorMidiaService.getValorMidia(m.getId(), a.getRetirada());
            }
            a.setValor(valor);
        }
        
        
        
        return new PageImpl<AluguelBackDTO>(
                        devolucoesDTO,
                        pageable, 
                        devolucoesDTO.size());
    }
}
