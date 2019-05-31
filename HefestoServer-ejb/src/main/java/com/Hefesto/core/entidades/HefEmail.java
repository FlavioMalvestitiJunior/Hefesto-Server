/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEmail.findAll", query = "SELECT h FROM HefEmail h"),
    @NamedQuery(name = "HefEmail.findByIdemail", query = "SELECT h FROM HefEmail h WHERE h.idemail = :idemail"),
    @NamedQuery(name = "HefEmail.findByDthemail", query = "SELECT h FROM HefEmail h WHERE h.dthemail = :dthemail"),
    @NamedQuery(name = "HefEmail.findByDthenviado", query = "SELECT h FROM HefEmail h WHERE h.dthenviado = :dthenviado"),
    @NamedQuery(name = "HefEmail.findByAssunto", query = "SELECT h FROM HefEmail h WHERE h.assunto = :assunto"),
    @NamedQuery(name = "HefEmail.findByCorpoemail", query = "SELECT h FROM HefEmail h WHERE h.corpoemail = :corpoemail"),
    @NamedQuery(name = "HefEmail.findByIdenviado", query = "SELECT h FROM HefEmail h WHERE h.idenviado = :idenviado")})
public class HefEmail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemail")
    private Long idemail;
    @Column(name = "dthemail")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthemail;
    @Column(name = "dthenviado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthenviado;
    @Size(max = 2147483647)
    @Column(name = "assunto")
    private String assunto;
    @Size(max = 2147483647)
    @Column(name = "corpoemail")
    private String corpoemail;
    @Column(name = "idenviado")
    private Boolean idenviado;
    @OneToMany(mappedBy = "idemail")
    private Collection<HefEmailAnexo> hefEmailAnexoCollection;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;
    @OneToMany(mappedBy = "idemail")
    private Collection<HefEmailContato> hefEmailContatoCollection;

    public HefEmail() {
    }

    public HefEmail(Long idemail) {
        this.idemail = idemail;
    }

    public Long getIdemail() {
        return idemail;
    }

    public void setIdemail(Long idemail) {
        this.idemail = idemail;
    }

    public Date getDthemail() {
        return dthemail;
    }

    public void setDthemail(Date dthemail) {
        this.dthemail = dthemail;
    }

    public Date getDthenviado() {
        return dthenviado;
    }

    public void setDthenviado(Date dthenviado) {
        this.dthenviado = dthenviado;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpoemail() {
        return corpoemail;
    }

    public void setCorpoemail(String corpoemail) {
        this.corpoemail = corpoemail;
    }

    public Boolean getIdenviado() {
        return idenviado;
    }

    public void setIdenviado(Boolean idenviado) {
        this.idenviado = idenviado;
    }

    @XmlTransient
    public Collection<HefEmailAnexo> getHefEmailAnexoCollection() {
        return hefEmailAnexoCollection;
    }

    public void setHefEmailAnexoCollection(Collection<HefEmailAnexo> hefEmailAnexoCollection) {
        this.hefEmailAnexoCollection = hefEmailAnexoCollection;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<HefEmailContato> getHefEmailContatoCollection() {
        return hefEmailContatoCollection;
    }

    public void setHefEmailContatoCollection(Collection<HefEmailContato> hefEmailContatoCollection) {
        this.hefEmailContatoCollection = hefEmailContatoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemail != null ? idemail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEmail)) {
            return false;
        }
        HefEmail other = (HefEmail) object;
        if ((this.idemail == null && other.idemail != null) || (this.idemail != null && !this.idemail.equals(other.idemail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEmail[ idemail=" + idemail + " ]";
    }
    
}
