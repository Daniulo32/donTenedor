/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "incidence_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncidenceComment.findAll", query = "SELECT i FROM IncidenceComment i")
    , @NamedQuery(name = "IncidenceComment.findByIdIncidence", query = "SELECT i FROM IncidenceComment i WHERE i.idIncidence = :idIncidence")
    , @NamedQuery(name = "IncidenceComment.findByObservation", query = "SELECT i FROM IncidenceComment i WHERE i.observation = :observation")})
public class IncidenceComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_incidence")
    private Integer idIncidence;
    @Basic(optional = false)
    @Column(name = "observation")
    private String observation;
    @JoinColumn(name = "id_comment", referencedColumnName = "id_comment")
    @ManyToOne(optional = false)
    private Comments idComment;

    public IncidenceComment() {
    }

    public IncidenceComment(Integer idIncidence) {
        this.idIncidence = idIncidence;
    }

    public IncidenceComment(Integer idIncidence, String observation) {
        this.idIncidence = idIncidence;
        this.observation = observation;
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

    public Comments getIdComment() {
        return idComment;
    }

    public void setIdComment(Comments idComment) {
        this.idComment = idComment;
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
        if (!(object instanceof IncidenceComment)) {
            return false;
        }
        IncidenceComment other = (IncidenceComment) object;
        if ((this.idIncidence == null && other.idIncidence != null) || (this.idIncidence != null && !this.idIncidence.equals(other.idIncidence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.IncidenceComment[ idIncidence=" + idIncidence + " ]";
    }
    
}
