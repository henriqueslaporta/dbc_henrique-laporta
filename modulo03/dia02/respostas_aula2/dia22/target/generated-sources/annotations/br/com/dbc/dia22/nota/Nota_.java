package br.com.dbc.dia22.nota;

import br.com.dbc.dia22.nota.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-10T22:05:53")
@StaticMetamodel(Nota.class)
public class Nota_ { 

    public static volatile ListAttribute<Nota, Item> itensCollection;
    public static volatile SingularAttribute<Nota, Long> id;
    public static volatile SingularAttribute<Nota, String> descricao;

}