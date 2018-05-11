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
import DTO.Restaurant;
import DTO.Users;
import DTO.Voting;
import DTO.VotingPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class VotingJpaController implements Serializable {

    public VotingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Voting voting) throws PreexistingEntityException, Exception {
        if (voting.getVotingPK() == null) {
            voting.setVotingPK(new VotingPK());
        }
        voting.getVotingPK().setIdUser(voting.getUsers().getIdUser());
        voting.getVotingPK().setIdRestaurant(voting.getRestaurant().getIdRestaurant());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant restaurant = voting.getRestaurant();
            if (restaurant != null) {
                restaurant = em.getReference(restaurant.getClass(), restaurant.getIdRestaurant());
                voting.setRestaurant(restaurant);
            }
            Users users = voting.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIdUser());
                voting.setUsers(users);
            }
            em.persist(voting);
            if (restaurant != null) {
                restaurant.getVotingList().add(voting);
                restaurant = em.merge(restaurant);
            }
            if (users != null) {
                users.getVotingList().add(voting);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVoting(voting.getVotingPK()) != null) {
                throw new PreexistingEntityException("Voting " + voting + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Voting voting) throws NonexistentEntityException, Exception {
        voting.getVotingPK().setIdUser(voting.getUsers().getIdUser());
        voting.getVotingPK().setIdRestaurant(voting.getRestaurant().getIdRestaurant());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Voting persistentVoting = em.find(Voting.class, voting.getVotingPK());
            Restaurant restaurantOld = persistentVoting.getRestaurant();
            Restaurant restaurantNew = voting.getRestaurant();
            Users usersOld = persistentVoting.getUsers();
            Users usersNew = voting.getUsers();
            if (restaurantNew != null) {
                restaurantNew = em.getReference(restaurantNew.getClass(), restaurantNew.getIdRestaurant());
                voting.setRestaurant(restaurantNew);
            }
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIdUser());
                voting.setUsers(usersNew);
            }
            voting = em.merge(voting);
            if (restaurantOld != null && !restaurantOld.equals(restaurantNew)) {
                restaurantOld.getVotingList().remove(voting);
                restaurantOld = em.merge(restaurantOld);
            }
            if (restaurantNew != null && !restaurantNew.equals(restaurantOld)) {
                restaurantNew.getVotingList().add(voting);
                restaurantNew = em.merge(restaurantNew);
            }
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getVotingList().remove(voting);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getVotingList().add(voting);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VotingPK id = voting.getVotingPK();
                if (findVoting(id) == null) {
                    throw new NonexistentEntityException("The voting with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VotingPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Voting voting;
            try {
                voting = em.getReference(Voting.class, id);
                voting.getVotingPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The voting with id " + id + " no longer exists.", enfe);
            }
            Restaurant restaurant = voting.getRestaurant();
            if (restaurant != null) {
                restaurant.getVotingList().remove(voting);
                restaurant = em.merge(restaurant);
            }
            Users users = voting.getUsers();
            if (users != null) {
                users.getVotingList().remove(voting);
                users = em.merge(users);
            }
            em.remove(voting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Voting> findVotingEntities() {
        return findVotingEntities(true, -1, -1);
    }

    public List<Voting> findVotingEntities(int maxResults, int firstResult) {
        return findVotingEntities(false, maxResults, firstResult);
    }

    private List<Voting> findVotingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Voting.class));
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

    public Voting findVoting(VotingPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Voting.class, id);
        } finally {
            em.close();
        }
    }

    public int getVotingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Voting> rt = cq.from(Voting.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
