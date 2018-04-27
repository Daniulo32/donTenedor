/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "photo_restaurant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhotoRestaurant.findAll", query = "SELECT p FROM PhotoRestaurant p")
    , @NamedQuery(name = "PhotoRestaurant.findByIdRestaurant", query = "SELECT p FROM PhotoRestaurant p WHERE p.photoRestaurantPK.idRestaurant = :idRestaurant")
    , @NamedQuery(name = "PhotoRestaurant.findByNamePhoto", query = "SELECT p FROM PhotoRestaurant p WHERE p.photoRestaurantPK.namePhoto = :namePhoto")
    , @NamedQuery(name = "PhotoRestaurant.findByPrincipalPhoto", query = "SELECT p FROM PhotoRestaurant p WHERE p.principalPhoto = :principalPhoto")})
public class PhotoRestaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PhotoRestaurantPK photoRestaurantPK;
    @Basic(optional = false)
    @Column(name = "principal_photo")
    private int principalPhoto;
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Restaurant restaurant;

    public PhotoRestaurant() {
    }

    public PhotoRestaurant(PhotoRestaurantPK photoRestaurantPK) {
        this.photoRestaurantPK = photoRestaurantPK;
    }

    public PhotoRestaurant(PhotoRestaurantPK photoRestaurantPK, int principalPhoto) {
        this.photoRestaurantPK = photoRestaurantPK;
        this.principalPhoto = principalPhoto;
    }

    public PhotoRestaurant(int idRestaurant, String namePhoto) {
        this.photoRestaurantPK = new PhotoRestaurantPK(idRestaurant, namePhoto);
    }

    public PhotoRestaurantPK getPhotoRestaurantPK() {
        return photoRestaurantPK;
    }

    public void setPhotoRestaurantPK(PhotoRestaurantPK photoRestaurantPK) {
        this.photoRestaurantPK = photoRestaurantPK;
    }

    public int getPrincipalPhoto() {
        return principalPhoto;
    }

    public void setPrincipalPhoto(int principalPhoto) {
        this.principalPhoto = principalPhoto;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (photoRestaurantPK != null ? photoRestaurantPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhotoRestaurant)) {
            return false;
        }
        PhotoRestaurant other = (PhotoRestaurant) object;
        if ((this.photoRestaurantPK == null && other.photoRestaurantPK != null) || (this.photoRestaurantPK != null && !this.photoRestaurantPK.equals(other.photoRestaurantPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.PhotoRestaurant[ photoRestaurantPK=" + photoRestaurantPK + " ]";
    }
    
}
