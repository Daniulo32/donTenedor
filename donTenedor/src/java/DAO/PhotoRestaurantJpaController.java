/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.PhotoRestaurant;
import DTO.PhotoRestaurantPK;
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
public class PhotoRestaurantJpaController implements Serializable {

    public PhotoRestaurantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PhotoRestaurant photoRestaurant) throws PreexistingEntityException, Exception {
        if (photoRestaurant.getPhotoRestaurantPK() == null) {
            photoRestaurant.setPhotoRestaurantPK(new PhotoRestaurantPK());
        }
        photoRestaurant.getPhotoRestaurantPK().setIdRestaurant(photoRestaurant.getRestaurant().getIdRestaurant());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant restaurant = photoRestaurant.getRestaurant();
            if (restaurant != null) {
                restaurant = em.getReference(restaurant.getClass(), restaurant.getIdRestaurant());
                photoRestaurant.setRestaurant(restaurant);
            }
            em.persist(photoRestaurant);
            if (restaurant != null) {
                restaurant.getPhotoRestaurantList().add(photoRestaurant);
                restaurant = em.merge(restaurant);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPhotoRestaurant(photoRestaurant.getPhotoRestaurantPK()) != null) {
                throw new PreexistingEntityException("PhotoRestaurant " + photoRestaurant + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PhotoRestaurant photoRestaurant) throws NonexistentEntityException, Exception {
        photoRestaurant.getPhotoRestaurantPK().setIdRestaurant(photoRestaurant.getRestaurant().getIdRestaurant());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PhotoRestaurant persistentPhotoRestaurant = em.find(PhotoRestaurant.class, photoRestaurant.getPhotoRestaurantPK());
            Restaurant restaurantOld = persistentPhotoRestaurant.getRestaurant();
            Restaurant restaurantNew = photoRestaurant.getRestaurant();
            if (restaurantNew != null) {
                restaurantNew = em.getReference(restaurantNew.getClass(), restaurantNew.getIdRestaurant());
                photoRestaurant.setRestaurant(restaurantNew);
            }
            photoRestaurant = em.merge(photoRestaurant);
            if (restaurantOld != null && !restaurantOld.equals(restaurantNew)) {
                restaurantOld.getPhotoRestaurantList().remove(photoRestaurant);
                restaurantOld = em.merge(restaurantOld);
            }
            if (restaurantNew != null && !restaurantNew.equals(restaurantOld)) {
                restaurantNew.getPhotoRestaurantList().add(photoRestaurant);
                restaurantNew = em.merge(restaurantNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PhotoRestaurantPK id = photoRestaurant.getPhotoRestaurantPK();
                if (findPhotoRestaurant(id) == null) {
                    throw new NonexistentEntityException("The photoRestaurant with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PhotoRestaurantPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PhotoRestaurant photoRestaurant;
            try {
                photoRestaurant = em.getReference(PhotoRestaurant.class, id);
                photoRestaurant.getPhotoRestaurantPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The photoRestaurant with id " + id + " no longer exists.", enfe);
            }
            Restaurant restaurant = photoRestaurant.getRestaurant();
            if (restaurant != null) {
                restaurant.getPhotoRestaurantList().remove(photoRestaurant);
                restaurant = em.merge(restaurant);
            }
            em.remove(photoRestaurant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PhotoRestaurant> findPhotoRestaurantEntities() {
        return findPhotoRestaurantEntities(true, -1, -1);
    }

    public List<PhotoRestaurant> findPhotoRestaurantEntities(int maxResults, int firstResult) {
        return findPhotoRestaurantEntities(false, maxResults, firstResult);
    }

    private List<PhotoRestaurant> findPhotoRestaurantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PhotoRestaurant.class));
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

    public PhotoRestaurant findPhotoRestaurant(PhotoRestaurantPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PhotoRestaurant.class, id);
        } finally {
            em.close();
        }
    }

    public int getPhotoRestaurantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PhotoRestaurant> rt = cq.from(PhotoRestaurant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
