/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_estoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEstoque.findAll", query = "SELECT h FROM HefEstoque h"),
    @NamedQuery(name = "HefEstoque.findByIdestoque", query = "SELECT h FROM HefEstoque h WHERE h.idestoque = :idestoque"),
    @NamedQuery(name = "HefEstoque.findByQtdanterior", query = "SELECT h FROM HefEstoque h WHERE h.qtdanterior = :qtdanterior"),
    @NamedQuery(name = "HefEstoque.findByMovimentacao", query = "SELECT h FROM HefEstoque h WHERE h.movimentacao = :movimentacao"),
    @NamedQuery(name = "HefEstoque.findByQtdfinal", query = "SELECT h FROM HefEstoque h WHERE h.qtdfinal = :qtdfinal"),
    @NamedQuery(name = "HefEstoque.findByIdativo", query = "SELECT h FROM HefEstoque h WHERE h.idativo = :idativo")})
public class HefEstoque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestoque")
    private Long idestoque;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qtdanterior")
    private BigDecimal qtdanterior;
    @Column(name = "movimentacao")
    private BigDecimal movimentacao;
    @Column(name = "qtdfinal")
    private BigDecimal qtdfinal;
    @Column(name = "idativo")
    private Boolean idativo;
    @JoinColumn(name = "idfilial", referencedColumnName = "idfilial")
    @ManyToOne
    private HefFilial idfilial;
    @JoinColumn(name = "idintegracao_estoque", referencedColumnName = "idintegracao_estoque")
    @ManyToOne
    private HefIntegracaoEstoque idintegracaoEstoque;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne
    private HefProduto idproduto;

    public HefEstoque() {
    }

    public HefEstoque(Long idestoque) {
        this.idestoque = idestoque;
    }

    public Long getIdestoque() {
        return idestoque;
    }

    public void setIdestoque(Long idestoque) {
        this.idestoque = idestoque;
    }

    public BigDecimal getQtdanterior() {
        return qtdanterior;
    }

    public void setQtdanterior(BigDecimal qtdanterior) {
        this.qtdanterior = qtdanterior;
    }

    public BigDecimal getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(BigDecimal movimentacao) {
        this.movimentacao = movimentacao;
    }

    public BigDecimal getQtdfinal() {
        return qtdfinal;
    }

    public void setQtdfinal(BigDecimal qtdfinal) {
        this.qtdfinal = qtdfinal;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public HefFilial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(HefFilial idfilial) {
        this.idfilial = idfilial;
    }

    public HefIntegracaoEstoque getIdintegracaoEstoque() {
        return idintegracaoEstoque;
    }

    public void setIdintegracaoEstoque(HefIntegracaoEstoque idintegracaoEstoque) {
        this.idintegracaoEstoque = idintegracaoEstoque;
    }

    public HefProduto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(HefProduto idproduto) {
        this.idproduto = idproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestoque != null ? idestoque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEstoque)) {
            return false;
        }
        HefEstoque other = (HefEstoque) object;
        if ((this.idestoque == null && other.idestoque != null) || (this.idestoque != null && !this.idestoque.equals(other.idestoque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEstoque[ idestoque=" + idestoque + " ]";
    }
    
}
