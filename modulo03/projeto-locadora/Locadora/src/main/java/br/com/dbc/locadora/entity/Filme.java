/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.locadora.entity;

import br.com.dbc.locadora.constantes.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author henrique.laporta
 */
@Entity
@Table(name = "FILME")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="FILME_SEQ", initialValue = 1, allocationSize = 1)
public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILME_SEQ")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITULO")
    private String titulo;

    @NotNull
    @Column(name = "LANCAMENTO")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate lancamento;

    @NotNull
    @Column(name = "CATEGORIA")
    private Categoria categoria;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFilme")
    //private List<Midia> midiaList;

}
