/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "hef_fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefFornecedor.findAll", query = "SELECT h FROM HefFornecedor h"),
    @NamedQuery(name = "HefFornecedor.findByIdfornecedor", query = "SELECT h FROM HefFornecedor h WHERE h.idfornecedor = :idfornecedor"),
    @NamedQuery(name = "HefFornecedor.findByCodigoforncedor", query = "SELECT h FROM HefFornecedor h WHERE h.codigoforncedor = :codigoforncedor"),
    @NamedQuery(name = "HefFornecedor.findByCpfCnpj", query = "SELECT h FROM HefFornecedor h WHERE h.cpfCnpj = :cpfCnpj")})
public class HefFornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfornecedor")
    private Long idfornecedor;
    @Size(max = 250)
    @Column(name = "codigoforncedor")
    private String codigoforncedor;
    @Column(name = "cpf_cnpj")
    private BigInteger cpfCnpj;
    @OneToMany(mappedBy = "idfornecedor")
    private Collection<HefEstoqueMovs> hefEstoqueMovsCollection;
    @OneToMany(mappedBy = "idfornecedor")
    private Collection<HefFornecedorInfo> hefFornecedorInfoCollection;

    public HefFornecedor() {
    }

    public HefFornecedor(Long idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public Long getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Long idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public String getCodigoforncedor() {
        return codigoforncedor;
    }

    public void setCodigoforncedor(String codigoforncedor) {
        this.codigoforncedor = codigoforncedor;
    }

    public BigInteger getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(BigInteger cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    @XmlTransient
    public Collection<HefEstoqueMovs> getHefEstoqueMovsCollection() {
        return hefEstoqueMovsCollection;
    }

    public void setHefEstoqueMovsCollection(Collection<HefEstoqueMovs> hefEstoqueMovsCollection) {
        this.hefEstoqueMovsCollection = hefEstoqueMovsCollection;
    }

    @XmlTransient
    public Collection<HefFornecedorInfo> getHefFornecedorInfoCollection() {
        return hefFornecedorInfoCollection;
    }

    public void setHefFornecedorInfoCollection(Collection<HefFornecedorInfo> hefFornecedorInfoCollection) {
        this.hefFornecedorInfoCollection = hefFornecedorInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedor != null ? idfornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefFornecedor)) {
            return false;
        }
        HefFornecedor other = (HefFornecedor) object;
        if ((this.idfornecedor == null && other.idfornecedor != null) || (this.idfornecedor != null && !this.idfornecedor.equals(other.idfornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefFornecedor[ idfornecedor=" + idfornecedor + " ]";
    }
    
}
