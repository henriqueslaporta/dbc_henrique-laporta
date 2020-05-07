package br.com.dbc.dia22.nota;

import br.com.dbc.dia22.nota.Nota;
import br.com.dbc.dia22.nota.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-10T22:05:53")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Produto> produto;
    public static volatile SingularAttribute<Item, Long> id;
    public static volatile SingularAttribute<Item, Nota> nota;
    public static volatile SingularAttribute<Item, String> descricao;

}