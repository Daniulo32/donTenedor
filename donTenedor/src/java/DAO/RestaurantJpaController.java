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
import DTO.Users;
import DTO.Poblacion;
import DTO.Provincia;
import DTO.Offers;
import java.util.ArrayList;
import java.util.List;
import DTO.Comments;
import DTO.Reservations;
import DTO.PhotoRestaurant;
import DTO.Restaurant;
import DTO.Voting;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author danieljimenez
 */
public class RestaurantJpaController implements Serializable {

    public RestaurantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Restaurant restaurant) {
        if (restaurant.getOffersList() == null) {
            restaurant.setOffersList(new ArrayList<Offers>());
        }
        if (restaurant.getCommentsList() == null) {
            restaurant.setCommentsList(new ArrayList<Comments>());
        }
        if (restaurant.getReservationsList() == null) {
            restaurant.setReservationsList(new ArrayList<Reservations>());
        }
        if (restaurant.getPhotoRestaurantList() == null) {
            restaurant.setPhotoRestaurantList(new ArrayList<PhotoRestaurant>());
        }
        if (restaurant.getVotingList() == null) {
            restaurant.setVotingList(new ArrayList<Voting>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users idAdmin = restaurant.getIdAdmin();
            if (idAdmin != null) {
                idAdmin = em.getReference(idAdmin.getClass(), idAdmin.getIdUser());
                restaurant.setIdAdmin(idAdmin);
            }
            Poblacion town = restaurant.getTown();
            if (town != null) {
                town = em.getReference(town.getClass(), town.getIdpoblacion());
                restaurant.setTown(town);
            }
            Provincia province = restaurant.getProvince();
            if (province != null) {
                province = em.getReference(province.getClass(), province.getIdprovincia());
                restaurant.setProvince(province);
            }
            List<Offers> attachedOffersList = new ArrayList<Offers>();
            for (Offers offersListOffersToAttach : restaurant.getOffersList()) {
                offersListOffersToAttach = em.getReference(offersListOffersToAttach.getClass(), offersListOffersToAttach.getIdOffer());
                attachedOffersList.add(offersListOffersToAttach);
            }
            restaurant.setOffersList(attachedOffersList);
            List<Comments> attachedCommentsList = new ArrayList<Comments>();
            for (Comments commentsListCommentsToAttach : restaurant.getCommentsList()) {
                commentsListCommentsToAttach = em.getReference(commentsListCommentsToAttach.getClass(), commentsListCommentsToAttach.getIdComment());
                attachedCommentsList.add(commentsListCommentsToAttach);
            }
            restaurant.setCommentsList(attachedCommentsList);
            List<Reservations> attachedReservationsList = new ArrayList<Reservations>();
            for (Reservations reservationsListReservationsToAttach : restaurant.getReservationsList()) {
                reservationsListReservationsToAttach = em.getReference(reservationsListReservationsToAttach.getClass(), reservationsListReservationsToAttach.getIdReservation());
                attachedReservationsList.add(reservationsListReservationsToAttach);
            }
            restaurant.setReservationsList(attachedReservationsList);
            List<PhotoRestaurant> attachedPhotoRestaurantList = new ArrayList<PhotoRestaurant>();
            for (PhotoRestaurant photoRestaurantListPhotoRestaurantToAttach : restaurant.getPhotoRestaurantList()) {
                photoRestaurantListPhotoRestaurantToAttach = em.getReference(photoRestaurantListPhotoRestaurantToAttach.getClass(), photoRestaurantListPhotoRestaurantToAttach.getPhotoRestaurantPK());
                attachedPhotoRestaurantList.add(photoRestaurantListPhotoRestaurantToAttach);
            }
            restaurant.setPhotoRestaurantList(attachedPhotoRestaurantList);
            List<Voting> attachedVotingList = new ArrayList<Voting>();
            for (Voting votingListVotingToAttach : restaurant.getVotingList()) {
                votingListVotingToAttach = em.getReference(votingListVotingToAttach.getClass(), votingListVotingToAttach.getVotingPK());
                attachedVotingList.add(votingListVotingToAttach);
            }
            restaurant.setVotingList(attachedVotingList);
            em.persist(restaurant);
            if (idAdmin != null) {
                idAdmin.getRestaurantList().add(restaurant);
                idAdmin = em.merge(idAdmin);
            }
            if (town != null) {
                town.getRestaurantList().add(restaurant);
                town = em.merge(town);
            }
            if (province != null) {
                province.getRestaurantList().add(restaurant);
                province = em.merge(province);
            }
            for (Offers offersListOffers : restaurant.getOffersList()) {
                Restaurant oldIdRestaurantOfOffersListOffers = offersListOffers.getIdRestaurant();
                offersListOffers.setIdRestaurant(restaurant);
                offersListOffers = em.merge(offersListOffers);
                if (oldIdRestaurantOfOffersListOffers != null) {
                    oldIdRestaurantOfOffersListOffers.getOffersList().remove(offersListOffers);
                    oldIdRestaurantOfOffersListOffers = em.merge(oldIdRestaurantOfOffersListOffers);
                }
            }
            for (Comments commentsListComments : restaurant.getCommentsList()) {
                Restaurant oldIdRestaurantOfCommentsListComments = commentsListComments.getIdRestaurant();
                commentsListComments.setIdRestaurant(restaurant);
                commentsListComments = em.merge(commentsListComments);
                if (oldIdRestaurantOfCommentsListComments != null) {
                    oldIdRestaurantOfCommentsListComments.getCommentsList().remove(commentsListComments);
                    oldIdRestaurantOfCommentsListComments = em.merge(oldIdRestaurantOfCommentsListComments);
                }
            }
            for (Reservations reservationsListReservations : restaurant.getReservationsList()) {
                Restaurant oldIdRestaurantOfReservationsListReservations = reservationsListReservations.getIdRestaurant();
                reservationsListReservations.setIdRestaurant(restaurant);
                reservationsListReservations = em.merge(reservationsListReservations);
                if (oldIdRestaurantOfReservationsListReservations != null) {
                    oldIdRestaurantOfReservationsListReservations.getReservationsList().remove(reservationsListReservations);
                    oldIdRestaurantOfReservationsListReservations = em.merge(oldIdRestaurantOfReservationsListReservations);
                }
            }
            for (PhotoRestaurant photoRestaurantListPhotoRestaurant : restaurant.getPhotoRestaurantList()) {
                Restaurant oldRestaurantOfPhotoRestaurantListPhotoRestaurant = photoRestaurantListPhotoRestaurant.getRestaurant();
                photoRestaurantListPhotoRestaurant.setRestaurant(restaurant);
                photoRestaurantListPhotoRestaurant = em.merge(photoRestaurantListPhotoRestaurant);
                if (oldRestaurantOfPhotoRestaurantListPhotoRestaurant != null) {
                    oldRestaurantOfPhotoRestaurantListPhotoRestaurant.getPhotoRestaurantList().remove(photoRestaurantListPhotoRestaurant);
                    oldRestaurantOfPhotoRestaurantListPhotoRestaurant = em.merge(oldRestaurantOfPhotoRestaurantListPhotoRestaurant);
                }
            }
            for (Voting votingListVoting : restaurant.getVotingList()) {
                Restaurant oldRestaurantOfVotingListVoting = votingListVoting.getRestaurant();
                votingListVoting.setRestaurant(restaurant);
                votingListVoting = em.merge(votingListVoting);
                if (oldRestaurantOfVotingListVoting != null) {
                    oldRestaurantOfVotingListVoting.getVotingList().remove(votingListVoting);
                    oldRestaurantOfVotingListVoting = em.merge(oldRestaurantOfVotingListVoting);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Restaurant restaurant) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant persistentRestaurant = em.find(Restaurant.class, restaurant.getIdRestaurant());
            Users idAdminOld = persistentRestaurant.getIdAdmin();
            Users idAdminNew = restaurant.getIdAdmin();
            Poblacion townOld = persistentRestaurant.getTown();
            Poblacion townNew = restaurant.getTown();
            Provincia provinceOld = persistentRestaurant.getProvince();
            Provincia provinceNew = restaurant.getProvince();
            List<Offers> offersListOld = persistentRestaurant.getOffersList();
            List<Offers> offersListNew = restaurant.getOffersList();
            List<Comments> commentsListOld = persistentRestaurant.getCommentsList();
            List<Comments> commentsListNew = restaurant.getCommentsList();
            List<Reservations> reservationsListOld = persistentRestaurant.getReservationsList();
            List<Reservations> reservationsListNew = restaurant.getReservationsList();
            List<PhotoRestaurant> photoRestaurantListOld = persistentRestaurant.getPhotoRestaurantList();
            List<PhotoRestaurant> photoRestaurantListNew = restaurant.getPhotoRestaurantList();
            List<Voting> votingListOld = persistentRestaurant.getVotingList();
            List<Voting> votingListNew = restaurant.getVotingList();
            List<String> illegalOrphanMessages = null;
            for (Offers offersListOldOffers : offersListOld) {
                if (!offersListNew.contains(offersListOldOffers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Offers " + offersListOldOffers + " since its idRestaurant field is not nullable.");
                }
            }
            for (Comments commentsListOldComments : commentsListOld) {
                if (!commentsListNew.contains(commentsListOldComments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comments " + commentsListOldComments + " since its idRestaurant field is not nullable.");
                }
            }
            for (PhotoRestaurant photoRestaurantListOldPhotoRestaurant : photoRestaurantListOld) {
                if (!photoRestaurantListNew.contains(photoRestaurantListOldPhotoRestaurant)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PhotoRestaurant " + photoRestaurantListOldPhotoRestaurant + " since its restaurant field is not nullable.");
                }
            }
            for (Voting votingListOldVoting : votingListOld) {
                if (!votingListNew.contains(votingListOldVoting)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Voting " + votingListOldVoting + " since its restaurant field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAdminNew != null) {
                idAdminNew = em.getReference(idAdminNew.getClass(), idAdminNew.getIdUser());
                restaurant.setIdAdmin(idAdminNew);
            }
            if (townNew != null) {
                townNew = em.getReference(townNew.getClass(), townNew.getIdpoblacion());
                restaurant.setTown(townNew);
            }
            if (provinceNew != null) {
                provinceNew = em.getReference(provinceNew.getClass(), provinceNew.getIdprovincia());
                restaurant.setProvince(provinceNew);
            }
            List<Offers> attachedOffersListNew = new ArrayList<Offers>();
            for (Offers offersListNewOffersToAttach : offersListNew) {
                offersListNewOffersToAttach = em.getReference(offersListNewOffersToAttach.getClass(), offersListNewOffersToAttach.getIdOffer());
                attachedOffersListNew.add(offersListNewOffersToAttach);
            }
            offersListNew = attachedOffersListNew;
            restaurant.setOffersList(offersListNew);
            List<Comments> attachedCommentsListNew = new ArrayList<Comments>();
            for (Comments commentsListNewCommentsToAttach : commentsListNew) {
                commentsListNewCommentsToAttach = em.getReference(commentsListNewCommentsToAttach.getClass(), commentsListNewCommentsToAttach.getIdComment());
                attachedCommentsListNew.add(commentsListNewCommentsToAttach);
            }
            commentsListNew = attachedCommentsListNew;
            restaurant.setCommentsList(commentsListNew);
            List<Reservations> attachedReservationsListNew = new ArrayList<Reservations>();
            for (Reservations reservationsListNewReservationsToAttach : reservationsListNew) {
                reservationsListNewReservationsToAttach = em.getReference(reservationsListNewReservationsToAttach.getClass(), reservationsListNewReservationsToAttach.getIdReservation());
                attachedReservationsListNew.add(reservationsListNewReservationsToAttach);
            }
            reservationsListNew = attachedReservationsListNew;
            restaurant.setReservationsList(reservationsListNew);
            List<PhotoRestaurant> attachedPhotoRestaurantListNew = new ArrayList<PhotoRestaurant>();
            for (PhotoRestaurant photoRestaurantListNewPhotoRestaurantToAttach : photoRestaurantListNew) {
                photoRestaurantListNewPhotoRestaurantToAttach = em.getReference(photoRestaurantListNewPhotoRestaurantToAttach.getClass(), photoRestaurantListNewPhotoRestaurantToAttach.getPhotoRestaurantPK());
                attachedPhotoRestaurantListNew.add(photoRestaurantListNewPhotoRestaurantToAttach);
            }
            photoRestaurantListNew = attachedPhotoRestaurantListNew;
            restaurant.setPhotoRestaurantList(photoRestaurantListNew);
            List<Voting> attachedVotingListNew = new ArrayList<Voting>();
            for (Voting votingListNewVotingToAttach : votingListNew) {
                votingListNewVotingToAttach = em.getReference(votingListNewVotingToAttach.getClass(), votingListNewVotingToAttach.getVotingPK());
                attachedVotingListNew.add(votingListNewVotingToAttach);
            }
            votingListNew = attachedVotingListNew;
            restaurant.setVotingList(votingListNew);
            restaurant = em.merge(restaurant);
            if (idAdminOld != null && !idAdminOld.equals(idAdminNew)) {
                idAdminOld.getRestaurantList().remove(restaurant);
                idAdminOld = em.merge(idAdminOld);
            }
            if (idAdminNew != null && !idAdminNew.equals(idAdminOld)) {
                idAdminNew.getRestaurantList().add(restaurant);
                idAdminNew = em.merge(idAdminNew);
            }
            if (townOld != null && !townOld.equals(townNew)) {
                townOld.getRestaurantList().remove(restaurant);
                townOld = em.merge(townOld);
            }
            if (townNew != null && !townNew.equals(townOld)) {
                townNew.getRestaurantList().add(restaurant);
                townNew = em.merge(townNew);
            }
            if (provinceOld != null && !provinceOld.equals(provinceNew)) {
                provinceOld.getRestaurantList().remove(restaurant);
                provinceOld = em.merge(provinceOld);
            }
            if (provinceNew != null && !provinceNew.equals(provinceOld)) {
                provinceNew.getRestaurantList().add(restaurant);
                provinceNew = em.merge(provinceNew);
            }
            for (Offers offersListNewOffers : offersListNew) {
                if (!offersListOld.contains(offersListNewOffers)) {
                    Restaurant oldIdRestaurantOfOffersListNewOffers = offersListNewOffers.getIdRestaurant();
                    offersListNewOffers.setIdRestaurant(restaurant);
                    offersListNewOffers = em.merge(offersListNewOffers);
                    if (oldIdRestaurantOfOffersListNewOffers != null && !oldIdRestaurantOfOffersListNewOffers.equals(restaurant)) {
                        oldIdRestaurantOfOffersListNewOffers.getOffersList().remove(offersListNewOffers);
                        oldIdRestaurantOfOffersListNewOffers = em.merge(oldIdRestaurantOfOffersListNewOffers);
                    }
                }
            }
            for (Comments commentsListNewComments : commentsListNew) {
                if (!commentsListOld.contains(commentsListNewComments)) {
                    Restaurant oldIdRestaurantOfCommentsListNewComments = commentsListNewComments.getIdRestaurant();
                    commentsListNewComments.setIdRestaurant(restaurant);
                    commentsListNewComments = em.merge(commentsListNewComments);
                    if (oldIdRestaurantOfCommentsListNewComments != null && !oldIdRestaurantOfCommentsListNewComments.equals(restaurant)) {
                        oldIdRestaurantOfCommentsListNewComments.getCommentsList().remove(commentsListNewComments);
                        oldIdRestaurantOfCommentsListNewComments = em.merge(oldIdRestaurantOfCommentsListNewComments);
                    }
                }
            }
            for (Reservations reservationsListOldReservations : reservationsListOld) {
                if (!reservationsListNew.contains(reservationsListOldReservations)) {
                    reservationsListOldReservations.setIdRestaurant(null);
                    reservationsListOldReservations = em.merge(reservationsListOldReservations);
                }
            }
            for (Reservations reservationsListNewReservations : reservationsListNew) {
                if (!reservationsListOld.contains(reservationsListNewReservations)) {
                    Restaurant oldIdRestaurantOfReservationsListNewReservations = reservationsListNewReservations.getIdRestaurant();
                    reservationsListNewReservations.setIdRestaurant(restaurant);
                    reservationsListNewReservations = em.merge(reservationsListNewReservations);
                    if (oldIdRestaurantOfReservationsListNewReservations != null && !oldIdRestaurantOfReservationsListNewReservations.equals(restaurant)) {
                        oldIdRestaurantOfReservationsListNewReservations.getReservationsList().remove(reservationsListNewReservations);
                        oldIdRestaurantOfReservationsListNewReservations = em.merge(oldIdRestaurantOfReservationsListNewReservations);
                    }
                }
            }
            for (PhotoRestaurant photoRestaurantListNewPhotoRestaurant : photoRestaurantListNew) {
                if (!photoRestaurantListOld.contains(photoRestaurantListNewPhotoRestaurant)) {
                    Restaurant oldRestaurantOfPhotoRestaurantListNewPhotoRestaurant = photoRestaurantListNewPhotoRestaurant.getRestaurant();
                    photoRestaurantListNewPhotoRestaurant.setRestaurant(restaurant);
                    photoRestaurantListNewPhotoRestaurant = em.merge(photoRestaurantListNewPhotoRestaurant);
                    if (oldRestaurantOfPhotoRestaurantListNewPhotoRestaurant != null && !oldRestaurantOfPhotoRestaurantListNewPhotoRestaurant.equals(restaurant)) {
                        oldRestaurantOfPhotoRestaurantListNewPhotoRestaurant.getPhotoRestaurantList().remove(photoRestaurantListNewPhotoRestaurant);
                        oldRestaurantOfPhotoRestaurantListNewPhotoRestaurant = em.merge(oldRestaurantOfPhotoRestaurantListNewPhotoRestaurant);
                    }
                }
            }
            for (Voting votingListNewVoting : votingListNew) {
                if (!votingListOld.contains(votingListNewVoting)) {
                    Restaurant oldRestaurantOfVotingListNewVoting = votingListNewVoting.getRestaurant();
                    votingListNewVoting.setRestaurant(restaurant);
                    votingListNewVoting = em.merge(votingListNewVoting);
                    if (oldRestaurantOfVotingListNewVoting != null && !oldRestaurantOfVotingListNewVoting.equals(restaurant)) {
                        oldRestaurantOfVotingListNewVoting.getVotingList().remove(votingListNewVoting);
                        oldRestaurantOfVotingListNewVoting = em.merge(oldRestaurantOfVotingListNewVoting);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = restaurant.getIdRestaurant();
                if (findRestaurant(id) == null) {
                    throw new NonexistentEntityException("The restaurant with id " + id + " no longer exists.");
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
            Restaurant restaurant;
            try {
                restaurant = em.getReference(Restaurant.class, id);
                restaurant.getIdRestaurant();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The restaurant with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Offers> offersListOrphanCheck = restaurant.getOffersList();
            for (Offers offersListOrphanCheckOffers : offersListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Restaurant (" + restaurant + ") cannot be destroyed since the Offers " + offersListOrphanCheckOffers + " in its offersList field has a non-nullable idRestaurant field.");
            }
            List<Comments> commentsListOrphanCheck = restaurant.getCommentsList();
            for (Comments commentsListOrphanCheckComments : commentsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Restaurant (" + restaurant + ") cannot be destroyed since the Comments " + commentsListOrphanCheckComments + " in its commentsList field has a non-nullable idRestaurant field.");
            }
            List<PhotoRestaurant> photoRestaurantListOrphanCheck = restaurant.getPhotoRestaurantList();
            for (PhotoRestaurant photoRestaurantListOrphanCheckPhotoRestaurant : photoRestaurantListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Restaurant (" + restaurant + ") cannot be destroyed since the PhotoRestaurant " + photoRestaurantListOrphanCheckPhotoRestaurant + " in its photoRestaurantList field has a non-nullable restaurant field.");
            }
            List<Voting> votingListOrphanCheck = restaurant.getVotingList();
            for (Voting votingListOrphanCheckVoting : votingListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Restaurant (" + restaurant + ") cannot be destroyed since the Voting " + votingListOrphanCheckVoting + " in its votingList field has a non-nullable restaurant field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Users idAdmin = restaurant.getIdAdmin();
            if (idAdmin != null) {
                idAdmin.getRestaurantList().remove(restaurant);
                idAdmin = em.merge(idAdmin);
            }
            Poblacion town = restaurant.getTown();
            if (town != null) {
                town.getRestaurantList().remove(restaurant);
                town = em.merge(town);
            }
            Provincia province = restaurant.getProvince();
            if (province != null) {
                province.getRestaurantList().remove(restaurant);
                province = em.merge(province);
            }
            List<Reservations> reservationsList = restaurant.getReservationsList();
            for (Reservations reservationsListReservations : reservationsList) {
                reservationsListReservations.setIdRestaurant(null);
                reservationsListReservations = em.merge(reservationsListReservations);
            }
            em.remove(restaurant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Restaurant> findRestaurantEntities() {
        return findRestaurantEntities(true, -1, -1);
    }

    public List<Restaurant> findRestaurantEntities(int maxResults, int firstResult) {
        return findRestaurantEntities(false, maxResults, firstResult);
    }

    private List<Restaurant> findRestaurantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Restaurant.class));
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

    public Restaurant findRestaurant(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Restaurant.class, id);
        } finally {
            em.close();
        }
    }

    public int getRestaurantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Restaurant> rt = cq.from(Restaurant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Restaurant getRestaurant(Users user) {

        EntityManager em = getEntityManager();

        Restaurant restaurante = null;

        Query query = em.createNamedQuery("Restaurant.findByIdAdmin");
        query.setParameter("idAdmin", user);

        try {
            List lista = query.getResultList();
            if (lista.size() > 0) {
                restaurante = (Restaurant) lista.get(0);
            }
        } finally {
            em.close();
        }
        return restaurante;
    }

}
