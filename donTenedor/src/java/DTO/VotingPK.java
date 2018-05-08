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
public class VotingPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_restaurant")
    private int idRestaurant;
    @Basic(optional = false)
    @Column(name = "id_user")
    private int idUser;

    public VotingPK() {
    }

    public VotingPK(int idRestaurant, int idUser) {
        this.idRestaurant = idRestaurant;
        this.idUser = idUser;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRestaurant;
        hash += (int) idUser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotingPK)) {
            return false;
        }
        VotingPK other = (VotingPK) object;
        if (this.idRestaurant != other.idRestaurant) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.VotingPK[ idRestaurant=" + idRestaurant + ", idUser=" + idUser + " ]";
    }
    
}
