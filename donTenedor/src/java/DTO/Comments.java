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
@Table(name = "comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c")
    , @NamedQuery(name = "Comments.findByIdComment", query = "SELECT c FROM Comments c WHERE c.idComment = :idComment")
    , @NamedQuery(name = "Comments.findByComment", query = "SELECT c FROM Comments c WHERE c.comment = :comment")
    , @NamedQuery(name = "Comments.findByCommentDate", query = "SELECT c FROM Comments c WHERE c.commentDate = :commentDate")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comment")
    private Integer idComment;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @Column(name = "comment_date")
    @Temporal(TemporalType.DATE)
    private Date commentDate;
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant")
    @ManyToOne(optional = false)
    private Restaurant idRestaurant;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Users idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComment")
    private List<IncidenceComment> incidenceCommentList;

    public Comments() {
    }

    public Comments(Integer idComment) {
        this.idComment = idComment;
    }

    public Comments(Integer idComment, String comment) {
        this.idComment = idComment;
        this.comment = comment;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<IncidenceComment> getIncidenceCommentList() {
        return incidenceCommentList;
    }

    public void setIncidenceCommentList(List<IncidenceComment> incidenceCommentList) {
        this.incidenceCommentList = incidenceCommentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Comments[ idComment=" + idComment + " ]";
    }
    
}
