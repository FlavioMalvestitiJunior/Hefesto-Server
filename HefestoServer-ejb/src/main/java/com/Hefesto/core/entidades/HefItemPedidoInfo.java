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
@Table(name = "hef_item_pedido_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefItemPedidoInfo.findAll", query = "SELECT h FROM HefItemPedidoInfo h"),
    @NamedQuery(name = "HefItemPedidoInfo.findByIditemPedidoInfo", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.iditemPedidoInfo = :iditemPedidoInfo"),
    @NamedQuery(name = "HefItemPedidoInfo.findByIdativo", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefItemPedidoInfo.findByTamanho", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.tamanho = :tamanho"),
    @NamedQuery(name = "HefItemPedidoInfo.findByObservacoes", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.observacoes = :observacoes"),
    @NamedQuery(name = "HefItemPedidoInfo.findByQtd", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.qtd = :qtd"),
    @NamedQuery(name = "HefItemPedidoInfo.findByValor", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.valor = :valor"),
    @NamedQuery(name = "HefItemPedidoInfo.findByDesconto", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.desconto = :desconto"),
    @NamedQuery(name = "HefItemPedidoInfo.findByIddescontoPercentual", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.iddescontoPercentual = :iddescontoPercentual"),
    @NamedQuery(name = "HefItemPedidoInfo.findByIdserigrafia", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.idserigrafia = :idserigrafia"),
    @NamedQuery(name = "HefItemPedidoInfo.findByIdbordado", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.idbordado = :idbordado"),
    @NamedQuery(name = "HefItemPedidoInfo.findByIdartedigital", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.idartedigital = :idartedigital"),
    @NamedQuery(name = "HefItemPedidoInfo.findByDthcadastro", query = "SELECT h FROM HefItemPedidoInfo h WHERE h.dthcadastro = :dthcadastro")})
public class HefItemPedidoInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iditem_pedido_info")
    private Long iditemPedidoInfo;
    @Column(name = "idativo")
    private Boolean idativo;
    @Size(max = 10)
    @Column(name = "tamanho")
    private String tamanho;
    @Size(max = 2147483647)
    @Column(name = "observacoes")
    private String observacoes;
    @Column(name = "qtd")
    private BigInteger qtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Column(name = "iddesconto_percentual")
    private Boolean iddescontoPercentual;
    @Column(name = "idserigrafia")
    private Boolean idserigrafia;
    @Column(name = "idbordado")
    private Boolean idbordado;
    @Column(name = "idartedigital")
    private Boolean idartedigital;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idamostra", referencedColumnName = "idamostra")
    @ManyToOne
    private HefAmostraItem idamostra;
    @JoinColumn(name = "iditem_pedido", referencedColumnName = "iditem_pedido")
    @ManyToOne
    private HefItemPedido iditemPedido;
    @JoinColumn(name = "idproduto_info", referencedColumnName = "idproduto_info")
    @ManyToOne
    private HefProdutoInfo idprodutoInfo;
    @JoinColumn(name = "idsituacao", referencedColumnName = "idsituacao")
    @ManyToOne
    private HefSituacaoItem idsituacao;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;
    @OneToMany(mappedBy = "iditemInfo")
    private Collection<HefItemPersonalizacao> hefItemPersonalizacaoCollection;

    public HefItemPedidoInfo() {
    }

    public HefItemPedidoInfo(Long iditemPedidoInfo) {
        this.iditemPedidoInfo = iditemPedidoInfo;
    }

    public Long getIditemPedidoInfo() {
        return iditemPedidoInfo;
    }

    public void setIditemPedidoInfo(Long iditemPedidoInfo) {
        this.iditemPedidoInfo = iditemPedidoInfo;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public BigInteger getQtd() {
        return qtd;
    }

    public void setQtd(BigInteger qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Boolean getIddescontoPercentual() {
        return iddescontoPercentual;
    }

    public void setIddescontoPercentual(Boolean iddescontoPercentual) {
        this.iddescontoPercentual = iddescontoPercentual;
    }

    public Boolean getIdserigrafia() {
        return idserigrafia;
    }

    public void setIdserigrafia(Boolean idserigrafia) {
        this.idserigrafia = idserigrafia;
    }

    public Boolean getIdbordado() {
        return idbordado;
    }

    public void setIdbordado(Boolean idbordado) {
        this.idbordado = idbordado;
    }

    public Boolean getIdartedigital() {
        return idartedigital;
    }

    public void setIdartedigital(Boolean idartedigital) {
        this.idartedigital = idartedigital;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefAmostraItem getIdamostra() {
        return idamostra;
    }

    public void setIdamostra(HefAmostraItem idamostra) {
        this.idamostra = idamostra;
    }

    public HefItemPedido getIditemPedido() {
        return iditemPedido;
    }

    public void setIditemPedido(HefItemPedido iditemPedido) {
        this.iditemPedido = iditemPedido;
    }

    public HefProdutoInfo getIdprodutoInfo() {
        return idprodutoInfo;
    }

    public void setIdprodutoInfo(HefProdutoInfo idprodutoInfo) {
        this.idprodutoInfo = idprodutoInfo;
    }

    public HefSituacaoItem getIdsituacao() {
        return idsituacao;
    }

    public void setIdsituacao(HefSituacaoItem idsituacao) {
        this.idsituacao = idsituacao;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
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
        hash += (iditemPedidoInfo != null ? iditemPedidoInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefItemPedidoInfo)) {
            return false;
        }
        HefItemPedidoInfo other = (HefItemPedidoInfo) object;
        if ((this.iditemPedidoInfo == null && other.iditemPedidoInfo != null) || (this.iditemPedidoInfo != null && !this.iditemPedidoInfo.equals(other.iditemPedidoInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefItemPedidoInfo[ iditemPedidoInfo=" + iditemPedidoInfo + " ]";
    }
    
}
