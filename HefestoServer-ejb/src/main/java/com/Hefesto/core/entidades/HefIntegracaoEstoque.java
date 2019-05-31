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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "hef_integracao_estoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefIntegracaoEstoque.findAll", query = "SELECT h FROM HefIntegracaoEstoque h"),
    @NamedQuery(name = "HefIntegracaoEstoque.findByIdintegracaoEstoque", query = "SELECT h FROM HefIntegracaoEstoque h WHERE h.idintegracaoEstoque = :idintegracaoEstoque"),
    @NamedQuery(name = "HefIntegracaoEstoque.findByDescricao", query = "SELECT h FROM HefIntegracaoEstoque h WHERE h.descricao = :descricao")})
public class HefIntegracaoEstoque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idintegracao_estoque")
    private Long idintegracaoEstoque;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idintegracaoEstoque")
    private Collection<HefEstoque> hefEstoqueCollection;
    @OneToMany(mappedBy = "idintegracaoEstoque")
    private Collection<HefEstoqueMovintegrado> hefEstoqueMovintegradoCollection;
    @JoinColumn(name = "idfilial", referencedColumnName = "idfilial")
    @ManyToOne
    private HefFilial idfilial;

    public HefIntegracaoEstoque() {
    }

    public HefIntegracaoEstoque(Long idintegracaoEstoque) {
        this.idintegracaoEstoque = idintegracaoEstoque;
    }

    public Long getIdintegracaoEstoque() {
        return idintegracaoEstoque;
    }

    public void setIdintegracaoEstoque(Long idintegracaoEstoque) {
        this.idintegracaoEstoque = idintegracaoEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<HefEstoque> getHefEstoqueCollection() {
        return hefEstoqueCollection;
    }

    public void setHefEstoqueCollection(Collection<HefEstoque> hefEstoqueCollection) {
        this.hefEstoqueCollection = hefEstoqueCollection;
    }

    @XmlTransient
    public Collection<HefEstoqueMovintegrado> getHefEstoqueMovintegradoCollection() {
        return hefEstoqueMovintegradoCollection;
    }

    public void setHefEstoqueMovintegradoCollection(Collection<HefEstoqueMovintegrado> hefEstoqueMovintegradoCollection) {
        this.hefEstoqueMovintegradoCollection = hefEstoqueMovintegradoCollection;
    }

    public HefFilial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(HefFilial idfilial) {
        this.idfilial = idfilial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idintegracaoEstoque != null ? idintegracaoEstoque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefIntegracaoEstoque)) {
            return false;
        }
        HefIntegracaoEstoque other = (HefIntegracaoEstoque) object;
        if ((this.idintegracaoEstoque == null && other.idintegracaoEstoque != null) || (this.idintegracaoEstoque != null && !this.idintegracaoEstoque.equals(other.idintegracaoEstoque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefIntegracaoEstoque[ idintegracaoEstoque=" + idintegracaoEstoque + " ]";
    }
    
}
