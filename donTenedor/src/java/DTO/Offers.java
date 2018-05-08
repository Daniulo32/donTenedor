/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
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
    , @NamedQuery(name = "Offers.findByPercentage", query = "SELECT o FROM Offers o WHERE o.percentage = :percentage")
    , @NamedQuery(name = "Offers.findByPrice", query = "SELECT o FROM Offers o WHERE o.price = :price")
    , @NamedQuery(name = "Offers.findByPeople", query = "SELECT o FROM Offers o WHERE o.people = :people")
    , @NamedQuery(name = "Offers.findByDescription", query = "SELECT o FROM Offers o WHERE o.description = :description")})
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
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @Column(name = "people")
    private int people;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant")
    @ManyToOne(optional = false)
    private Restaurant idRestaurant;

    public Offers() {
    }

    public Offers(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public Offers(Integer idOffer, int percentage, int price, int people, String description) {
        this.idOffer = idOffer;
        this.percentage = percentage;
        this.price = price;
        this.people = people;
        this.description = description;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
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
