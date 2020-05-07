/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator( name = "CLIENTE_SEQ", sequenceName = "CLIENTE_SEQ", allocationSize = 1)
public class Cliente extends AbstractEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue( generator = "CLIENTE_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;
    
    @JoinTable(name = "CLIENTE_ANIMAL", joinColumns = {
        @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ANIMAL", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Animal> animalList;

}
