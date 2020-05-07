package br.com.dbc.dia22.entity;

import br.com.dbc.dia22.entity.Cidade;
import br.com.dbc.dia22.entity.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-10T22:05:53")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile SingularAttribute<Estado, String> sigla;
    public static volatile ListAttribute<Estado, Cidade> cidadeCollection;
    public static volatile SingularAttribute<Estado, Pais> idPais;
    public static volatile SingularAttribute<Estado, String> nome;
    public static volatile SingularAttribute<Estado, Long> id;

}