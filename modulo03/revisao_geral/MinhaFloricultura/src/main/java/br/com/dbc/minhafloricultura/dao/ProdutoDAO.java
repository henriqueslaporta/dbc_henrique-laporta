/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.minhafloricultura.dao;

import br.com.dbc.minhafloricultura.entity.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author henrique.laporta
 */
@Stateless
public class ProdutoDAO extends AbstractDAO<Produto> {

    @PersistenceContext(unitName = "minha_floricultura_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoDAO() {
        super(Produto.class);
    }
    
    public List<Produto> findDescricao(String descricao) {
        return this.em.createQuery("SELECT p FROM Produto p WHERE LOWER(DESCRICAO) LIKE LOWER(:descricao) ", Produto.class)
                .setParameter("descricao", "%"+descricao+"%").getResultList();
    }
}
