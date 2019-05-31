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
@Table(name = "hef_situacao_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefSituacaoItem.findAll", query = "SELECT h FROM HefSituacaoItem h"),
    @NamedQuery(name = "HefSituacaoItem.findByIdsituacao", query = "SELECT h FROM HefSituacaoItem h WHERE h.idsituacao = :idsituacao"),
    @NamedQuery(name = "HefSituacaoItem.findByCodigo", query = "SELECT h FROM HefSituacaoItem h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefSituacaoItem.findByDescricao", query = "SELECT h FROM HefSituacaoItem h WHERE h.descricao = :descricao")})
public class HefSituacaoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsituacao")
    private Long idsituacao;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idsituacao")
    private Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection;

    public HefSituacaoItem() {
    }

    public HefSituacaoItem(Long idsituacao) {
        this.idsituacao = idsituacao;
    }

    public Long getIdsituacao() {
        return idsituacao;
    }

    public void setIdsituacao(Long idsituacao) {
        this.idsituacao = idsituacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash += (idsituacao != null ? idsituacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefSituacaoItem)) {
            return false;
        }
        HefSituacaoItem other = (HefSituacaoItem) object;
        if ((this.idsituacao == null && other.idsituacao != null) || (this.idsituacao != null && !this.idsituacao.equals(other.idsituacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefSituacaoItem[ idsituacao=" + idsituacao + " ]";
    }
    
}
