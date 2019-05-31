/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_email_contato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEmailContato.findAll", query = "SELECT h FROM HefEmailContato h"),
    @NamedQuery(name = "HefEmailContato.findByIdemailContato", query = "SELECT h FROM HefEmailContato h WHERE h.idemailContato = :idemailContato")})
public class HefEmailContato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemail_contato")
    private Long idemailContato;
    @JoinColumn(name = "idcontato_email", referencedColumnName = "idcontato")
    @ManyToOne
    private HefContatoEmail idcontatoEmail;
    @JoinColumn(name = "idemail", referencedColumnName = "idemail")
    @ManyToOne
    private HefEmail idemail;

    public HefEmailContato() {
    }

    public HefEmailContato(Long idemailContato) {
        this.idemailContato = idemailContato;
    }

    public Long getIdemailContato() {
        return idemailContato;
    }

    public void setIdemailContato(Long idemailContato) {
        this.idemailContato = idemailContato;
    }

    public HefContatoEmail getIdcontatoEmail() {
        return idcontatoEmail;
    }

    public void setIdcontatoEmail(HefContatoEmail idcontatoEmail) {
        this.idcontatoEmail = idcontatoEmail;
    }

    public HefEmail getIdemail() {
        return idemail;
    }

    public void setIdemail(HefEmail idemail) {
        this.idemail = idemail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemailContato != null ? idemailContato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEmailContato)) {
            return false;
        }
        HefEmailContato other = (HefEmailContato) object;
        if ((this.idemailContato == null && other.idemailContato != null) || (this.idemailContato != null && !this.idemailContato.equals(other.idemailContato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEmailContato[ idemailContato=" + idemailContato + " ]";
    }
    
}
