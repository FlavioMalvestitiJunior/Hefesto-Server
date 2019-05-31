/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_perfil_filial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefPerfilFilial.findAll", query = "SELECT h FROM HefPerfilFilial h"),
    @NamedQuery(name = "HefPerfilFilial.findByIdperfilFilial", query = "SELECT h FROM HefPerfilFilial h WHERE h.idperfilFilial = :idperfilFilial"),
    @NamedQuery(name = "HefPerfilFilial.findByIdativo", query = "SELECT h FROM HefPerfilFilial h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefPerfilFilial.findByDthcadastro", query = "SELECT h FROM HefPerfilFilial h WHERE h.dthcadastro = :dthcadastro")})
public class HefPerfilFilial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperfil_filial")
    private Long idperfilFilial;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idfilial", referencedColumnName = "idfilial")
    @ManyToOne
    private HefFilial idfilial;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne
    private HefPerfil idperfil;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefPerfilFilial() {
    }

    public HefPerfilFilial(Long idperfilFilial) {
        this.idperfilFilial = idperfilFilial;
    }

    public Long getIdperfilFilial() {
        return idperfilFilial;
    }

    public void setIdperfilFilial(Long idperfilFilial) {
        this.idperfilFilial = idperfilFilial;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefFilial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(HefFilial idfilial) {
        this.idfilial = idfilial;
    }

    public HefPerfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(HefPerfil idperfil) {
        this.idperfil = idperfil;
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
        hash += (idperfilFilial != null ? idperfilFilial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefPerfilFilial)) {
            return false;
        }
        HefPerfilFilial other = (HefPerfilFilial) object;
        if ((this.idperfilFilial == null && other.idperfilFilial != null) || (this.idperfilFilial != null && !this.idperfilFilial.equals(other.idperfilFilial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefPerfilFilial[ idperfilFilial=" + idperfilFilial + " ]";
    }
    
}
