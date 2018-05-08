/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Provincia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Restaurant;
import DTO.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class ProvinciaJpaController implements Serializable {

    public ProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) {
        if (provincia.getRestaurantList() == null) {
            provincia.setRestaurantList(new ArrayList<Restaurant>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Restaurant> attachedRestaurantList = new ArrayList<Restaurant>();
            for (Restaurant restaurantListRestaurantToAttach : provincia.getRestaurantList()) {
                restaurantListRestaurantToAttach = em.getReference(restaurantListRestaurantToAttach.getClass(), restaurantListRestaurantToAttach.getIdRestaurant());
                attachedRestaurantList.add(restaurantListRestaurantToAttach);
            }
            provincia.setRestaurantList(attachedRestaurantList);
            em.persist(provincia);
            for (Restaurant restaurantListRestaurant : provincia.getRestaurantList()) {
                Provincia oldProvinceOfRestaurantListRestaurant = restaurantListRestaurant.getProvince();
                restaurantListRestaurant.setProvince(provincia);
                restaurantListRestaurant = em.merge(restaurantListRestaurant);
                if (oldProvinceOfRestaurantListRestaurant != null) {
                    oldProvinceOfRestaurantListRestaurant.getRestaurantList().remove(restaurantListRestaurant);
                    oldProvinceOfRestaurantListRestaurant = em.merge(oldProvinceOfRestaurantListRestaurant);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdprovincia());
            List<Restaurant> restaurantListOld = persistentProvincia.getRestaurantList();
            List<Restaurant> restaurantListNew = provincia.getRestaurantList();
            List<Restaurant> attachedRestaurantListNew = new ArrayList<Restaurant>();
            for (Restaurant restaurantListNewRestaurantToAttach : restaurantListNew) {
                restaurantListNewRestaurantToAttach = em.getReference(restaurantListNewRestaurantToAttach.getClass(), restaurantListNewRestaurantToAttach.getIdRestaurant());
                attachedRestaurantListNew.add(restaurantListNewRestaurantToAttach);
            }
            restaurantListNew = attachedRestaurantListNew;
            provincia.setRestaurantList(restaurantListNew);
            provincia = em.merge(provincia);
            for (Restaurant restaurantListOldRestaurant : restaurantListOld) {
                if (!restaurantListNew.contains(restaurantListOldRestaurant)) {
                    restaurantListOldRestaurant.setProvince(null);
                    restaurantListOldRestaurant = em.merge(restaurantListOldRestaurant);
                }
            }
            for (Restaurant restaurantListNewRestaurant : restaurantListNew) {
                if (!restaurantListOld.contains(restaurantListNewRestaurant)) {
                    Provincia oldProvinceOfRestaurantListNewRestaurant = restaurantListNewRestaurant.getProvince();
                    restaurantListNewRestaurant.setProvince(provincia);
                    restaurantListNewRestaurant = em.merge(restaurantListNewRestaurant);
                    if (oldProvinceOfRestaurantListNewRestaurant != null && !oldProvinceOfRestaurantListNewRestaurant.equals(provincia)) {
                        oldProvinceOfRestaurantListNewRestaurant.getRestaurantList().remove(restaurantListNewRestaurant);
                        oldProvinceOfRestaurantListNewRestaurant = em.merge(oldProvinceOfRestaurantListNewRestaurant);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provincia.getIdprovincia();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdprovincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            List<Restaurant> restaurantList = provincia.getRestaurantList();
            for (Restaurant restaurantListRestaurant : restaurantList) {
                restaurantListRestaurant.setProvince(null);
                restaurantListRestaurant = em.merge(restaurantListRestaurant);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List getAllProvincias() {
        EntityManager em = getEntityManager();

        Query query = em.createNamedQuery("Provincia.findAll");
        List listaProvincias = new ArrayList();
        try {
            List lista = query.getResultList();
            for(Object provincia : lista){
                listaProvincias.add((Provincia)provincia);
            }
        } finally {
            em.close();
        }
        return listaProvincias;
    }

}
