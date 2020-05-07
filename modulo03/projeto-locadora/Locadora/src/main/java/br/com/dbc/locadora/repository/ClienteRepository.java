/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.repository;

import br.com.dbc.locadora.entity.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author henrique.laporta
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    public Optional<Cliente> findByNome (String nome);
    
    public Cliente findByTelefone (String telefone);
    
    public Cliente findByEndereco (String endereco);
    
}
