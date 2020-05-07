package br.com.dbc.dia22.nota;

import br.com.dbc.dia22.nota.Item;
import br.com.dbc.dia22.nota.UnidadeDeMedida;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-10T22:05:53")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile ListAttribute<Produto, Item> item;
    public static volatile SingularAttribute<Produto, Long> valor;
    public static volatile SingularAttribute<Produto, UnidadeDeMedida> un;
    public static volatile SingularAttribute<Produto, Long> id;
    public static volatile SingularAttribute<Produto, String> descricao;

}