package br.com.dbc.petshopjpa.entity;

import br.com.dbc.petshopjpa.entity.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-18T15:59:22")
@StaticMetamodel(Animal.class)
public class Animal_ { 

    public static volatile ListAttribute<Animal, Cliente> clienteList;
    public static volatile SingularAttribute<Animal, String> nome;
    public static volatile SingularAttribute<Animal, Long> id;

}