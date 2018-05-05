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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "restaurant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r")
    , @NamedQuery(name = "Restaurant.findByIdRestaurant", query = "SELECT r FROM Restaurant r WHERE r.idRestaurant = :idRestaurant")
    , @NamedQuery(name = "Restaurant.findByType", query = "SELECT r FROM Restaurant r WHERE r.type = :type")
    , @NamedQuery(name = "Restaurant.findByAddress", query = "SELECT r FROM Restaurant r WHERE r.address = :address")
    , @NamedQuery(name = "Restaurant.findByLongitude", query = "SELECT r FROM Restaurant r WHERE r.longitude = :longitude")
    , @NamedQuery(name = "Restaurant.findByLatitude", query = "SELECT r FROM Restaurant r WHERE r.latitude = :latitude")
    , @NamedQuery(name = "Restaurant.findByScheduleOpen", query = "SELECT r FROM Restaurant r WHERE r.scheduleOpen = :scheduleOpen")
    , @NamedQuery(name = "Restaurant.findByScheduleClose", query = "SELECT r FROM Restaurant r WHERE r.scheduleClose = :scheduleClose")
    , @NamedQuery(name = "Restaurant.findByOpenDays", query = "SELECT r FROM Restaurant r WHERE r.openDays = :openDays")
    , @NamedQuery(name = "Restaurant.findByEmail", query = "SELECT r FROM Restaurant r WHERE r.email = :email")
    , @NamedQuery(name = "Restaurant.findByPhone", query = "SELECT r FROM Restaurant r WHERE r.phone = :phone")
    , @NamedQuery(name = "Restaurant.findByHalfPrice", query = "SELECT r FROM Restaurant r WHERE r.halfPrice = :halfPrice")
    , @NamedQuery(name = "Restaurant.findByHomeService", query = "SELECT r FROM Restaurant r WHERE r.homeService = :homeService")
    , @NamedQuery(name = "Restaurant.findByWifi", query = "SELECT r FROM Restaurant r WHERE r.wifi = :wifi")
    , @NamedQuery(name = "Restaurant.findByTerrace", query = "SELECT r FROM Restaurant r WHERE r.terrace = :terrace")
    , @NamedQuery(name = "Restaurant.findByHandicapped", query = "SELECT r FROM Restaurant r WHERE r.handicapped = :handicapped")
    , @NamedQuery(name = "Restaurant.findByCardPayment", query = "SELECT r FROM Restaurant r WHERE r.cardPayment = :cardPayment")
    , @NamedQuery(name = "Restaurant.findByObservations", query = "SELECT r FROM Restaurant r WHERE r.observations = :observations")})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_restaurant")
    private Integer idRestaurant;
    @Column(name = "type")
    private String type;
    @Column(name = "address")
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "schedule_open")
    @Temporal(TemporalType.TIME)
    private Date scheduleOpen;
    @Column(name = "schedule_close")
    @Temporal(TemporalType.TIME)
    private Date scheduleClose;
    @Column(name = "open_days")
    private String openDays;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "half_price")
    private Double halfPrice;
    @Basic(optional = false)
    @Column(name = "home_service")
    private int homeService;
    @Basic(optional = false)
    @Column(name = "wifi")
    private int wifi;
    @Basic(optional = false)
    @Column(name = "terrace")
    private int terrace;
    @Basic(optional = false)
    @Column(name = "handicapped")
    private int handicapped;
    @Basic(optional = false)
    @Column(name = "card_payment")
    private int cardPayment;
    @Column(name = "observations")
    private String observations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRestaurant")
    private List<Offers> offersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRestaurant")
    private List<Comments> commentsList;
    @OneToMany(mappedBy = "idRestaurant")
    private List<Reservations> reservationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<PhotoRestaurant> photoRestaurantList;
    @JoinColumn(name = "id_admin", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Users idAdmin;
    @JoinColumn(name = "province", referencedColumnName = "idprovincia")
    @ManyToOne
    private Provincia province;
    @JoinColumn(name = "town", referencedColumnName = "idpoblacion")
    @ManyToOne
    private Poblacion town;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Voting> votingList;

    public Restaurant() {
    }

    public Restaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Restaurant(Integer idRestaurant, int homeService, int wifi, int terrace, int handicapped, int cardPayment) {
        this.idRestaurant = idRestaurant;
        this.homeService = homeService;
        this.wifi = wifi;
        this.terrace = terrace;
        this.handicapped = handicapped;
        this.cardPayment = cardPayment;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getScheduleOpen() {
        return scheduleOpen;
    }

    public void setScheduleOpen(Date scheduleOpen) {
        this.scheduleOpen = scheduleOpen;
    }

    public Date getScheduleClose() {
        return scheduleClose;
    }

    public void setScheduleClose(Date scheduleClose) {
        this.scheduleClose = scheduleClose;
    }

    public String getOpenDays() {
        return openDays;
    }

    public void setOpenDays(String openDays) {
        this.openDays = openDays;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Double getHalfPrice() {
        return halfPrice;
    }

    public void setHalfPrice(Double halfPrice) {
        this.halfPrice = halfPrice;
    }

    public int getHomeService() {
        return homeService;
    }

    public void setHomeService(int homeService) {
        this.homeService = homeService;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getTerrace() {
        return terrace;
    }

    public void setTerrace(int terrace) {
        this.terrace = terrace;
    }

    public int getHandicapped() {
        return handicapped;
    }

    public void setHandicapped(int handicapped) {
        this.handicapped = handicapped;
    }

    public int getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(int cardPayment) {
        this.cardPayment = cardPayment;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @XmlTransient
    public List<Offers> getOffersList() {
        return offersList;
    }

    public void setOffersList(List<Offers> offersList) {
        this.offersList = offersList;
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
    public List<PhotoRestaurant> getPhotoRestaurantList() {
        return photoRestaurantList;
    }

    public void setPhotoRestaurantList(List<PhotoRestaurant> photoRestaurantList) {
        this.photoRestaurantList = photoRestaurantList;
    }

    public Users getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Users idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Provincia getProvince() {
        return province;
    }

    public void setProvince(Provincia province) {
        this.province = province;
    }

    public Poblacion getTown() {
        return town;
    }

    public void setTown(Poblacion town) {
        this.town = town;
    }

    @XmlTransient
    public List<Voting> getVotingList() {
        return votingList;
    }

    public void setVotingList(List<Voting> votingList) {
        this.votingList = votingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRestaurant != null ? idRestaurant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.idRestaurant == null && other.idRestaurant != null) || (this.idRestaurant != null && !this.idRestaurant.equals(other.idRestaurant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Restaurant[ idRestaurant=" + idRestaurant + " ]";
    }
    
}
