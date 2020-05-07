/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.entity;

import br.com.dbc.locadora.constantes.TipoMidia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author henrique.laporta
 */
@Entity
@Table(name = "MIDIA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="MIDIA_SEQ", initialValue = 1, allocationSize = 1)
public class Midia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MIDIA_SEQ")
    private Long id;
    
    @NotNull
    @Column(name = "TIPO")
    private TipoMidia tipo;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMidia")
    //private List<ValorMidia> valorMidiaList;
    
    @JoinColumn(name = "ID_FILME", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Filme idFilme;
    
    @JoinColumn(name = "ID_ALGUEL", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Aluguel idAluguel;

}
