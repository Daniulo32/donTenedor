/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Comments;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Restaurant;
import DTO.Users;
import DTO.IncidenceComment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class CommentsJpaController implements Serializable {

    public CommentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comments comments) {
        if (comments.getIncidenceCommentList() == null) {
            comments.setIncidenceCommentList(new ArrayList<IncidenceComment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant idRestaurant = comments.getIdRestaurant();
            if (idRestaurant != null) {
                idRestaurant = em.getReference(idRestaurant.getClass(), idRestaurant.getIdRestaurant());
                comments.setIdRestaurant(idRestaurant);
            }
            Users idUser = comments.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                comments.setIdUser(idUser);
            }
            List<IncidenceComment> attachedIncidenceCommentList = new ArrayList<IncidenceComment>();
            for (IncidenceComment incidenceCommentListIncidenceCommentToAttach : comments.getIncidenceCommentList()) {
                incidenceCommentListIncidenceCommentToAttach = em.getReference(incidenceCommentListIncidenceCommentToAttach.getClass(), incidenceCommentListIncidenceCommentToAttach.getIdIncidence());
                attachedIncidenceCommentList.add(incidenceCommentListIncidenceCommentToAttach);
            }
            comments.setIncidenceCommentList(attachedIncidenceCommentList);
            em.persist(comments);
            if (idRestaurant != null) {
                idRestaurant.getCommentsList().add(comments);
                idRestaurant = em.merge(idRestaurant);
            }
            if (idUser != null) {
                idUser.getCommentsList().add(comments);
                idUser = em.merge(idUser);
            }
            for (IncidenceComment incidenceCommentListIncidenceComment : comments.getIncidenceCommentList()) {
                Comments oldIdCommentOfIncidenceCommentListIncidenceComment = incidenceCommentListIncidenceComment.getIdComment();
                incidenceCommentListIncidenceComment.setIdComment(comments);
                incidenceCommentListIncidenceComment = em.merge(incidenceCommentListIncidenceComment);
                if (oldIdCommentOfIncidenceCommentListIncidenceComment != null) {
                    oldIdCommentOfIncidenceCommentListIncidenceComment.getIncidenceCommentList().remove(incidenceCommentListIncidenceComment);
                    oldIdCommentOfIncidenceCommentListIncidenceComment = em.merge(oldIdCommentOfIncidenceCommentListIncidenceComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comments comments) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comments persistentComments = em.find(Comments.class, comments.getIdComment());
            Restaurant idRestaurantOld = persistentComments.getIdRestaurant();
            Restaurant idRestaurantNew = comments.getIdRestaurant();
            Users idUserOld = persistentComments.getIdUser();
            Users idUserNew = comments.getIdUser();
            List<IncidenceComment> incidenceCommentListOld = persistentComments.getIncidenceCommentList();
            List<IncidenceComment> incidenceCommentListNew = comments.getIncidenceCommentList();
            List<String> illegalOrphanMessages = null;
            for (IncidenceComment incidenceCommentListOldIncidenceComment : incidenceCommentListOld) {
                if (!incidenceCommentListNew.contains(incidenceCommentListOldIncidenceComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain IncidenceComment " + incidenceCommentListOldIncidenceComment + " since its idComment field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRestaurantNew != null) {
                idRestaurantNew = em.getReference(idRestaurantNew.getClass(), idRestaurantNew.getIdRestaurant());
                comments.setIdRestaurant(idRestaurantNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                comments.setIdUser(idUserNew);
            }
            List<IncidenceComment> attachedIncidenceCommentListNew = new ArrayList<IncidenceComment>();
            for (IncidenceComment incidenceCommentListNewIncidenceCommentToAttach : incidenceCommentListNew) {
                incidenceCommentListNewIncidenceCommentToAttach = em.getReference(incidenceCommentListNewIncidenceCommentToAttach.getClass(), incidenceCommentListNewIncidenceCommentToAttach.getIdIncidence());
                attachedIncidenceCommentListNew.add(incidenceCommentListNewIncidenceCommentToAttach);
            }
            incidenceCommentListNew = attachedIncidenceCommentListNew;
            comments.setIncidenceCommentList(incidenceCommentListNew);
            comments = em.merge(comments);
            if (idRestaurantOld != null && !idRestaurantOld.equals(idRestaurantNew)) {
                idRestaurantOld.getCommentsList().remove(comments);
                idRestaurantOld = em.merge(idRestaurantOld);
            }
            if (idRestaurantNew != null && !idRestaurantNew.equals(idRestaurantOld)) {
                idRestaurantNew.getCommentsList().add(comments);
                idRestaurantNew = em.merge(idRestaurantNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getCommentsList().remove(comments);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getCommentsList().add(comments);
                idUserNew = em.merge(idUserNew);
            }
            for (IncidenceComment incidenceCommentListNewIncidenceComment : incidenceCommentListNew) {
                if (!incidenceCommentListOld.contains(incidenceCommentListNewIncidenceComment)) {
                    Comments oldIdCommentOfIncidenceCommentListNewIncidenceComment = incidenceCommentListNewIncidenceComment.getIdComment();
                    incidenceCommentListNewIncidenceComment.setIdComment(comments);
                    incidenceCommentListNewIncidenceComment = em.merge(incidenceCommentListNewIncidenceComment);
                    if (oldIdCommentOfIncidenceCommentListNewIncidenceComment != null && !oldIdCommentOfIncidenceCommentListNewIncidenceComment.equals(comments)) {
                        oldIdCommentOfIncidenceCommentListNewIncidenceComment.getIncidenceCommentList().remove(incidenceCommentListNewIncidenceComment);
                        oldIdCommentOfIncidenceCommentListNewIncidenceComment = em.merge(oldIdCommentOfIncidenceCommentListNewIncidenceComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comments.getIdComment();
                if (findComments(id) == null) {
                    throw new NonexistentEntityException("The comments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comments comments;
            try {
                comments = em.getReference(Comments.class, id);
                comments.getIdComment();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comments with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<IncidenceComment> incidenceCommentListOrphanCheck = comments.getIncidenceCommentList();
            for (IncidenceComment incidenceCommentListOrphanCheckIncidenceComment : incidenceCommentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Comments (" + comments + ") cannot be destroyed since the IncidenceComment " + incidenceCommentListOrphanCheckIncidenceComment + " in its incidenceCommentList field has a non-nullable idComment field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Restaurant idRestaurant = comments.getIdRestaurant();
            if (idRestaurant != null) {
                idRestaurant.getCommentsList().remove(comments);
                idRestaurant = em.merge(idRestaurant);
            }
            Users idUser = comments.getIdUser();
            if (idUser != null) {
                idUser.getCommentsList().remove(comments);
                idUser = em.merge(idUser);
            }
            em.remove(comments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comments> findCommentsEntities() {
        return findCommentsEntities(true, -1, -1);
    }

    public List<Comments> findCommentsEntities(int maxResults, int firstResult) {
        return findCommentsEntities(false, maxResults, firstResult);
    }

    private List<Comments> findCommentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comments.class));
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

    public Comments findComments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comments.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comments> rt = cq.from(Comments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
