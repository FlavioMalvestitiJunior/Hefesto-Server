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
@Table(name = "hef_logradouro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefLogradouro.findAll", query = "SELECT h FROM HefLogradouro h"),
    @NamedQuery(name = "HefLogradouro.findByIdlogradouro", query = "SELECT h FROM HefLogradouro h WHERE h.idlogradouro = :idlogradouro"),
    @NamedQuery(name = "HefLogradouro.findByDescricao", query = "SELECT h FROM HefLogradouro h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefLogradouro.findByCep", query = "SELECT h FROM HefLogradouro h WHERE h.cep = :cep")})
public class HefLogradouro implements Serializable {
    @OneToMany(mappedBy = "idlogradouro")
    private Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection;
    @OneToMany(mappedBy = "idlogradouro")
    private Collection<HefEnderecoPedido> hefEnderecoPedidoCollection;
    @OneToMany(mappedBy = "idlogradouro")
    private Collection<HefFornecedorInfo> hefFornecedorInfoCollection;
    @OneToMany(mappedBy = "idlogradouro")
    private Collection<HefClienteInfo> hefClienteInfoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlogradouro")
    private Long idlogradouro;
    @Size(max = 600)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "cep")
    private BigInteger cep;
    @OneToMany(mappedBy = "idlogradouro")
    private Collection<HefEmpresaInfo> hefEmpresaInfoCollection;
    @OneToMany(mappedBy = "idlogradouro")
    private Collection<HefFilialInfo> hefFilialInfoCollection;
    @JoinColumn(name = "idcidade", referencedColumnName = "idcidade")
    @ManyToOne
    private HefCidade idcidade;

    public HefLogradouro() {
    }

    public HefLogradouro(Long idlogradouro) {
        this.idlogradouro = idlogradouro;
    }

    public Long getIdlogradouro() {
        return idlogradouro;
    }

    public void setIdlogradouro(Long idlogradouro) {
        this.idlogradouro = idlogradouro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigInteger getCep() {
        return cep;
    }

    public void setCep(BigInteger cep) {
        this.cep = cep;
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

    public HefCidade getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(HefCidade idcidade) {
        this.idcidade = idcidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlogradouro != null ? idlogradouro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefLogradouro)) {
            return false;
        }
        HefLogradouro other = (HefLogradouro) object;
        if ((this.idlogradouro == null && other.idlogradouro != null) || (this.idlogradouro != null && !this.idlogradouro.equals(other.idlogradouro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefLogradouro[ idlogradouro=" + idlogradouro + " ]";
    }

    @XmlTransient
    public Collection<HefFuncionarioInfo> getHefFuncionarioInfoCollection() {
        return hefFuncionarioInfoCollection;
    }

    public void setHefFuncionarioInfoCollection(Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection) {
        this.hefFuncionarioInfoCollection = hefFuncionarioInfoCollection;
    }

    @XmlTransient
    public Collection<HefEnderecoPedido> getHefEnderecoPedidoCollection() {
        return hefEnderecoPedidoCollection;
    }

    public void setHefEnderecoPedidoCollection(Collection<HefEnderecoPedido> hefEnderecoPedidoCollection) {
        this.hefEnderecoPedidoCollection = hefEnderecoPedidoCollection;
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
