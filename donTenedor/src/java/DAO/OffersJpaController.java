/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Offers;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Restaurant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class OffersJpaController implements Serializable {

    public OffersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Offers offers) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant idRestaurant = offers.getIdRestaurant();
            if (idRestaurant != null) {
                idRestaurant = em.getReference(idRestaurant.getClass(), idRestaurant.getIdRestaurant());
                offers.setIdRestaurant(idRestaurant);
            }
            em.persist(offers);
            if (idRestaurant != null) {
                idRestaurant.getOffersList().add(offers);
                idRestaurant = em.merge(idRestaurant);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Offers offers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Offers persistentOffers = em.find(Offers.class, offers.getIdOffer());
            Restaurant idRestaurantOld = persistentOffers.getIdRestaurant();
            Restaurant idRestaurantNew = offers.getIdRestaurant();
            if (idRestaurantNew != null) {
                idRestaurantNew = em.getReference(idRestaurantNew.getClass(), idRestaurantNew.getIdRestaurant());
                offers.setIdRestaurant(idRestaurantNew);
            }
            offers = em.merge(offers);
            if (idRestaurantOld != null && !idRestaurantOld.equals(idRestaurantNew)) {
                idRestaurantOld.getOffersList().remove(offers);
                idRestaurantOld = em.merge(idRestaurantOld);
            }
            if (idRestaurantNew != null && !idRestaurantNew.equals(idRestaurantOld)) {
                idRestaurantNew.getOffersList().add(offers);
                idRestaurantNew = em.merge(idRestaurantNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = offers.getIdOffer();
                if (findOffers(id) == null) {
                    throw new NonexistentEntityException("The offers with id " + id + " no longer exists.");
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
            Offers offers;
            try {
                offers = em.getReference(Offers.class, id);
                offers.getIdOffer();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The offers with id " + id + " no longer exists.", enfe);
            }
            Restaurant idRestaurant = offers.getIdRestaurant();
            if (idRestaurant != null) {
                idRestaurant.getOffersList().remove(offers);
                idRestaurant = em.merge(idRestaurant);
            }
            em.remove(offers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Offers> findOffersEntities() {
        return findOffersEntities(true, -1, -1);
    }

    public List<Offers> findOffersEntities(int maxResults, int firstResult) {
        return findOffersEntities(false, maxResults, firstResult);
    }

    private List<Offers> findOffersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Offers.class));
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

    public Offers findOffers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Offers.class, id);
        } finally {
            em.close();
        }
    }

    public int getOffersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Offers> rt = cq.from(Offers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Offers findOfferByRestaurant(Restaurant restaurant) {

        Offers o = null;
        EntityManager em = getEntityManager();

        Query query = em.createNamedQuery("Offers.findByIdRestaurant");
        query.setParameter("idRestaurant", restaurant);
        try {
            List lista = query.getResultList();
            if (lista.size() > 0) {
                o = (Offers) lista.get(0);
            }
        } finally {
            em.close();
        }
        return o;

    }

}
