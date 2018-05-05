/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.BannedUsers;
import DTO.Comments;
import java.util.ArrayList;
import java.util.List;
import DTO.Reservations;
import DTO.Restaurant;
import DTO.Users;
import DTO.Voting;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getCommentsList() == null) {
            users.setCommentsList(new ArrayList<Comments>());
        }
        if (users.getReservationsList() == null) {
            users.setReservationsList(new ArrayList<Reservations>());
        }
        if (users.getRestaurantList() == null) {
            users.setRestaurantList(new ArrayList<Restaurant>());
        }
        if (users.getVotingList() == null) {
            users.setVotingList(new ArrayList<Voting>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BannedUsers bannedUsers = users.getBannedUsers();
            if (bannedUsers != null) {
                bannedUsers = em.getReference(bannedUsers.getClass(), bannedUsers.getIdUser());
                users.setBannedUsers(bannedUsers);
            }
            List<Comments> attachedCommentsList = new ArrayList<Comments>();
            for (Comments commentsListCommentsToAttach : users.getCommentsList()) {
                commentsListCommentsToAttach = em.getReference(commentsListCommentsToAttach.getClass(), commentsListCommentsToAttach.getIdComment());
                attachedCommentsList.add(commentsListCommentsToAttach);
            }
            users.setCommentsList(attachedCommentsList);
            List<Reservations> attachedReservationsList = new ArrayList<Reservations>();
            for (Reservations reservationsListReservationsToAttach : users.getReservationsList()) {
                reservationsListReservationsToAttach = em.getReference(reservationsListReservationsToAttach.getClass(), reservationsListReservationsToAttach.getIdReservation());
                attachedReservationsList.add(reservationsListReservationsToAttach);
            }
            users.setReservationsList(attachedReservationsList);
            List<Restaurant> attachedRestaurantList = new ArrayList<Restaurant>();
            for (Restaurant restaurantListRestaurantToAttach : users.getRestaurantList()) {
                restaurantListRestaurantToAttach = em.getReference(restaurantListRestaurantToAttach.getClass(), restaurantListRestaurantToAttach.getIdRestaurant());
                attachedRestaurantList.add(restaurantListRestaurantToAttach);
            }
            users.setRestaurantList(attachedRestaurantList);
            List<Voting> attachedVotingList = new ArrayList<Voting>();
            for (Voting votingListVotingToAttach : users.getVotingList()) {
                votingListVotingToAttach = em.getReference(votingListVotingToAttach.getClass(), votingListVotingToAttach.getVotingPK());
                attachedVotingList.add(votingListVotingToAttach);
            }
            users.setVotingList(attachedVotingList);
            em.persist(users);
            if (bannedUsers != null) {
                Users oldUsersOfBannedUsers = bannedUsers.getUsers();
                if (oldUsersOfBannedUsers != null) {
                    oldUsersOfBannedUsers.setBannedUsers(null);
                    oldUsersOfBannedUsers = em.merge(oldUsersOfBannedUsers);
                }
                bannedUsers.setUsers(users);
                bannedUsers = em.merge(bannedUsers);
            }
            for (Comments commentsListComments : users.getCommentsList()) {
                Users oldIdUserOfCommentsListComments = commentsListComments.getIdUser();
                commentsListComments.setIdUser(users);
                commentsListComments = em.merge(commentsListComments);
                if (oldIdUserOfCommentsListComments != null) {
                    oldIdUserOfCommentsListComments.getCommentsList().remove(commentsListComments);
                    oldIdUserOfCommentsListComments = em.merge(oldIdUserOfCommentsListComments);
                }
            }
            for (Reservations reservationsListReservations : users.getReservationsList()) {
                Users oldIdUserOfReservationsListReservations = reservationsListReservations.getIdUser();
                reservationsListReservations.setIdUser(users);
                reservationsListReservations = em.merge(reservationsListReservations);
                if (oldIdUserOfReservationsListReservations != null) {
                    oldIdUserOfReservationsListReservations.getReservationsList().remove(reservationsListReservations);
                    oldIdUserOfReservationsListReservations = em.merge(oldIdUserOfReservationsListReservations);
                }
            }
            for (Restaurant restaurantListRestaurant : users.getRestaurantList()) {
                Users oldIdAdminOfRestaurantListRestaurant = restaurantListRestaurant.getIdAdmin();
                restaurantListRestaurant.setIdAdmin(users);
                restaurantListRestaurant = em.merge(restaurantListRestaurant);
                if (oldIdAdminOfRestaurantListRestaurant != null) {
                    oldIdAdminOfRestaurantListRestaurant.getRestaurantList().remove(restaurantListRestaurant);
                    oldIdAdminOfRestaurantListRestaurant = em.merge(oldIdAdminOfRestaurantListRestaurant);
                }
            }
            for (Voting votingListVoting : users.getVotingList()) {
                Users oldUsersOfVotingListVoting = votingListVoting.getUsers();
                votingListVoting.setUsers(users);
                votingListVoting = em.merge(votingListVoting);
                if (oldUsersOfVotingListVoting != null) {
                    oldUsersOfVotingListVoting.getVotingList().remove(votingListVoting);
                    oldUsersOfVotingListVoting = em.merge(oldUsersOfVotingListVoting);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getIdUser());
            BannedUsers bannedUsersOld = persistentUsers.getBannedUsers();
            BannedUsers bannedUsersNew = users.getBannedUsers();
            List<Comments> commentsListOld = persistentUsers.getCommentsList();
            List<Comments> commentsListNew = users.getCommentsList();
            List<Reservations> reservationsListOld = persistentUsers.getReservationsList();
            List<Reservations> reservationsListNew = users.getReservationsList();
            List<Restaurant> restaurantListOld = persistentUsers.getRestaurantList();
            List<Restaurant> restaurantListNew = users.getRestaurantList();
            List<Voting> votingListOld = persistentUsers.getVotingList();
            List<Voting> votingListNew = users.getVotingList();
            List<String> illegalOrphanMessages = null;
            if (bannedUsersOld != null && !bannedUsersOld.equals(bannedUsersNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain BannedUsers " + bannedUsersOld + " since its users field is not nullable.");
            }
            for (Comments commentsListOldComments : commentsListOld) {
                if (!commentsListNew.contains(commentsListOldComments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comments " + commentsListOldComments + " since its idUser field is not nullable.");
                }
            }
            for (Restaurant restaurantListOldRestaurant : restaurantListOld) {
                if (!restaurantListNew.contains(restaurantListOldRestaurant)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Restaurant " + restaurantListOldRestaurant + " since its idAdmin field is not nullable.");
                }
            }
            for (Voting votingListOldVoting : votingListOld) {
                if (!votingListNew.contains(votingListOldVoting)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Voting " + votingListOldVoting + " since its users field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (bannedUsersNew != null) {
                bannedUsersNew = em.getReference(bannedUsersNew.getClass(), bannedUsersNew.getIdUser());
                users.setBannedUsers(bannedUsersNew);
            }
            List<Comments> attachedCommentsListNew = new ArrayList<Comments>();
            for (Comments commentsListNewCommentsToAttach : commentsListNew) {
                commentsListNewCommentsToAttach = em.getReference(commentsListNewCommentsToAttach.getClass(), commentsListNewCommentsToAttach.getIdComment());
                attachedCommentsListNew.add(commentsListNewCommentsToAttach);
            }
            commentsListNew = attachedCommentsListNew;
            users.setCommentsList(commentsListNew);
            List<Reservations> attachedReservationsListNew = new ArrayList<Reservations>();
            for (Reservations reservationsListNewReservationsToAttach : reservationsListNew) {
                reservationsListNewReservationsToAttach = em.getReference(reservationsListNewReservationsToAttach.getClass(), reservationsListNewReservationsToAttach.getIdReservation());
                attachedReservationsListNew.add(reservationsListNewReservationsToAttach);
            }
            reservationsListNew = attachedReservationsListNew;
            users.setReservationsList(reservationsListNew);
            List<Restaurant> attachedRestaurantListNew = new ArrayList<Restaurant>();
            for (Restaurant restaurantListNewRestaurantToAttach : restaurantListNew) {
                restaurantListNewRestaurantToAttach = em.getReference(restaurantListNewRestaurantToAttach.getClass(), restaurantListNewRestaurantToAttach.getIdRestaurant());
                attachedRestaurantListNew.add(restaurantListNewRestaurantToAttach);
            }
            restaurantListNew = attachedRestaurantListNew;
            users.setRestaurantList(restaurantListNew);
            List<Voting> attachedVotingListNew = new ArrayList<Voting>();
            for (Voting votingListNewVotingToAttach : votingListNew) {
                votingListNewVotingToAttach = em.getReference(votingListNewVotingToAttach.getClass(), votingListNewVotingToAttach.getVotingPK());
                attachedVotingListNew.add(votingListNewVotingToAttach);
            }
            votingListNew = attachedVotingListNew;
            users.setVotingList(votingListNew);
            users = em.merge(users);
            if (bannedUsersNew != null && !bannedUsersNew.equals(bannedUsersOld)) {
                Users oldUsersOfBannedUsers = bannedUsersNew.getUsers();
                if (oldUsersOfBannedUsers != null) {
                    oldUsersOfBannedUsers.setBannedUsers(null);
                    oldUsersOfBannedUsers = em.merge(oldUsersOfBannedUsers);
                }
                bannedUsersNew.setUsers(users);
                bannedUsersNew = em.merge(bannedUsersNew);
            }
            for (Comments commentsListNewComments : commentsListNew) {
                if (!commentsListOld.contains(commentsListNewComments)) {
                    Users oldIdUserOfCommentsListNewComments = commentsListNewComments.getIdUser();
                    commentsListNewComments.setIdUser(users);
                    commentsListNewComments = em.merge(commentsListNewComments);
                    if (oldIdUserOfCommentsListNewComments != null && !oldIdUserOfCommentsListNewComments.equals(users)) {
                        oldIdUserOfCommentsListNewComments.getCommentsList().remove(commentsListNewComments);
                        oldIdUserOfCommentsListNewComments = em.merge(oldIdUserOfCommentsListNewComments);
                    }
                }
            }
            for (Reservations reservationsListOldReservations : reservationsListOld) {
                if (!reservationsListNew.contains(reservationsListOldReservations)) {
                    reservationsListOldReservations.setIdUser(null);
                    reservationsListOldReservations = em.merge(reservationsListOldReservations);
                }
            }
            for (Reservations reservationsListNewReservations : reservationsListNew) {
                if (!reservationsListOld.contains(reservationsListNewReservations)) {
                    Users oldIdUserOfReservationsListNewReservations = reservationsListNewReservations.getIdUser();
                    reservationsListNewReservations.setIdUser(users);
                    reservationsListNewReservations = em.merge(reservationsListNewReservations);
                    if (oldIdUserOfReservationsListNewReservations != null && !oldIdUserOfReservationsListNewReservations.equals(users)) {
                        oldIdUserOfReservationsListNewReservations.getReservationsList().remove(reservationsListNewReservations);
                        oldIdUserOfReservationsListNewReservations = em.merge(oldIdUserOfReservationsListNewReservations);
                    }
                }
            }
            for (Restaurant restaurantListNewRestaurant : restaurantListNew) {
                if (!restaurantListOld.contains(restaurantListNewRestaurant)) {
                    Users oldIdAdminOfRestaurantListNewRestaurant = restaurantListNewRestaurant.getIdAdmin();
                    restaurantListNewRestaurant.setIdAdmin(users);
                    restaurantListNewRestaurant = em.merge(restaurantListNewRestaurant);
                    if (oldIdAdminOfRestaurantListNewRestaurant != null && !oldIdAdminOfRestaurantListNewRestaurant.equals(users)) {
                        oldIdAdminOfRestaurantListNewRestaurant.getRestaurantList().remove(restaurantListNewRestaurant);
                        oldIdAdminOfRestaurantListNewRestaurant = em.merge(oldIdAdminOfRestaurantListNewRestaurant);
                    }
                }
            }
            for (Voting votingListNewVoting : votingListNew) {
                if (!votingListOld.contains(votingListNewVoting)) {
                    Users oldUsersOfVotingListNewVoting = votingListNewVoting.getUsers();
                    votingListNewVoting.setUsers(users);
                    votingListNewVoting = em.merge(votingListNewVoting);
                    if (oldUsersOfVotingListNewVoting != null && !oldUsersOfVotingListNewVoting.equals(users)) {
                        oldUsersOfVotingListNewVoting.getVotingList().remove(votingListNewVoting);
                        oldUsersOfVotingListNewVoting = em.merge(oldUsersOfVotingListNewVoting);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getIdUser();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            BannedUsers bannedUsersOrphanCheck = users.getBannedUsers();
            if (bannedUsersOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the BannedUsers " + bannedUsersOrphanCheck + " in its bannedUsers field has a non-nullable users field.");
            }
            List<Comments> commentsListOrphanCheck = users.getCommentsList();
            for (Comments commentsListOrphanCheckComments : commentsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Comments " + commentsListOrphanCheckComments + " in its commentsList field has a non-nullable idUser field.");
            }
            List<Restaurant> restaurantListOrphanCheck = users.getRestaurantList();
            for (Restaurant restaurantListOrphanCheckRestaurant : restaurantListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Restaurant " + restaurantListOrphanCheckRestaurant + " in its restaurantList field has a non-nullable idAdmin field.");
            }
            List<Voting> votingListOrphanCheck = users.getVotingList();
            for (Voting votingListOrphanCheckVoting : votingListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Voting " + votingListOrphanCheckVoting + " in its votingList field has a non-nullable users field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Reservations> reservationsList = users.getReservationsList();
            for (Reservations reservationsListReservations : reservationsList) {
                reservationsListReservations.setIdUser(null);
                reservationsListReservations = em.merge(reservationsListReservations);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Users getUser(String usuario) {

        EntityManager em = getEntityManager();

        Users user = null;

        Query query = em.createNamedQuery("Users.findByEmail");
        query.setParameter("email", usuario);

        try {
            List lista = query.getResultList();
            if (lista.size() > 0) {
                user = (Users) lista.get(0);
            }
        } finally {
            em.close();
        }
        return user;
    }

}
