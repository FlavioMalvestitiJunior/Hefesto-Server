/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_itens_produzidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefItensProduzidos.findAll", query = "SELECT h FROM HefItensProduzidos h"),
    @NamedQuery(name = "HefItensProduzidos.findByIdtotalProduzido", query = "SELECT h FROM HefItensProduzidos h WHERE h.idtotalProduzido = :idtotalProduzido"),
    @NamedQuery(name = "HefItensProduzidos.findByQtd", query = "SELECT h FROM HefItensProduzidos h WHERE h.qtd = :qtd"),
    @NamedQuery(name = "HefItensProduzidos.findByIdusuario", query = "SELECT h FROM HefItensProduzidos h WHERE h.idusuario = :idusuario"),
    @NamedQuery(name = "HefItensProduzidos.findByIdativo", query = "SELECT h FROM HefItensProduzidos h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefItensProduzidos.findByDthcadastro", query = "SELECT h FROM HefItensProduzidos h WHERE h.dthcadastro = :dthcadastro")})
public class HefItensProduzidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtotal_produzido")
    private Long idtotalProduzido;
    @Column(name = "qtd")
    private BigInteger qtd;
    @Column(name = "idusuario")
    private BigInteger idusuario;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idetapa", referencedColumnName = "idetapa")
    @ManyToOne
    private HefEtapa idetapa;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne
    private HefFuncionario idfuncionario;
    @JoinColumn(name = "iditem_pedido", referencedColumnName = "iditem_pedido")
    @ManyToOne
    private HefItemPedido iditemPedido;

    public HefItensProduzidos() {
    }

    public HefItensProduzidos(Long idtotalProduzido) {
        this.idtotalProduzido = idtotalProduzido;
    }

    public Long getIdtotalProduzido() {
        return idtotalProduzido;
    }

    public void setIdtotalProduzido(Long idtotalProduzido) {
        this.idtotalProduzido = idtotalProduzido;
    }

    public BigInteger getQtd() {
        return qtd;
    }

    public void setQtd(BigInteger qtd) {
        this.qtd = qtd;
    }

    public BigInteger getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(BigInteger idusuario) {
        this.idusuario = idusuario;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefEtapa getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(HefEtapa idetapa) {
        this.idetapa = idetapa;
    }

    public HefFuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(HefFuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public HefItemPedido getIditemPedido() {
        return iditemPedido;
    }

    public void setIditemPedido(HefItemPedido iditemPedido) {
        this.iditemPedido = iditemPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtotalProduzido != null ? idtotalProduzido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefItensProduzidos)) {
            return false;
        }
        HefItensProduzidos other = (HefItensProduzidos) object;
        if ((this.idtotalProduzido == null && other.idtotalProduzido != null) || (this.idtotalProduzido != null && !this.idtotalProduzido.equals(other.idtotalProduzido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefItensProduzidos[ idtotalProduzido=" + idtotalProduzido + " ]";
    }
    
}
