/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.floricultura.type;

import javax.xml.bind.annotation.XmlEnum;

/**
 *
 * @author tiago
 */
@XmlEnum(String.class)
public enum UnidadeMedidaType {
    UN("Unidade"),
    KG("Quilo"),
    M("Metro"),
    CM("Centimetro");

    private final String descricao;

    private UnidadeMedidaType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
