/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_pedido_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefPedidoInfo.findAll", query = "SELECT h FROM HefPedidoInfo h"),
    @NamedQuery(name = "HefPedidoInfo.findByIdpedidoInfo", query = "SELECT h FROM HefPedidoInfo h WHERE h.idpedidoInfo = :idpedidoInfo"),
    @NamedQuery(name = "HefPedidoInfo.findByDthcadastro", query = "SELECT h FROM HefPedidoInfo h WHERE h.dthcadastro = :dthcadastro")})
public class HefPedidoInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido_info")
    private Long idpedidoInfo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private HefCliente idcliente;
    @JoinColumn(name = "idendereco_cobranca", referencedColumnName = "idendereco")
    @ManyToOne
    private HefEnderecoPedido idenderecoCobranca;
    @JoinColumn(name = "idendereco_entrega", referencedColumnName = "idendereco")
    @ManyToOne
    private HefEnderecoPedido idenderecoEntrega;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne
    private HefPedido idpedido;
    @JoinColumn(name = "idsituacao", referencedColumnName = "idsituacao_pedido")
    @ManyToOne
    private HefSituacaoPedido idsituacao;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;
    @OneToMany(mappedBy = "idpedidoInfo")
    private Collection<HefItemPedido> hefItemPedidoCollection;

    public HefPedidoInfo() {
    }

    public HefPedidoInfo(Long idpedidoInfo) {
        this.idpedidoInfo = idpedidoInfo;
    }

    public Long getIdpedidoInfo() {
        return idpedidoInfo;
    }

    public void setIdpedidoInfo(Long idpedidoInfo) {
        this.idpedidoInfo = idpedidoInfo;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefCliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(HefCliente idcliente) {
        this.idcliente = idcliente;
    }

    public HefEnderecoPedido getIdenderecoCobranca() {
        return idenderecoCobranca;
    }

    public void setIdenderecoCobranca(HefEnderecoPedido idenderecoCobranca) {
        this.idenderecoCobranca = idenderecoCobranca;
    }

    public HefEnderecoPedido getIdenderecoEntrega() {
        return idenderecoEntrega;
    }

    public void setIdenderecoEntrega(HefEnderecoPedido idenderecoEntrega) {
        this.idenderecoEntrega = idenderecoEntrega;
    }

    public HefPedido getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(HefPedido idpedido) {
        this.idpedido = idpedido;
    }

    public HefSituacaoPedido getIdsituacao() {
        return idsituacao;
    }

    public void setIdsituacao(HefSituacaoPedido idsituacao) {
        this.idsituacao = idsituacao;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<HefItemPedido> getHefItemPedidoCollection() {
        return hefItemPedidoCollection;
    }

    public void setHefItemPedidoCollection(Collection<HefItemPedido> hefItemPedidoCollection) {
        this.hefItemPedidoCollection = hefItemPedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidoInfo != null ? idpedidoInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefPedidoInfo)) {
            return false;
        }
        HefPedidoInfo other = (HefPedidoInfo) object;
        if ((this.idpedidoInfo == null && other.idpedidoInfo != null) || (this.idpedidoInfo != null && !this.idpedidoInfo.equals(other.idpedidoInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefPedidoInfo[ idpedidoInfo=" + idpedidoInfo + " ]";
    }
    
}
