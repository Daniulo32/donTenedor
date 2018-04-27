/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danieljimenez
 */
@Entity
@Table(name = "reservations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r")
    , @NamedQuery(name = "Reservations.findByIdReservation", query = "SELECT r FROM Reservations r WHERE r.idReservation = :idReservation")
    , @NamedQuery(name = "Reservations.findByReservationDate", query = "SELECT r FROM Reservations r WHERE r.reservationDate = :reservationDate")
    , @NamedQuery(name = "Reservations.findByHour", query = "SELECT r FROM Reservations r WHERE r.hour = :hour")
    , @NamedQuery(name = "Reservations.findByPeople", query = "SELECT r FROM Reservations r WHERE r.people = :people")
    , @NamedQuery(name = "Reservations.findByConfirmation", query = "SELECT r FROM Reservations r WHERE r.confirmation = :confirmation")
    , @NamedQuery(name = "Reservations.findByStatus", query = "SELECT r FROM Reservations r WHERE r.status = :status")})
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reservation")
    private Integer idReservation;
    @Column(name = "reservation_date")
    @Temporal(TemporalType.DATE)
    private Date reservationDate;
    @Column(name = "hour")
    @Temporal(TemporalType.TIME)
    private Date hour;
    @Column(name = "people")
    private Integer people;
    @Column(name = "confirmation")
    private Integer confirmation;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant")
    @ManyToOne
    private Restaurant idRestaurant;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private Users idUser;

    public Reservations() {
    }

    public Reservations(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Integer confirmation) {
        this.confirmation = confirmation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservation != null ? idReservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.idReservation == null && other.idReservation != null) || (this.idReservation != null && !this.idReservation.equals(other.idReservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Reservations[ idReservation=" + idReservation + " ]";
    }
    
}
