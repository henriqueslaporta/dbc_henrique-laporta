/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshopjpa.dao;

import br.com.dbc.petshopjpa.entity.AbstractEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author henrique.laporta
 */
public abstract class AbstractDAO< ENTITY extends AbstractEntity<ID> , ID > {
    
    private static final Logger LOG = Logger.getLogger(ClienteDAO.class.getName());
    
    protected abstract Class<ENTITY> getEntityClass();
    
    public List<ENTITY> findAll(){
        EntityManager em = PersistenceUtils.getEntityManager();
        try{
            return em.createQuery(
                    String.format("select e from %s e", getEntityClass().getSimpleName()),
                    getEntityClass()).getResultList();
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }
    
    public ENTITY findOne(ID id){
        EntityManager em = PersistenceUtils.getEntityManager();
        try{
            em.getTransaction().begin();
            return em.createQuery( 
                    String.format("select e from %s e", getEntityClass().getSimpleName())
                    ,getEntityClass() )
                    .setParameter("id", id)
                    .getSingleResult();            
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }
    
    public void createOrUpdate(ENTITY entity){
        EntityManager em = PersistenceUtils.getEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            em.getTransaction().rollback();
            throw ex;
        }
    }
    
    public void delete(ID id){
        if(id == null){
            throw new IllegalArgumentException();
        }
        EntityManager em = PersistenceUtils.getEntityManager();
        try{
            em.getTransaction().begin();
            em.createQuery("delete from Cliente c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
            em.getTransaction().commit();
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            em.getTransaction().rollback();
            throw ex;
        }
    }
}
