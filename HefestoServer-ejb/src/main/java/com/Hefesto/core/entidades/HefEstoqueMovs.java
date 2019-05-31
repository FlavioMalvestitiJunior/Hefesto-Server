/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "hef_estoque_movs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEstoqueMovs.findAll", query = "SELECT h FROM HefEstoqueMovs h"),
    @NamedQuery(name = "HefEstoqueMovs.findByIdestoqueMov", query = "SELECT h FROM HefEstoqueMovs h WHERE h.idestoqueMov = :idestoqueMov"),
    @NamedQuery(name = "HefEstoqueMovs.findByIdativo", query = "SELECT h FROM HefEstoqueMovs h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefEstoqueMovs.findByDthmovimentacao", query = "SELECT h FROM HefEstoqueMovs h WHERE h.dthmovimentacao = :dthmovimentacao"),
    @NamedQuery(name = "HefEstoqueMovs.findByIdcompra", query = "SELECT h FROM HefEstoqueMovs h WHERE h.idcompra = :idcompra"),
    @NamedQuery(name = "HefEstoqueMovs.findByQtd", query = "SELECT h FROM HefEstoqueMovs h WHERE h.qtd = :qtd"),
    @NamedQuery(name = "HefEstoqueMovs.findByValor", query = "SELECT h FROM HefEstoqueMovs h WHERE h.valor = :valor")})
public class HefEstoqueMovs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestoque_mov")
    private Long idestoqueMov;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthmovimentacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthmovimentacao;
    @Column(name = "idcompra")
    private Boolean idcompra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qtd")
    private BigDecimal qtd;
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "idfilial", referencedColumnName = "idfilial")
    @ManyToOne
    private HefFilial idfilial;
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne
    private HefFornecedor idfornecedor;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne
    private HefProduto idproduto;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefEstoqueMovs() {
    }

    public HefEstoqueMovs(Long idestoqueMov) {
        this.idestoqueMov = idestoqueMov;
    }

    public Long getIdestoqueMov() {
        return idestoqueMov;
    }

    public void setIdestoqueMov(Long idestoqueMov) {
        this.idestoqueMov = idestoqueMov;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public Date getDthmovimentacao() {
        return dthmovimentacao;
    }

    public void setDthmovimentacao(Date dthmovimentacao) {
        this.dthmovimentacao = dthmovimentacao;
    }

    public Boolean getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Boolean idcompra) {
        this.idcompra = idcompra;
    }

    public BigDecimal getQtd() {
        return qtd;
    }

    public void setQtd(BigDecimal qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public HefFilial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(HefFilial idfilial) {
        this.idfilial = idfilial;
    }

    public HefFornecedor getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(HefFornecedor idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public HefProduto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(HefProduto idproduto) {
        this.idproduto = idproduto;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestoqueMov != null ? idestoqueMov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEstoqueMovs)) {
            return false;
        }
        HefEstoqueMovs other = (HefEstoqueMovs) object;
        if ((this.idestoqueMov == null && other.idestoqueMov != null) || (this.idestoqueMov != null && !this.idestoqueMov.equals(other.idestoqueMov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEstoqueMovs[ idestoqueMov=" + idestoqueMov + " ]";
    }
    
}
