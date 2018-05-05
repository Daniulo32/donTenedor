/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.BannedUsers;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class BannedUsersJpaController implements Serializable {

    public BannedUsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BannedUsers bannedUsers) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Users usersOrphanCheck = bannedUsers.getUsers();
        if (usersOrphanCheck != null) {
            BannedUsers oldBannedUsersOfUsers = usersOrphanCheck.getBannedUsers();
            if (oldBannedUsersOfUsers != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Users " + usersOrphanCheck + " already has an item of type BannedUsers whose users column cannot be null. Please make another selection for the users field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = bannedUsers.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIdUser());
                bannedUsers.setUsers(users);
            }
            em.persist(bannedUsers);
            if (users != null) {
                users.setBannedUsers(bannedUsers);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBannedUsers(bannedUsers.getIdUser()) != null) {
                throw new PreexistingEntityException("BannedUsers " + bannedUsers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BannedUsers bannedUsers) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BannedUsers persistentBannedUsers = em.find(BannedUsers.class, bannedUsers.getIdUser());
            Users usersOld = persistentBannedUsers.getUsers();
            Users usersNew = bannedUsers.getUsers();
            List<String> illegalOrphanMessages = null;
            if (usersNew != null && !usersNew.equals(usersOld)) {
                BannedUsers oldBannedUsersOfUsers = usersNew.getBannedUsers();
                if (oldBannedUsersOfUsers != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Users " + usersNew + " already has an item of type BannedUsers whose users column cannot be null. Please make another selection for the users field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIdUser());
                bannedUsers.setUsers(usersNew);
            }
            bannedUsers = em.merge(bannedUsers);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.setBannedUsers(null);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.setBannedUsers(bannedUsers);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bannedUsers.getIdUser();
                if (findBannedUsers(id) == null) {
                    throw new NonexistentEntityException("The bannedUsers with id " + id + " no longer exists.");
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
            BannedUsers bannedUsers;
            try {
                bannedUsers = em.getReference(BannedUsers.class, id);
                bannedUsers.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bannedUsers with id " + id + " no longer exists.", enfe);
            }
            Users users = bannedUsers.getUsers();
            if (users != null) {
                users.setBannedUsers(null);
                users = em.merge(users);
            }
            em.remove(bannedUsers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BannedUsers> findBannedUsersEntities() {
        return findBannedUsersEntities(true, -1, -1);
    }

    public List<BannedUsers> findBannedUsersEntities(int maxResults, int firstResult) {
        return findBannedUsersEntities(false, maxResults, firstResult);
    }

    private List<BannedUsers> findBannedUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BannedUsers.class));
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

    public BannedUsers findBannedUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BannedUsers.class, id);
        } finally {
            em.close();
        }
    }

    public int getBannedUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BannedUsers> rt = cq.from(BannedUsers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
