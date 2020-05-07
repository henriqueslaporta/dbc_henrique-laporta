package br.com.dbc.seguradora.service;

import br.com.dbc.seguradora.entity.Cliente;
import br.com.dbc.seguradora.repository.ClienteRepository;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tiago
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Cliente save(@NotNull @Valid Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
    
    public Optional<Cliente> findById(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente update(Long id, Cliente input) {
        input.setId(id);
        return clienteRepository.save(input);
    }

}
