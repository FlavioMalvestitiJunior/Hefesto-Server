/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEmpresa.findAll", query = "SELECT h FROM HefEmpresa h"),
    @NamedQuery(name = "HefEmpresa.findByIdempresa", query = "SELECT h FROM HefEmpresa h WHERE h.idempresa = :idempresa"),
    @NamedQuery(name = "HefEmpresa.findByDescricao", query = "SELECT h FROM HefEmpresa h WHERE h.descricao = :descricao")})
public class HefEmpresa implements Serializable {
    @OneToMany(mappedBy = "idempresa")
    private Collection<HefFornecedorInfo> hefFornecedorInfoCollection;
    @OneToMany(mappedBy = "idempresa")
    private Collection<HefClienteInfo> hefClienteInfoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresa")
    private Long idempresa;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idempresa")
    private Collection<HefPerfilEmpresa> hefPerfilEmpresaCollection;
    @OneToMany(mappedBy = "idempresa")
    private Collection<HefEmpresaInfo> hefEmpresaInfoCollection;
    @OneToMany(mappedBy = "idempresa")
    private Collection<HefFilialInfo> hefFilialInfoCollection;

    public HefEmpresa() {
    }

    public HefEmpresa(Long idempresa) {
        this.idempresa = idempresa;
    }

    public Long getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Long idempresa) {
        this.idempresa = idempresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<HefPerfilEmpresa> getHefPerfilEmpresaCollection() {
        return hefPerfilEmpresaCollection;
    }

    public void setHefPerfilEmpresaCollection(Collection<HefPerfilEmpresa> hefPerfilEmpresaCollection) {
        this.hefPerfilEmpresaCollection = hefPerfilEmpresaCollection;
    }

    @XmlTransient
    public Collection<HefEmpresaInfo> getHefEmpresaInfoCollection() {
        return hefEmpresaInfoCollection;
    }

    public void setHefEmpresaInfoCollection(Collection<HefEmpresaInfo> hefEmpresaInfoCollection) {
        this.hefEmpresaInfoCollection = hefEmpresaInfoCollection;
    }

    @XmlTransient
    public Collection<HefFilialInfo> getHefFilialInfoCollection() {
        return hefFilialInfoCollection;
    }

    public void setHefFilialInfoCollection(Collection<HefFilialInfo> hefFilialInfoCollection) {
        this.hefFilialInfoCollection = hefFilialInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempresa != null ? idempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEmpresa)) {
            return false;
        }
        HefEmpresa other = (HefEmpresa) object;
        if ((this.idempresa == null && other.idempresa != null) || (this.idempresa != null && !this.idempresa.equals(other.idempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEmpresa[ idempresa=" + idempresa + " ]";
    }

    @XmlTransient
    public Collection<HefFornecedorInfo> getHefFornecedorInfoCollection() {
        return hefFornecedorInfoCollection;
    }

    public void setHefFornecedorInfoCollection(Collection<HefFornecedorInfo> hefFornecedorInfoCollection) {
        this.hefFornecedorInfoCollection = hefFornecedorInfoCollection;
    }

    @XmlTransient
    public Collection<HefClienteInfo> getHefClienteInfoCollection() {
        return hefClienteInfoCollection;
    }

    public void setHefClienteInfoCollection(Collection<HefClienteInfo> hefClienteInfoCollection) {
        this.hefClienteInfoCollection = hefClienteInfoCollection;
    }
    
}
