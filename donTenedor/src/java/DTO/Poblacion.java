/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author danieljimenez
 */
@Entity
@Table(name = "poblacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poblacion.findAll", query = "SELECT p FROM Poblacion p")
    , @NamedQuery(name = "Poblacion.findByIdpoblacion", query = "SELECT p FROM Poblacion p WHERE p.idpoblacion = :idpoblacion")
    , @NamedQuery(name = "Poblacion.findByIdprovincia", query = "SELECT p FROM Poblacion p WHERE p.idprovincia = :idprovincia")
    , @NamedQuery(name = "Poblacion.findByPoblacion", query = "SELECT p FROM Poblacion p WHERE p.poblacion = :poblacion")
    , @NamedQuery(name = "Poblacion.findByPoblacionseo", query = "SELECT p FROM Poblacion p WHERE p.poblacionseo = :poblacionseo")
    , @NamedQuery(name = "Poblacion.findByPostal", query = "SELECT p FROM Poblacion p WHERE p.postal = :postal")
    , @NamedQuery(name = "Poblacion.findByLatitud", query = "SELECT p FROM Poblacion p WHERE p.latitud = :latitud")
    , @NamedQuery(name = "Poblacion.findByLongitud", query = "SELECT p FROM Poblacion p WHERE p.longitud = :longitud")})
public class Poblacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpoblacion")
    private Integer idpoblacion;
    @Basic(optional = false)
    @Column(name = "idprovincia")
    private int idprovincia;
    @Basic(optional = false)
    @Column(name = "poblacion")
    private String poblacion;
    @Column(name = "poblacionseo")
    private String poblacionseo;
    @Column(name = "postal")
    private Integer postal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Column(name = "longitud")
    private BigDecimal longitud;
    @OneToMany(mappedBy = "town")
    private List<Restaurant> restaurantList;

    public Poblacion() {
    }

    public Poblacion(Integer idpoblacion) {
        this.idpoblacion = idpoblacion;
    }

    public Poblacion(Integer idpoblacion, int idprovincia, String poblacion) {
        this.idpoblacion = idpoblacion;
        this.idprovincia = idprovincia;
        this.poblacion = poblacion;
    }

    public Integer getIdpoblacion() {
        return idpoblacion;
    }

    public void setIdpoblacion(Integer idpoblacion) {
        this.idpoblacion = idpoblacion;
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getPoblacionseo() {
        return poblacionseo;
    }

    public void setPoblacionseo(String poblacionseo) {
        this.poblacionseo = poblacionseo;
    }

    public Integer getPostal() {
        return postal;
    }

    public void setPostal(Integer postal) {
        this.postal = postal;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    @XmlTransient
    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpoblacion != null ? idpoblacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poblacion)) {
            return false;
        }
        Poblacion other = (Poblacion) object;
        if ((this.idpoblacion == null && other.idpoblacion != null) || (this.idpoblacion != null && !this.idpoblacion.equals(other.idpoblacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Poblacion[ idpoblacion=" + idpoblacion + " ]";
    }
    
}
