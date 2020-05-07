/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.floricultura.entity;

import br.com.dbc.floricultura.type.UnidadeMedidaType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tiago
 */
@Entity
@Table(name = "PRODUTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id")
    , @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao like :descricao")
    , @NamedQuery(name = "Produto.findByUnidadeMedida", query = "SELECT p FROM Produto p WHERE p.unidadeMedida = :unidadeMedida")
    , @NamedQuery(name = "Produto.findByValor", query = "SELECT p FROM Produto p WHERE p.valor = :valor")})
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_SEQ")
    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "PRODUTO_SEQ", sequenceName = "PRODUTO_SEQ")
    private Long id;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;
    @Column(name = "UNIDADE_MEDIDA", length = 3)
    @Enumerated(EnumType.STRING)
    private UnidadeMedidaType unidadeMedida;
    @NotNull
    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduto")
    private List<Venda> vendaList;

    public Produto() {
    }

    public Produto(Long id) {
        this.id = id;
    }

    public Produto(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Produto(Long id, String descricao, UnidadeMedidaType unidadeMedida, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeMedidaType getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedidaType unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.dbc.entity.Produto[ id=" + id + " ]";
    }

}
