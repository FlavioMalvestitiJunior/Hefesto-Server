/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "hef_produto_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefProdutoInfo.findAll", query = "SELECT h FROM HefProdutoInfo h"),
    @NamedQuery(name = "HefProdutoInfo.findByIdprodutoInfo", query = "SELECT h FROM HefProdutoInfo h WHERE h.idprodutoInfo = :idprodutoInfo"),
    @NamedQuery(name = "HefProdutoInfo.findByIdproduto", query = "SELECT h FROM HefProdutoInfo h WHERE h.idproduto = :idproduto"),
    @NamedQuery(name = "HefProdutoInfo.findByDescricao", query = "SELECT h FROM HefProdutoInfo h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefProdutoInfo.findByObservacoes", query = "SELECT h FROM HefProdutoInfo h WHERE h.observacoes = :observacoes"),
    @NamedQuery(name = "HefProdutoInfo.findByIdativo", query = "SELECT h FROM HefProdutoInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefProdutoInfo.findByEstoqueMin", query = "SELECT h FROM HefProdutoInfo h WHERE h.estoqueMin = :estoqueMin"),
    @NamedQuery(name = "HefProdutoInfo.findByEstoqueMax", query = "SELECT h FROM HefProdutoInfo h WHERE h.estoqueMax = :estoqueMax"),
    @NamedQuery(name = "HefProdutoInfo.findByDthcadastro", query = "SELECT h FROM HefProdutoInfo h WHERE h.dthcadastro = :dthcadastro")})
public class HefProdutoInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduto_info")
    private Long idprodutoInfo;
    @Column(name = "idproduto")
    private BigInteger idproduto;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 2147483647)
    @Column(name = "observacoes")
    private String observacoes;
    @Column(name = "idativo")
    private Boolean idativo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "estoque_min")
    private BigDecimal estoqueMin;
    @Column(name = "estoque_max")
    private BigDecimal estoqueMax;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idunidade", referencedColumnName = "idunidade")
    @ManyToOne
    private HefUnidadeMedida idunidade;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;
    @OneToMany(mappedBy = "idprodutoInfo")
    private Collection<HefEtapaProduto> hefEtapaProdutoCollection;
    @OneToMany(mappedBy = "idprodutoInfo")
    private Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection;

    public HefProdutoInfo() {
    }

    public HefProdutoInfo(Long idprodutoInfo) {
        this.idprodutoInfo = idprodutoInfo;
    }

    public Long getIdprodutoInfo() {
        return idprodutoInfo;
    }

    public void setIdprodutoInfo(Long idprodutoInfo) {
        this.idprodutoInfo = idprodutoInfo;
    }

    public BigInteger getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(BigInteger idproduto) {
        this.idproduto = idproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public BigDecimal getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(BigDecimal estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public BigDecimal getEstoqueMax() {
        return estoqueMax;
    }

    public void setEstoqueMax(BigDecimal estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefUnidadeMedida getIdunidade() {
        return idunidade;
    }

    public void setIdunidade(HefUnidadeMedida idunidade) {
        this.idunidade = idunidade;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<HefEtapaProduto> getHefEtapaProdutoCollection() {
        return hefEtapaProdutoCollection;
    }

    public void setHefEtapaProdutoCollection(Collection<HefEtapaProduto> hefEtapaProdutoCollection) {
        this.hefEtapaProdutoCollection = hefEtapaProdutoCollection;
    }

    @XmlTransient
    public Collection<HefItemPedidoInfo> getHefItemPedidoInfoCollection() {
        return hefItemPedidoInfoCollection;
    }

    public void setHefItemPedidoInfoCollection(Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection) {
        this.hefItemPedidoInfoCollection = hefItemPedidoInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprodutoInfo != null ? idprodutoInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefProdutoInfo)) {
            return false;
        }
        HefProdutoInfo other = (HefProdutoInfo) object;
        if ((this.idprodutoInfo == null && other.idprodutoInfo != null) || (this.idprodutoInfo != null && !this.idprodutoInfo.equals(other.idprodutoInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefProdutoInfo[ idprodutoInfo=" + idprodutoInfo + " ]";
    }
    
}
