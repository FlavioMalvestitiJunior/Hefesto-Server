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
@Table(name = "hef_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefCliente.findAll", query = "SELECT h FROM HefCliente h"),
    @NamedQuery(name = "HefCliente.findByIdcliente", query = "SELECT h FROM HefCliente h WHERE h.idcliente = :idcliente"),
    @NamedQuery(name = "HefCliente.findByCodigocliente", query = "SELECT h FROM HefCliente h WHERE h.codigocliente = :codigocliente"),
    @NamedQuery(name = "HefCliente.findByCpfCnpj", query = "SELECT h FROM HefCliente h WHERE h.cpfCnpj = :cpfCnpj")})
public class HefCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Long idcliente;
    @Size(max = 250)
    @Column(name = "codigocliente")
    private String codigocliente;
    @Column(name = "cpf_cnpj")
    private BigInteger cpfCnpj;
    @OneToMany(mappedBy = "idcliente")
    private Collection<HefPedidoInfo> hefPedidoInfoCollection;
    @OneToMany(mappedBy = "idcliente")
    private Collection<HefClienteInfo> hefClienteInfoCollection;

    public HefCliente() {
    }

    public HefCliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getCodigocliente() {
        return codigocliente;
    }

    public void setCodigocliente(String codigocliente) {
        this.codigocliente = codigocliente;
    }

    public BigInteger getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(BigInteger cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    @XmlTransient
    public Collection<HefPedidoInfo> getHefPedidoInfoCollection() {
        return hefPedidoInfoCollection;
    }

    public void setHefPedidoInfoCollection(Collection<HefPedidoInfo> hefPedidoInfoCollection) {
        this.hefPedidoInfoCollection = hefPedidoInfoCollection;
    }

    @XmlTransient
    public Collection<HefClienteInfo> getHefClienteInfoCollection() {
        return hefClienteInfoCollection;
    }

    public void setHefClienteInfoCollection(Collection<HefClienteInfo> hefClienteInfoCollection) {
        this.hefClienteInfoCollection = hefClienteInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefCliente)) {
            return false;
        }
        HefCliente other = (HefCliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefCliente[ idcliente=" + idcliente + " ]";
    }
    
}
