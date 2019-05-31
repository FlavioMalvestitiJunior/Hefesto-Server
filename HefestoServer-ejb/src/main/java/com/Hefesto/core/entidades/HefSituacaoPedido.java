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
@Table(name = "hef_situacao_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefSituacaoPedido.findAll", query = "SELECT h FROM HefSituacaoPedido h"),
    @NamedQuery(name = "HefSituacaoPedido.findByCodigo", query = "SELECT h FROM HefSituacaoPedido h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefSituacaoPedido.findByDescricao", query = "SELECT h FROM HefSituacaoPedido h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefSituacaoPedido.findByIdsituacaoPedido", query = "SELECT h FROM HefSituacaoPedido h WHERE h.idsituacaoPedido = :idsituacaoPedido")})
public class HefSituacaoPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsituacao_pedido")
    private Long idsituacaoPedido;
    @OneToMany(mappedBy = "idsituacao")
    private Collection<HefPedidoInfo> hefPedidoInfoCollection;

    public HefSituacaoPedido() {
    }

    public HefSituacaoPedido(Long idsituacaoPedido) {
        this.idsituacaoPedido = idsituacaoPedido;
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

    public Long getIdsituacaoPedido() {
        return idsituacaoPedido;
    }

    public void setIdsituacaoPedido(Long idsituacaoPedido) {
        this.idsituacaoPedido = idsituacaoPedido;
    }

    @XmlTransient
    public Collection<HefPedidoInfo> getHefPedidoInfoCollection() {
        return hefPedidoInfoCollection;
    }

    public void setHefPedidoInfoCollection(Collection<HefPedidoInfo> hefPedidoInfoCollection) {
        this.hefPedidoInfoCollection = hefPedidoInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsituacaoPedido != null ? idsituacaoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefSituacaoPedido)) {
            return false;
        }
        HefSituacaoPedido other = (HefSituacaoPedido) object;
        if ((this.idsituacaoPedido == null && other.idsituacaoPedido != null) || (this.idsituacaoPedido != null && !this.idsituacaoPedido.equals(other.idsituacaoPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefSituacaoPedido[ idsituacaoPedido=" + idsituacaoPedido + " ]";
    }
    
}
