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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danieljimenez
 */
@Entity
@Table(name = "incidence_errors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncidenceErrors.findAll", query = "SELECT i FROM IncidenceErrors i")
    , @NamedQuery(name = "IncidenceErrors.findByIdIncidence", query = "SELECT i FROM IncidenceErrors i WHERE i.idIncidence = :idIncidence")
    , @NamedQuery(name = "IncidenceErrors.findByObservation", query = "SELECT i FROM IncidenceErrors i WHERE i.observation = :observation")
    , @NamedQuery(name = "IncidenceErrors.findByErrorPlace", query = "SELECT i FROM IncidenceErrors i WHERE i.errorPlace = :errorPlace")
    , @NamedQuery(name = "IncidenceErrors.findByStatus", query = "SELECT i FROM IncidenceErrors i WHERE i.status = :status")})
public class IncidenceErrors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_incidence")
    private Integer idIncidence;
    @Basic(optional = false)
    @Column(name = "observation")
    private String observation;
    @Basic(optional = false)
    @Column(name = "error_place")
    private String errorPlace;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;

    public IncidenceErrors() {
    }

    public IncidenceErrors(Integer idIncidence) {
        this.idIncidence = idIncidence;
    }

    public IncidenceErrors(Integer idIncidence, String observation, String errorPlace, int status) {
        this.idIncidence = idIncidence;
        this.observation = observation;
        this.errorPlace = errorPlace;
        this.status = status;
    }

    public Integer getIdIncidence() {
        return idIncidence;
    }

    public void setIdIncidence(Integer idIncidence) {
        this.idIncidence = idIncidence;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getErrorPlace() {
        return errorPlace;
    }

    public void setErrorPlace(String errorPlace) {
        this.errorPlace = errorPlace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncidence != null ? idIncidence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncidenceErrors)) {
            return false;
        }
        IncidenceErrors other = (IncidenceErrors) object;
        if ((this.idIncidence == null && other.idIncidence != null) || (this.idIncidence != null && !this.idIncidence.equals(other.idIncidence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.IncidenceErrors[ idIncidence=" + idIncidence + " ]";
    }
    
}
