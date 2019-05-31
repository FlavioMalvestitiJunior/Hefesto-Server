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
@Table(name = "hef_produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefProduto.findAll", query = "SELECT h FROM HefProduto h"),
    @NamedQuery(name = "HefProduto.findByIdproduto", query = "SELECT h FROM HefProduto h WHERE h.idproduto = :idproduto"),
    @NamedQuery(name = "HefProduto.findByCodigo", query = "SELECT h FROM HefProduto h WHERE h.codigo = :codigo")})
public class HefProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduto")
    private Long idproduto;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idproduto")
    private Collection<HefEstoque> hefEstoqueCollection;
    @OneToMany(mappedBy = "idproduto")
    private Collection<HefEstoqueMovs> hefEstoqueMovsCollection;
    @OneToMany(mappedBy = "idproduto")
    private Collection<HefItemPersonalizacao> hefItemPersonalizacaoCollection;

    public HefProduto() {
    }

    public HefProduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public Collection<HefEstoque> getHefEstoqueCollection() {
        return hefEstoqueCollection;
    }

    public void setHefEstoqueCollection(Collection<HefEstoque> hefEstoqueCollection) {
        this.hefEstoqueCollection = hefEstoqueCollection;
    }

    @XmlTransient
    public Collection<HefEstoqueMovs> getHefEstoqueMovsCollection() {
        return hefEstoqueMovsCollection;
    }

    public void setHefEstoqueMovsCollection(Collection<HefEstoqueMovs> hefEstoqueMovsCollection) {
        this.hefEstoqueMovsCollection = hefEstoqueMovsCollection;
    }

    @XmlTransient
    public Collection<HefItemPersonalizacao> getHefItemPersonalizacaoCollection() {
        return hefItemPersonalizacaoCollection;
    }

    public void setHefItemPersonalizacaoCollection(Collection<HefItemPersonalizacao> hefItemPersonalizacaoCollection) {
        this.hefItemPersonalizacaoCollection = hefItemPersonalizacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduto != null ? idproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefProduto)) {
            return false;
        }
        HefProduto other = (HefProduto) object;
        if ((this.idproduto == null && other.idproduto != null) || (this.idproduto != null && !this.idproduto.equals(other.idproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefProduto[ idproduto=" + idproduto + " ]";
    }
    
}
