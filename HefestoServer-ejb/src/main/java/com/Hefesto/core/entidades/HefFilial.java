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
@Table(name = "hef_filial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefFilial.findAll", query = "SELECT h FROM HefFilial h"),
    @NamedQuery(name = "HefFilial.findByIdfilial", query = "SELECT h FROM HefFilial h WHERE h.idfilial = :idfilial"),
    @NamedQuery(name = "HefFilial.findByDescricao", query = "SELECT h FROM HefFilial h WHERE h.descricao = :descricao")})
public class HefFilial implements Serializable {
    @OneToMany(mappedBy = "idfilial")
    private Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection;
    @OneToMany(mappedBy = "idfilial")
    private Collection<HefEstoque> hefEstoqueCollection;
    @OneToMany(mappedBy = "idfilial")
    private Collection<HefIntegracaoEstoque> hefIntegracaoEstoqueCollection;
    @OneToMany(mappedBy = "idfilial")
    private Collection<HefEstoqueMovs> hefEstoqueMovsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfilial")
    private Long idfilial;
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idfilial")
    private Collection<HefUsuarioInfo> hefUsuarioInfoCollection;
    @OneToMany(mappedBy = "idfilial")
    private Collection<HefFilialInfo> hefFilialInfoCollection;

    public HefFilial() {
    }

    public HefFilial(Long idfilial) {
        this.idfilial = idfilial;
    }

    public Long getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(Long idfilial) {
        this.idfilial = idfilial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<HefUsuarioInfo> getHefUsuarioInfoCollection() {
        return hefUsuarioInfoCollection;
    }

    public void setHefUsuarioInfoCollection(Collection<HefUsuarioInfo> hefUsuarioInfoCollection) {
        this.hefUsuarioInfoCollection = hefUsuarioInfoCollection;
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
        hash += (idfilial != null ? idfilial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefFilial)) {
            return false;
        }
        HefFilial other = (HefFilial) object;
        if ((this.idfilial == null && other.idfilial != null) || (this.idfilial != null && !this.idfilial.equals(other.idfilial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefFilial[ idfilial=" + idfilial + " ]";
    }

    @XmlTransient
    public Collection<HefFuncionarioInfo> getHefFuncionarioInfoCollection() {
        return hefFuncionarioInfoCollection;
    }

    public void setHefFuncionarioInfoCollection(Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection) {
        this.hefFuncionarioInfoCollection = hefFuncionarioInfoCollection;
    }

    @XmlTransient
    public Collection<HefEstoque> getHefEstoqueCollection() {
        return hefEstoqueCollection;
    }

    public void setHefEstoqueCollection(Collection<HefEstoque> hefEstoqueCollection) {
        this.hefEstoqueCollection = hefEstoqueCollection;
    }

    @XmlTransient
    public Collection<HefIntegracaoEstoque> getHefIntegracaoEstoqueCollection() {
        return hefIntegracaoEstoqueCollection;
    }

    public void setHefIntegracaoEstoqueCollection(Collection<HefIntegracaoEstoque> hefIntegracaoEstoqueCollection) {
        this.hefIntegracaoEstoqueCollection = hefIntegracaoEstoqueCollection;
    }

    @XmlTransient
    public Collection<HefEstoqueMovs> getHefEstoqueMovsCollection() {
        return hefEstoqueMovsCollection;
    }

    public void setHefEstoqueMovsCollection(Collection<HefEstoqueMovs> hefEstoqueMovsCollection) {
        this.hefEstoqueMovsCollection = hefEstoqueMovsCollection;
    }
    
}
