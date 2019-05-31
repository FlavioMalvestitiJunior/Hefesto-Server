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
@Table(name = "hef_endereco_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEnderecoPedido.findAll", query = "SELECT h FROM HefEnderecoPedido h"),
    @NamedQuery(name = "HefEnderecoPedido.findByIdendereco", query = "SELECT h FROM HefEnderecoPedido h WHERE h.idendereco = :idendereco"),
    @NamedQuery(name = "HefEnderecoPedido.findByRua", query = "SELECT h FROM HefEnderecoPedido h WHERE h.rua = :rua"),
    @NamedQuery(name = "HefEnderecoPedido.findByBairro", query = "SELECT h FROM HefEnderecoPedido h WHERE h.bairro = :bairro"),
    @NamedQuery(name = "HefEnderecoPedido.findByNumero", query = "SELECT h FROM HefEnderecoPedido h WHERE h.numero = :numero")})
public class HefEnderecoPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idendereco")
    private Long idendereco;
    @Size(max = 700)
    @Column(name = "rua")
    private String rua;
    @Size(max = 700)
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "numero")
    private BigInteger numero;
    @OneToMany(mappedBy = "idenderecoCobranca")
    private Collection<HefPedidoInfo> hefPedidoInfoCollection;
    @OneToMany(mappedBy = "idenderecoEntrega")
    private Collection<HefPedidoInfo> hefPedidoInfoCollection1;
    @JoinColumn(name = "idlogradouro", referencedColumnName = "idlogradouro")
    @ManyToOne
    private HefLogradouro idlogradouro;

    public HefEnderecoPedido() {
    }

    public HefEnderecoPedido(Long idendereco) {
        this.idendereco = idendereco;
    }

    public Long getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(Long idendereco) {
        this.idendereco = idendereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    @XmlTransient
    public Collection<HefPedidoInfo> getHefPedidoInfoCollection() {
        return hefPedidoInfoCollection;
    }

    public void setHefPedidoInfoCollection(Collection<HefPedidoInfo> hefPedidoInfoCollection) {
        this.hefPedidoInfoCollection = hefPedidoInfoCollection;
    }

    @XmlTransient
    public Collection<HefPedidoInfo> getHefPedidoInfoCollection1() {
        return hefPedidoInfoCollection1;
    }

    public void setHefPedidoInfoCollection1(Collection<HefPedidoInfo> hefPedidoInfoCollection1) {
        this.hefPedidoInfoCollection1 = hefPedidoInfoCollection1;
    }

    public HefLogradouro getIdlogradouro() {
        return idlogradouro;
    }

    public void setIdlogradouro(HefLogradouro idlogradouro) {
        this.idlogradouro = idlogradouro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idendereco != null ? idendereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEnderecoPedido)) {
            return false;
        }
        HefEnderecoPedido other = (HefEnderecoPedido) object;
        if ((this.idendereco == null && other.idendereco != null) || (this.idendereco != null && !this.idendereco.equals(other.idendereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEnderecoPedido[ idendereco=" + idendereco + " ]";
    }
    
}
