/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
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
@Table(name = "provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p order by p.provincia")
    , @NamedQuery(name = "Provincia.findByIdprovincia", query = "SELECT p FROM Provincia p WHERE p.idprovincia = :idprovincia")
    , @NamedQuery(name = "Provincia.findByProvincia", query = "SELECT p FROM Provincia p WHERE p.provincia = :provincia")
    , @NamedQuery(name = "Provincia.findByProvinciaseo", query = "SELECT p FROM Provincia p WHERE p.provinciaseo = :provinciaseo")
    , @NamedQuery(name = "Provincia.findByProvincia3", query = "SELECT p FROM Provincia p WHERE p.provincia3 = :provincia3")
    , @NamedQuery(name = "Provincia.findByComunidad", query = "SELECT p FROM Provincia p WHERE p.comunidad = :comunidad")})
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprovincia")
    private Integer idprovincia;
    @Basic(optional = false)
    @Column(name = "provincia")
    private String provincia;
    @Basic(optional = false)
    @Column(name = "provinciaseo")
    private String provinciaseo;
    @Column(name = "provincia3")
    private String provincia3;
    @Basic(optional = false)
    @Column(name = "comunidad")
    private String comunidad;
    @OneToMany(mappedBy = "province")
    private List<Restaurant> restaurantList;

    public Provincia() {
    }

    public Provincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public Provincia(Integer idprovincia, String provincia, String provinciaseo, String comunidad) {
        this.idprovincia = idprovincia;
        this.provincia = provincia;
        this.provinciaseo = provinciaseo;
        this.comunidad = comunidad;
    }

    public Integer getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getProvinciaseo() {
        return provinciaseo;
    }

    public void setProvinciaseo(String provinciaseo) {
        this.provinciaseo = provinciaseo;
    }

    public String getProvincia3() {
        return provincia3;
    }

    public void setProvincia3(String provincia3) {
        this.provincia3 = provincia3;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
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
        hash += (idprovincia != null ? idprovincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.idprovincia == null && other.idprovincia != null) || (this.idprovincia != null && !this.idprovincia.equals(other.idprovincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Provincia[ idprovincia=" + idprovincia + " ]";
    }
    
}
