/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Reservations;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Restaurant;
import DTO.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class ReservationsJpaController implements Serializable {

    public ReservationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservations reservations) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant idRestaurant = reservations.getIdRestaurant();
            if (idRestaurant != null) {
                idRestaurant = em.getReference(idRestaurant.getClass(), idRestaurant.getIdRestaurant());
                reservations.setIdRestaurant(idRestaurant);
            }
            Users idUser = reservations.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                reservations.setIdUser(idUser);
            }
            em.persist(reservations);
            if (idRestaurant != null) {
                idRestaurant.getReservationsList().add(reservations);
                idRestaurant = em.merge(idRestaurant);
            }
            if (idUser != null) {
                idUser.getReservationsList().add(reservations);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservations reservations) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservations persistentReservations = em.find(Reservations.class, reservations.getIdReservation());
            Restaurant idRestaurantOld = persistentReservations.getIdRestaurant();
            Restaurant idRestaurantNew = reservations.getIdRestaurant();
            Users idUserOld = persistentReservations.getIdUser();
            Users idUserNew = reservations.getIdUser();
            if (idRestaurantNew != null) {
                idRestaurantNew = em.getReference(idRestaurantNew.getClass(), idRestaurantNew.getIdRestaurant());
                reservations.setIdRestaurant(idRestaurantNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                reservations.setIdUser(idUserNew);
            }
            reservations = em.merge(reservations);
            if (idRestaurantOld != null && !idRestaurantOld.equals(idRestaurantNew)) {
                idRestaurantOld.getReservationsList().remove(reservations);
                idRestaurantOld = em.merge(idRestaurantOld);
            }
            if (idRestaurantNew != null && !idRestaurantNew.equals(idRestaurantOld)) {
                idRestaurantNew.getReservationsList().add(reservations);
                idRestaurantNew = em.merge(idRestaurantNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getReservationsList().remove(reservations);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getReservationsList().add(reservations);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservations.getIdReservation();
                if (findReservations(id) == null) {
                    throw new NonexistentEntityException("The reservations with id " + id + " no longer exists.");
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
            Reservations reservations;
            try {
                reservations = em.getReference(Reservations.class, id);
                reservations.getIdReservation();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservations with id " + id + " no longer exists.", enfe);
            }
            Restaurant idRestaurant = reservations.getIdRestaurant();
            if (idRestaurant != null) {
                idRestaurant.getReservationsList().remove(reservations);
                idRestaurant = em.merge(idRestaurant);
            }
            Users idUser = reservations.getIdUser();
            if (idUser != null) {
                idUser.getReservationsList().remove(reservations);
                idUser = em.merge(idUser);
            }
            em.remove(reservations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservations> findReservationsEntities() {
        return findReservationsEntities(true, -1, -1);
    }

    public List<Reservations> findReservationsEntities(int maxResults, int firstResult) {
        return findReservationsEntities(false, maxResults, firstResult);
    }

    private List<Reservations> findReservationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservations.class));
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

    public Reservations findReservations(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservations.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservations> rt = cq.from(Reservations.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
