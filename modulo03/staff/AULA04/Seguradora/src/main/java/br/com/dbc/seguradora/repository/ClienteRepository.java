package br.com.dbc.seguradora.repository;

import br.com.dbc.seguradora.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tiago
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
