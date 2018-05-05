/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.IncidenceErrors;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author danieljimenez
 */
public class IncidenceErrorsJpaController implements Serializable {

    public IncidenceErrorsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncidenceErrors incidenceErrors) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incidenceErrors);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIncidenceErrors(incidenceErrors.getIdIncidence()) != null) {
                throw new PreexistingEntityException("IncidenceErrors " + incidenceErrors + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncidenceErrors incidenceErrors) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incidenceErrors = em.merge(incidenceErrors);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incidenceErrors.getIdIncidence();
                if (findIncidenceErrors(id) == null) {
                    throw new NonexistentEntityException("The incidenceErrors with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IncidenceErrors incidenceErrors;
            try {
                incidenceErrors = em.getReference(IncidenceErrors.class, id);
                incidenceErrors.getIdIncidence();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The incidenceErrors with id " + id + " no longer exists.", enfe);
            }
            em.remove(incidenceErrors);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncidenceErrors> findIncidenceErrorsEntities() {
        return findIncidenceErrorsEntities(true, -1, -1);
    }

    public List<IncidenceErrors> findIncidenceErrorsEntities(int maxResults, int firstResult) {
        return findIncidenceErrorsEntities(false, maxResults, firstResult);
    }

    private List<IncidenceErrors> findIncidenceErrorsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IncidenceErrors.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public IncidenceErrors findIncidenceErrors(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncidenceErrors.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncidenceErrorsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncidenceErrors> rt = cq.from(IncidenceErrors.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
