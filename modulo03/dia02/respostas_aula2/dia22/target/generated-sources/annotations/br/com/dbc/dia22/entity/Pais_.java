package br.com.dbc.dia22.entity;

import br.com.dbc.dia22.entity.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-10T22:05:53")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile SingularAttribute<Pais, String> sigla;
    public static volatile ListAttribute<Pais, Estado> estadoCollection;
    public static volatile SingularAttribute<Pais, String> nome;
    public static volatile SingularAttribute<Pais, Long> id;

}