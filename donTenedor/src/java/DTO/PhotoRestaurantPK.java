/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author danieljimenez
 */
@Embeddable
public class PhotoRestaurantPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_restaurant")
    private int idRestaurant;
    @Basic(optional = false)
    @Column(name = "name_photo")
    private String namePhoto;

    public PhotoRestaurantPK() {
    }

    public PhotoRestaurantPK(int idRestaurant, String namePhoto) {
        this.idRestaurant = idRestaurant;
        this.namePhoto = namePhoto;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRestaurant;
        hash += (namePhoto != null ? namePhoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhotoRestaurantPK)) {
            return false;
        }
        PhotoRestaurantPK other = (PhotoRestaurantPK) object;
        if (this.idRestaurant != other.idRestaurant) {
            return false;
        }
        if ((this.namePhoto == null && other.namePhoto != null) || (this.namePhoto != null && !this.namePhoto.equals(other.namePhoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.PhotoRestaurantPK[ idRestaurant=" + idRestaurant + ", namePhoto=" + namePhoto + " ]";
    }
    
}
