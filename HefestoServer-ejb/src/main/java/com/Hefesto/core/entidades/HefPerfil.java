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
@Table(name = "hef_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefPerfil.findAll", query = "SELECT h FROM HefPerfil h"),
    @NamedQuery(name = "HefPerfil.findByIdperfil", query = "SELECT h FROM HefPerfil h WHERE h.idperfil = :idperfil"),
    @NamedQuery(name = "HefPerfil.findByDescricao", query = "SELECT h FROM HefPerfil h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefPerfil.findByIdadministrador", query = "SELECT h FROM HefPerfil h WHERE h.idadministrador = :idadministrador"),
    @NamedQuery(name = "HefPerfil.findByIdgerente", query = "SELECT h FROM HefPerfil h WHERE h.idgerente = :idgerente"),
    @NamedQuery(name = "HefPerfil.findByDthcadastro", query = "SELECT h FROM HefPerfil h WHERE h.dthcadastro = :dthcadastro"),
    @NamedQuery(name = "HefPerfil.findByIdativo", query = "SELECT h FROM HefPerfil h WHERE h.idativo = :idativo")})
public class HefPerfil implements Serializable {
    @Column(name = "idadministrador_empresa")
    private Boolean idadministradorEmpresa;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperfil")
    private Long idperfil;
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "idadministrador")
    private Boolean idadministrador;
    @Column(name = "idgerente")
    private Boolean idgerente;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @Column(name = "idativo")
    private Boolean idativo;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;
    @OneToMany(mappedBy = "idperfil")
    private Collection<HefPerfilEmpresa> hefPerfilEmpresaCollection;
    @OneToMany(mappedBy = "idperfil")
    private Collection<HefLiberacaoTela> hefLiberacaoTelaCollection;
    @OneToMany(mappedBy = "idperfil")
    private Collection<HefUsuarioInfo> hefUsuarioInfoCollection;

    public HefPerfil() {
    }

    public HefPerfil(Long idperfil) {
        this.idperfil = idperfil;
    }

    public Long getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Long idperfil) {
        this.idperfil = idperfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Boolean idadministrador) {
        this.idadministrador = idadministrador;
    }

    public Boolean getIdgerente() {
        return idgerente;
    }

    public void setIdgerente(Boolean idgerente) {
        this.idgerente = idgerente;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<HefPerfilEmpresa> getHefPerfilEmpresaCollection() {
        return hefPerfilEmpresaCollection;
    }

    public void setHefPerfilEmpresaCollection(Collection<HefPerfilEmpresa> hefPerfilEmpresaCollection) {
        this.hefPerfilEmpresaCollection = hefPerfilEmpresaCollection;
    }

    @XmlTransient
    public Collection<HefLiberacaoTela> getHefLiberacaoTelaCollection() {
        return hefLiberacaoTelaCollection;
    }

    public void setHefLiberacaoTelaCollection(Collection<HefLiberacaoTela> hefLiberacaoTelaCollection) {
        this.hefLiberacaoTelaCollection = hefLiberacaoTelaCollection;
    }

    @XmlTransient
    public Collection<HefUsuarioInfo> getHefUsuarioInfoCollection() {
        return hefUsuarioInfoCollection;
    }

    public void setHefUsuarioInfoCollection(Collection<HefUsuarioInfo> hefUsuarioInfoCollection) {
        this.hefUsuarioInfoCollection = hefUsuarioInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperfil != null ? idperfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefPerfil)) {
            return false;
        }
        HefPerfil other = (HefPerfil) object;
        if ((this.idperfil == null && other.idperfil != null) || (this.idperfil != null && !this.idperfil.equals(other.idperfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefPerfil[ idperfil=" + idperfil + " ]";
    }

    public Boolean getIdadministradorEmpresa() {
        return idadministradorEmpresa;
    }

    public void setIdadministradorEmpresa(Boolean idadministradorEmpresa) {
        this.idadministradorEmpresa = idadministradorEmpresa;
    }
    
}
