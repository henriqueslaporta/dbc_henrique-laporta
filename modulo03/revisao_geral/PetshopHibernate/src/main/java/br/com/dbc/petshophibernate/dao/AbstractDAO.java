/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbc.petshophibernate.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author henrique.laporta
 */
public abstract class AbstractDAO<ENTITY, ID> {    
    
    protected abstract Class<ENTITY> getEntityClass();
    protected abstract String getIdProperty();
    
    private static final Logger LOG = Logger.getLogger(AbstractDAO.class.getName());
    
    public AbstractDAO() {
    }
    
    public ENTITY findById(ID id){        
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            return (ENTITY) session
                        .createCriteria(getEntityClass())
                        .add(Restrictions.eq(getIdProperty(), id))
                        .uniqueResult();
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        finally{
            if(session != null)
                session.close();
        }
    }
    
    public List<ENTITY> findAll(){        
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createCriteria(getEntityClass()).list();
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        finally{
            if(session != null)
                session.close();
        }
    }
    
    
    public void createOrUpdate(ENTITY e){
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(e);
            transaction.commit();
        }
        catch(HibernateException ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        finally{
            if(session != null)
                session.close();
        }
    }
    
    public void delete(ENTITY e){
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(e);
            transaction.commit();
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
        finally{
            if(session != null)
                session.close();
        }
    }
    
    
}
