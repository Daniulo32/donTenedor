/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

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
@Table(name = "voting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voting.findAll", query = "SELECT v FROM Voting v")
    , @NamedQuery(name = "Voting.findByIdRestaurant", query = "SELECT v FROM Voting v WHERE v.votingPK.idRestaurant = :idRestaurant")
    , @NamedQuery(name = "Voting.findByIdUser", query = "SELECT v FROM Voting v WHERE v.votingPK.idUser = :idUser")
    , @NamedQuery(name = "Voting.findByVote", query = "SELECT v FROM Voting v WHERE v.vote = :vote")})
public class Voting implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VotingPK votingPK;
    @Basic(optional = false)
    @Column(name = "vote")
    private int vote;
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Restaurant restaurant;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Voting() {
    }

    public Voting(VotingPK votingPK) {
        this.votingPK = votingPK;
    }

    public Voting(VotingPK votingPK, int vote) {
        this.votingPK = votingPK;
        this.vote = vote;
    }

    public Voting(int idRestaurant, int idUser) {
        this.votingPK = new VotingPK(idRestaurant, idUser);
    }

    public VotingPK getVotingPK() {
        return votingPK;
    }

    public void setVotingPK(VotingPK votingPK) {
        this.votingPK = votingPK;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (votingPK != null ? votingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voting)) {
            return false;
        }
        Voting other = (Voting) object;
        if ((this.votingPK == null && other.votingPK != null) || (this.votingPK != null && !this.votingPK.equals(other.votingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Voting[ votingPK=" + votingPK + " ]";
    }
    
}
