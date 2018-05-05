/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author danieljimenez
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByIdUser", query = "SELECT u FROM Users u WHERE u.idUser = :idUser")
    , @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name")
    , @NamedQuery(name = "Users.findBySubname", query = "SELECT u FROM Users u WHERE u.subname = :subname")
    , @NamedQuery(name = "Users.findByDni", query = "SELECT u FROM Users u WHERE u.dni = :dni")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByBirthday", query = "SELECT u FROM Users u WHERE u.birthday = :birthday")
    , @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role")
    , @NamedQuery(name = "Users.findByActive", query = "SELECT u FROM Users u WHERE u.active = :active")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "subname")
    private String subname;
    @Column(name = "dni")
    private String dni;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @Column(name = "role")
    private int role;
    @Basic(optional = false)
    @Column(name = "active")
    private int active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Comments> commentsList;
    @OneToMany(mappedBy = "idUser")
    private List<Reservations> reservationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdmin")
    private List<Restaurant> restaurantList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Voting> votingList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private BannedUsers bannedUsers;

    public Users() {
    }

    public Users(Integer idUser) {
        this.idUser = idUser;
    }

    public Users(Integer idUser, int role, int active) {
        this.idUser = idUser;
        this.role = role;
        this.active = active;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @XmlTransient
    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    @XmlTransient
    public List<Reservations> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    @XmlTransient
    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @XmlTransient
    public List<Voting> getVotingList() {
        return votingList;
    }

    public void setVotingList(List<Voting> votingList) {
        this.votingList = votingList;
    }

    public BannedUsers getBannedUsers() {
        return bannedUsers;
    }

    public void setBannedUsers(BannedUsers bannedUsers) {
        this.bannedUsers = bannedUsers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Users[ idUser=" + idUser + " ]";
    }
    
}
