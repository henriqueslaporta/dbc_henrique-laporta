/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author henrique.laporta
 */
@Entity
@Table(name = "CLIENTE")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="CLIENTE_SEQ", initialValue = 1, allocationSize = 1)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTE_SEQ")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TELEFONE")
    private String telefone;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ENDERECO")
    private String endereco;
    
}
