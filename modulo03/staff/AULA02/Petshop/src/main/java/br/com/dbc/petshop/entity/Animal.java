/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author tiago
 */
@Entity
@Table(name = "ANIMAL")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")})
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANIMAL_SEQ")
    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "ANIMAL_SEQ", sequenceName = "ANIMAL_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;
    @Column(name = "SEXO", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private SexoType sexo;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    public Animal() {
    }

    public Animal(Long id) {
        this.id = id;
    }

    public Animal(Long id, String nome, SexoType sexo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SexoType getSexo() {
        return sexo;
    }

    public void setSexo(SexoType sexo) {
        this.sexo = sexo;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.dbc.petshop.entity.Animal[ id=" + id + " ]";
    }
    
    
    
}
