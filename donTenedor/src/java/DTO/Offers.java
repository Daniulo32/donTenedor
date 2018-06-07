/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

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
@Table(name = "offers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offers.findAll", query = "SELECT o FROM Offers o")
    , @NamedQuery(name = "Offers.findByIdOffer", query = "SELECT o FROM Offers o WHERE o.idOffer = :idOffer")
    , @NamedQuery(name = "Offers.findByIdRestaurant", query = "SELECT o FROM Offers o WHERE o.idRestaurant = :idRestaurant")
    , @NamedQuery(name = "Offers.findByPercentage", query = "SELECT o FROM Offers o WHERE o.percentage = :percentage")})
public class Offers implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_offer")
    private Integer idOffer;
    @Basic(optional = false)
    @Column(name = "percentage")
    private int percentage;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant")
    @ManyToOne(optional = false)
    private Restaurant idRestaurant;

    public Offers() {
    }

    public Offers(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public Offers(Integer idOffer, int percentage, Date startDate, Date endDate) {
        this.idOffer = idOffer;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOffer != null ? idOffer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offers)) {
            return false;
        }
        Offers other = (Offers) object;
        if ((this.idOffer == null && other.idOffer != null) || (this.idOffer != null && !this.idOffer.equals(other.idOffer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Offers[ idOffer=" + idOffer + " ]";
    }

}
