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
@Table(name = "hef_contato_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefContatoEmail.findAll", query = "SELECT h FROM HefContatoEmail h"),
    @NamedQuery(name = "HefContatoEmail.findByIdcontato", query = "SELECT h FROM HefContatoEmail h WHERE h.idcontato = :idcontato"),
    @NamedQuery(name = "HefContatoEmail.findByNome", query = "SELECT h FROM HefContatoEmail h WHERE h.nome = :nome"),
    @NamedQuery(name = "HefContatoEmail.findByEmail", query = "SELECT h FROM HefContatoEmail h WHERE h.email = :email"),
    @NamedQuery(name = "HefContatoEmail.findByDthcadasdtro", query = "SELECT h FROM HefContatoEmail h WHERE h.dthcadasdtro = :dthcadasdtro"),
    @NamedQuery(name = "HefContatoEmail.findByIdativo", query = "SELECT h FROM HefContatoEmail h WHERE h.idativo = :idativo")})
public class HefContatoEmail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontato")
    private Long idcontato;
    @Size(max = 700)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 700)
    @Column(name = "email")
    private String email;
    @Column(name = "dthcadasdtro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadasdtro;
    @Column(name = "idativo")
    private Boolean idativo;
    @OneToMany(mappedBy = "idcontatoEmail")
    private Collection<HefEmailContato> hefEmailContatoCollection;
    @JoinColumn(name = "idusuario_contato", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuarioContato;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefContatoEmail() {
    }

    public HefContatoEmail(Long idcontato) {
        this.idcontato = idcontato;
    }

    public Long getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(Long idcontato) {
        this.idcontato = idcontato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDthcadasdtro() {
        return dthcadasdtro;
    }

    public void setDthcadasdtro(Date dthcadasdtro) {
        this.dthcadasdtro = dthcadasdtro;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    @XmlTransient
    public Collection<HefEmailContato> getHefEmailContatoCollection() {
        return hefEmailContatoCollection;
    }

    public void setHefEmailContatoCollection(Collection<HefEmailContato> hefEmailContatoCollection) {
        this.hefEmailContatoCollection = hefEmailContatoCollection;
    }

    public HefUsuario getIdusuarioContato() {
        return idusuarioContato;
    }

    public void setIdusuarioContato(HefUsuario idusuarioContato) {
        this.idusuarioContato = idusuarioContato;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontato != null ? idcontato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefContatoEmail)) {
            return false;
        }
        HefContatoEmail other = (HefContatoEmail) object;
        if ((this.idcontato == null && other.idcontato != null) || (this.idcontato != null && !this.idcontato.equals(other.idcontato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefContatoEmail[ idcontato=" + idcontato + " ]";
    }
    
}
