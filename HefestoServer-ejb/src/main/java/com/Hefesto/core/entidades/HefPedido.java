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
@Table(name = "hef_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefPedido.findAll", query = "SELECT h FROM HefPedido h"),
    @NamedQuery(name = "HefPedido.findByIdpedido", query = "SELECT h FROM HefPedido h WHERE h.idpedido = :idpedido"),
    @NamedQuery(name = "HefPedido.findByCodigo", query = "SELECT h FROM HefPedido h WHERE h.codigo = :codigo")})
public class HefPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Long idpedido;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idpedido")
    private Collection<HefPedidoInfo> hefPedidoInfoCollection;

    public HefPedido() {
    }

    public HefPedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefPedido)) {
            return false;
        }
        HefPedido other = (HefPedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefPedido[ idpedido=" + idpedido + " ]";
    }
    
}
