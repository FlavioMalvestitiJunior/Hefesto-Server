/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_item_personalizacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefItemPersonalizacao.findAll", query = "SELECT h FROM HefItemPersonalizacao h"),
    @NamedQuery(name = "HefItemPersonalizacao.findByIdpersonalizacao", query = "SELECT h FROM HefItemPersonalizacao h WHERE h.idpersonalizacao = :idpersonalizacao"),
    @NamedQuery(name = "HefItemPersonalizacao.findByQtd", query = "SELECT h FROM HefItemPersonalizacao h WHERE h.qtd = :qtd")})
public class HefItemPersonalizacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersonalizacao")
    private Long idpersonalizacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qtd")
    private BigDecimal qtd;
    @JoinColumn(name = "iditem_info", referencedColumnName = "iditem_pedido_info")
    @ManyToOne
    private HefItemPedidoInfo iditemInfo;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne
    private HefProduto idproduto;

    public HefItemPersonalizacao() {
    }

    public HefItemPersonalizacao(Long idpersonalizacao) {
        this.idpersonalizacao = idpersonalizacao;
    }

    public Long getIdpersonalizacao() {
        return idpersonalizacao;
    }

    public void setIdpersonalizacao(Long idpersonalizacao) {
        this.idpersonalizacao = idpersonalizacao;
    }

    public BigDecimal getQtd() {
        return qtd;
    }

    public void setQtd(BigDecimal qtd) {
        this.qtd = qtd;
    }

    public HefItemPedidoInfo getIditemInfo() {
        return iditemInfo;
    }

    public void setIditemInfo(HefItemPedidoInfo iditemInfo) {
        this.iditemInfo = iditemInfo;
    }

    public HefProduto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(HefProduto idproduto) {
        this.idproduto = idproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonalizacao != null ? idpersonalizacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefItemPersonalizacao)) {
            return false;
        }
        HefItemPersonalizacao other = (HefItemPersonalizacao) object;
        if ((this.idpersonalizacao == null && other.idpersonalizacao != null) || (this.idpersonalizacao != null && !this.idpersonalizacao.equals(other.idpersonalizacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefItemPersonalizacao[ idpersonalizacao=" + idpersonalizacao + " ]";
    }
    
}
