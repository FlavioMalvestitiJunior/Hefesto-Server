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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_item_erro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefItemErro.findAll", query = "SELECT h FROM HefItemErro h"),
    @NamedQuery(name = "HefItemErro.findByIditemErro", query = "SELECT h FROM HefItemErro h WHERE h.iditemErro = :iditemErro"),
    @NamedQuery(name = "HefItemErro.findByIdetapa", query = "SELECT h FROM HefItemErro h WHERE h.idetapa = :idetapa"),
    @NamedQuery(name = "HefItemErro.findByQtd", query = "SELECT h FROM HefItemErro h WHERE h.qtd = :qtd"),
    @NamedQuery(name = "HefItemErro.findByIdativo", query = "SELECT h FROM HefItemErro h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefItemErro.findByIdperca", query = "SELECT h FROM HefItemErro h WHERE h.idperca = :idperca"),
    @NamedQuery(name = "HefItemErro.findByObservacao", query = "SELECT h FROM HefItemErro h WHERE h.observacao = :observacao"),
    @NamedQuery(name = "HefItemErro.findByDthcadastro", query = "SELECT h FROM HefItemErro h WHERE h.dthcadastro = :dthcadastro")})
public class HefItemErro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iditem_erro")
    private Long iditemErro;
    @Column(name = "idetapa")
    private BigInteger idetapa;
    @Column(name = "qtd")
    private BigInteger qtd;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "idperca")
    private Boolean idperca;
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIME)
    private Date dthcadastro;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne
    private HefFuncionario idfuncionario;
    @JoinColumn(name = "iditem", referencedColumnName = "iditem_pedido")
    @ManyToOne
    private HefItemPedido iditem;
    @JoinColumn(name = "idtperro", referencedColumnName = "idtperro")
    @ManyToOne
    private HefTperro idtperro;

    public HefItemErro() {
    }

    public HefItemErro(Long iditemErro) {
        this.iditemErro = iditemErro;
    }

    public Long getIditemErro() {
        return iditemErro;
    }

    public void setIditemErro(Long iditemErro) {
        this.iditemErro = iditemErro;
    }

    public BigInteger getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(BigInteger idetapa) {
        this.idetapa = idetapa;
    }

    public BigInteger getQtd() {
        return qtd;
    }

    public void setQtd(BigInteger qtd) {
        this.qtd = qtd;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public Boolean getIdperca() {
        return idperca;
    }

    public void setIdperca(Boolean idperca) {
        this.idperca = idperca;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefFuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(HefFuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public HefItemPedido getIditem() {
        return iditem;
    }

    public void setIditem(HefItemPedido iditem) {
        this.iditem = iditem;
    }

    public HefTperro getIdtperro() {
        return idtperro;
    }

    public void setIdtperro(HefTperro idtperro) {
        this.idtperro = idtperro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditemErro != null ? iditemErro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefItemErro)) {
            return false;
        }
        HefItemErro other = (HefItemErro) object;
        if ((this.iditemErro == null && other.iditemErro != null) || (this.iditemErro != null && !this.iditemErro.equals(other.iditemErro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefItemErro[ iditemErro=" + iditemErro + " ]";
    }
    
}
