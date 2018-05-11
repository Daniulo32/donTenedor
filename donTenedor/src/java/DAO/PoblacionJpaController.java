/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Poblacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Restaurant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class PoblacionJpaController implements Serializable {

    public PoblacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Poblacion poblacion) {
        if (poblacion.getRestaurantList() == null) {
            poblacion.setRestaurantList(new ArrayList<Restaurant>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Restaurant> attachedRestaurantList = new ArrayList<Restaurant>();
            for (Restaurant restaurantListRestaurantToAttach : poblacion.getRestaurantList()) {
                restaurantListRestaurantToAttach = em.getReference(restaurantListRestaurantToAttach.getClass(), restaurantListRestaurantToAttach.getIdRestaurant());
                attachedRestaurantList.add(restaurantListRestaurantToAttach);
            }
            poblacion.setRestaurantList(attachedRestaurantList);
            em.persist(poblacion);
            for (Restaurant restaurantListRestaurant : poblacion.getRestaurantList()) {
                Poblacion oldTownOfRestaurantListRestaurant = restaurantListRestaurant.getTown();
                restaurantListRestaurant.setTown(poblacion);
                restaurantListRestaurant = em.merge(restaurantListRestaurant);
                if (oldTownOfRestaurantListRestaurant != null) {
                    oldTownOfRestaurantListRestaurant.getRestaurantList().remove(restaurantListRestaurant);
                    oldTownOfRestaurantListRestaurant = em.merge(oldTownOfRestaurantListRestaurant);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Poblacion poblacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Poblacion persistentPoblacion = em.find(Poblacion.class, poblacion.getIdpoblacion());
            List<Restaurant> restaurantListOld = persistentPoblacion.getRestaurantList();
            List<Restaurant> restaurantListNew = poblacion.getRestaurantList();
            List<Restaurant> attachedRestaurantListNew = new ArrayList<Restaurant>();
            for (Restaurant restaurantListNewRestaurantToAttach : restaurantListNew) {
                restaurantListNewRestaurantToAttach = em.getReference(restaurantListNewRestaurantToAttach.getClass(), restaurantListNewRestaurantToAttach.getIdRestaurant());
                attachedRestaurantListNew.add(restaurantListNewRestaurantToAttach);
            }
            restaurantListNew = attachedRestaurantListNew;
            poblacion.setRestaurantList(restaurantListNew);
            poblacion = em.merge(poblacion);
            for (Restaurant restaurantListOldRestaurant : restaurantListOld) {
                if (!restaurantListNew.contains(restaurantListOldRestaurant)) {
                    restaurantListOldRestaurant.setTown(null);
                    restaurantListOldRestaurant = em.merge(restaurantListOldRestaurant);
                }
            }
            for (Restaurant restaurantListNewRestaurant : restaurantListNew) {
                if (!restaurantListOld.contains(restaurantListNewRestaurant)) {
                    Poblacion oldTownOfRestaurantListNewRestaurant = restaurantListNewRestaurant.getTown();
                    restaurantListNewRestaurant.setTown(poblacion);
                    restaurantListNewRestaurant = em.merge(restaurantListNewRestaurant);
                    if (oldTownOfRestaurantListNewRestaurant != null && !oldTownOfRestaurantListNewRestaurant.equals(poblacion)) {
                        oldTownOfRestaurantListNewRestaurant.getRestaurantList().remove(restaurantListNewRestaurant);
                        oldTownOfRestaurantListNewRestaurant = em.merge(oldTownOfRestaurantListNewRestaurant);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = poblacion.getIdpoblacion();
                if (findPoblacion(id) == null) {
                    throw new NonexistentEntityException("The poblacion with id " + id + " no longer exists.");
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
            Poblacion poblacion;
            try {
                poblacion = em.getReference(Poblacion.class, id);
                poblacion.getIdpoblacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The poblacion with id " + id + " no longer exists.", enfe);
            }
            List<Restaurant> restaurantList = poblacion.getRestaurantList();
            for (Restaurant restaurantListRestaurant : restaurantList) {
                restaurantListRestaurant.setTown(null);
                restaurantListRestaurant = em.merge(restaurantListRestaurant);
            }
            em.remove(poblacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Poblacion> findPoblacionEntities() {
        return findPoblacionEntities(true, -1, -1);
    }

    public List<Poblacion> findPoblacionEntities(int maxResults, int firstResult) {
        return findPoblacionEntities(false, maxResults, firstResult);
    }

    private List<Poblacion> findPoblacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Poblacion.class));
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

    public Poblacion findPoblacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Poblacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPoblacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Poblacion> rt = cq.from(Poblacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Poblacion> getPoblacionByProvincia(int idProvincia) {
        EntityManager em = getEntityManager();
        List<Poblacion> listaPoblaciones = new ArrayList();

        Query query = em.createNamedQuery("Poblacion.findByIdprovincia");
        query.setParameter("idprovincia", idProvincia);

        try {
            List lista = query.getResultList();
            for (Object poblacion : lista) {
                listaPoblaciones.add((Poblacion) poblacion);
            }
        } catch (Exception e) {
        }

        return listaPoblaciones;
    }
    
    public Poblacion getPoblacionByNamePoblacion(String poblacion){
        EntityManager em = getEntityManager();
        
        Poblacion pueblo = null;
        Query query = em.createNamedQuery("Poblacion.findByPoblacion");
        query.setParameter("poblacion", poblacion);
        
        try {
            List lista = query.getResultList();
            if (lista.size() > 0) {
                pueblo = (Poblacion) lista.get(0);
            }
        } catch (Exception e) {
        }
        
        return pueblo;
    }

}
