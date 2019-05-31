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
@Table(name = "hef_perfil_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefPerfilEmpresa.findAll", query = "SELECT h FROM HefPerfilEmpresa h"),
    @NamedQuery(name = "HefPerfilEmpresa.findByIdperfilEmpresa", query = "SELECT h FROM HefPerfilEmpresa h WHERE h.idperfilEmpresa = :idperfilEmpresa"),
    @NamedQuery(name = "HefPerfilEmpresa.findByIdativo", query = "SELECT h FROM HefPerfilEmpresa h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefPerfilEmpresa.findByDthcadastro", query = "SELECT h FROM HefPerfilEmpresa h WHERE h.dthcadastro = :dthcadastro")})
public class HefPerfilEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperfil_empresa")
    private Long idperfilEmpresa;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private HefEmpresa idempresa;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne
    private HefPerfil idperfil;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefPerfilEmpresa() {
    }

    public HefPerfilEmpresa(Long idperfilEmpresa) {
        this.idperfilEmpresa = idperfilEmpresa;
    }

    public Long getIdperfilEmpresa() {
        return idperfilEmpresa;
    }

    public void setIdperfilEmpresa(Long idperfilEmpresa) {
        this.idperfilEmpresa = idperfilEmpresa;
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

    public HefEmpresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(HefEmpresa idempresa) {
        this.idempresa = idempresa;
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
        hash += (idperfilEmpresa != null ? idperfilEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefPerfilEmpresa)) {
            return false;
        }
        HefPerfilEmpresa other = (HefPerfilEmpresa) object;
        if ((this.idperfilEmpresa == null && other.idperfilEmpresa != null) || (this.idperfilEmpresa != null && !this.idperfilEmpresa.equals(other.idperfilEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefPerfilEmpresa[ idperfilEmpresa=" + idperfilEmpresa + " ]";
    }
    
}
