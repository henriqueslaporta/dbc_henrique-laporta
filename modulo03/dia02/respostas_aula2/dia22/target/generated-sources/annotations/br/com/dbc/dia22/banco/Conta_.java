package br.com.dbc.dia22.banco;

import br.com.dbc.dia22.banco.Banco;
import br.com.dbc.dia22.banco.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-10T22:05:53")
@StaticMetamodel(Conta.class)
public class Conta_ { 

    public static volatile SingularAttribute<Conta, Cliente> cliente;
    public static volatile SingularAttribute<Conta, String> numeroConta;
    public static volatile SingularAttribute<Conta, Banco> banco;
    public static volatile SingularAttribute<Conta, Long> id;

}