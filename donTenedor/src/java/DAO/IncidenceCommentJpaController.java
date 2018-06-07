/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Comments;
import DTO.IncidenceComment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class IncidenceCommentJpaController implements Serializable {

    public IncidenceCommentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncidenceComment incidenceComment) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comments idComment = incidenceComment.getIdComment();
            if (idComment != null) {
                idComment = em.getReference(idComment.getClass(), idComment.getIdComment());
                incidenceComment.setIdComment(idComment);
            }
            em.persist(incidenceComment);
            if (idComment != null) {
                idComment.getIncidenceCommentList().add(incidenceComment);
                idComment = em.merge(idComment);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIncidenceComment(incidenceComment.getIdIncidence()) != null) {
                throw new PreexistingEntityException("IncidenceComment " + incidenceComment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncidenceComment incidenceComment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IncidenceComment persistentIncidenceComment = em.find(IncidenceComment.class, incidenceComment.getIdIncidence());
            Comments idCommentOld = persistentIncidenceComment.getIdComment();
            Comments idCommentNew = incidenceComment.getIdComment();
            if (idCommentNew != null) {
                idCommentNew = em.getReference(idCommentNew.getClass(), idCommentNew.getIdComment());
                incidenceComment.setIdComment(idCommentNew);
            }
            incidenceComment = em.merge(incidenceComment);
            if (idCommentOld != null && !idCommentOld.equals(idCommentNew)) {
                idCommentOld.getIncidenceCommentList().remove(incidenceComment);
                idCommentOld = em.merge(idCommentOld);
            }
            if (idCommentNew != null && !idCommentNew.equals(idCommentOld)) {
                idCommentNew.getIncidenceCommentList().add(incidenceComment);
                idCommentNew = em.merge(idCommentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incidenceComment.getIdIncidence();
                if (findIncidenceComment(id) == null) {
                    throw new NonexistentEntityException("The incidenceComment with id " + id + " no longer exists.");
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
            IncidenceComment incidenceComment;
            try {
                incidenceComment = em.getReference(IncidenceComment.class, id);
                incidenceComment.getIdIncidence();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The incidenceComment with id " + id + " no longer exists.", enfe);
            }
            Comments idComment = incidenceComment.getIdComment();
            if (idComment != null) {
                idComment.getIncidenceCommentList().remove(incidenceComment);
                idComment = em.merge(idComment);
            }
            em.remove(incidenceComment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncidenceComment> findIncidenceCommentEntities() {
        return findIncidenceCommentEntities(true, -1, -1);
    }

    public List<IncidenceComment> findIncidenceCommentEntities(int maxResults, int firstResult) {
        return findIncidenceCommentEntities(false, maxResults, firstResult);
    }

    private List<IncidenceComment> findIncidenceCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IncidenceComment.class));
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

    public IncidenceComment findIncidenceComment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncidenceComment.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncidenceCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncidenceComment> rt = cq.from(IncidenceComment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public IncidenceComment findIncidenceByIdComment(Comments comment){
        EntityManager em = getEntityManager();

        IncidenceComment incidence = null;

        Query query = em.createNamedQuery("IncidenceComment.findByIdComment");
        query.setParameter("idComment", comment);

        try {
            List lista = query.getResultList();
            if (lista.size() > 0) {
                incidence = (IncidenceComment) lista.get(0);
            }
        } finally {
            em.close();
        }
        return incidence;
    }
    
}
