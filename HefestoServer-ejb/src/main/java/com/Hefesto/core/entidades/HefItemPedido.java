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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_item_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefItemPedido.findAll", query = "SELECT h FROM HefItemPedido h"),
    @NamedQuery(name = "HefItemPedido.findByIditemPedido", query = "SELECT h FROM HefItemPedido h WHERE h.iditemPedido = :iditemPedido")})
public class HefItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iditem_pedido")
    private Long iditemPedido;
    @OneToMany(mappedBy = "iditem")
    private Collection<HefItemErro> hefItemErroCollection;
    @JoinColumn(name = "idpedido_info", referencedColumnName = "idpedido_info")
    @ManyToOne
    private HefPedidoInfo idpedidoInfo;
    @OneToMany(mappedBy = "iditemPedido")
    private Collection<HefItensProduzidos> hefItensProduzidosCollection;
    @OneToMany(mappedBy = "iditemPedido")
    private Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection;

    public HefItemPedido() {
    }

    public HefItemPedido(Long iditemPedido) {
        this.iditemPedido = iditemPedido;
    }

    public Long getIditemPedido() {
        return iditemPedido;
    }

    public void setIditemPedido(Long iditemPedido) {
        this.iditemPedido = iditemPedido;
    }

    @XmlTransient
    public Collection<HefItemErro> getHefItemErroCollection() {
        return hefItemErroCollection;
    }

    public void setHefItemErroCollection(Collection<HefItemErro> hefItemErroCollection) {
        this.hefItemErroCollection = hefItemErroCollection;
    }

    public HefPedidoInfo getIdpedidoInfo() {
        return idpedidoInfo;
    }

    public void setIdpedidoInfo(HefPedidoInfo idpedidoInfo) {
        this.idpedidoInfo = idpedidoInfo;
    }

    @XmlTransient
    public Collection<HefItensProduzidos> getHefItensProduzidosCollection() {
        return hefItensProduzidosCollection;
    }

    public void setHefItensProduzidosCollection(Collection<HefItensProduzidos> hefItensProduzidosCollection) {
        this.hefItensProduzidosCollection = hefItensProduzidosCollection;
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
        hash += (iditemPedido != null ? iditemPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefItemPedido)) {
            return false;
        }
        HefItemPedido other = (HefItemPedido) object;
        if ((this.iditemPedido == null && other.iditemPedido != null) || (this.iditemPedido != null && !this.iditemPedido.equals(other.iditemPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefItemPedido[ iditemPedido=" + iditemPedido + " ]";
    }
    
}
