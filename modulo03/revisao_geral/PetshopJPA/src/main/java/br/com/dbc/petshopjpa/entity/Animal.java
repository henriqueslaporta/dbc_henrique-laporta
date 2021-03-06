/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author henrique.laporta
 */
@Entity
@Table(name = "ANIMAL")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator( name = "ANIMAL_SEQ", sequenceName = "ANIMAL_SEQ", allocationSize = 1)
public class Animal extends AbstractEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue( generator = "ANIMAL_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;
    
    @ToString.Exclude
    @ManyToMany(mappedBy = "animalList")
    private List<Cliente> clienteList;
    
}
