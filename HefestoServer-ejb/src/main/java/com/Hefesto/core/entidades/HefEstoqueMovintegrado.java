/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
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
@Table(name = "hef_estoque_movintegrado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEstoqueMovintegrado.findAll", query = "SELECT h FROM HefEstoqueMovintegrado h"),
    @NamedQuery(name = "HefEstoqueMovintegrado.findByIdintegracaoMov", query = "SELECT h FROM HefEstoqueMovintegrado h WHERE h.idintegracaoMov = :idintegracaoMov"),
    @NamedQuery(name = "HefEstoqueMovintegrado.findByDthcadastro", query = "SELECT h FROM HefEstoqueMovintegrado h WHERE h.dthcadastro = :dthcadastro"),
    @NamedQuery(name = "HefEstoqueMovintegrado.findByIdativo", query = "SELECT h FROM HefEstoqueMovintegrado h WHERE h.idativo = :idativo")})
public class HefEstoqueMovintegrado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idintegracao_mov")
    private Long idintegracaoMov;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @Column(name = "idativo")
    private Boolean idativo;
    @JoinColumn(name = "idestoque_mov", referencedColumnName = "idestoque_mov")
    @ManyToOne
    private HefEstoqueMovs idestoqueMov;
    @JoinColumn(name = "idintegracao_estoque", referencedColumnName = "idintegracao_estoque")
    @ManyToOne
    private HefIntegracaoEstoque idintegracaoEstoque;

    public HefEstoqueMovintegrado() {
    }

    public HefEstoqueMovintegrado(Long idintegracaoMov) {
        this.idintegracaoMov = idintegracaoMov;
    }

    public Long getIdintegracaoMov() {
        return idintegracaoMov;
    }

    public void setIdintegracaoMov(Long idintegracaoMov) {
        this.idintegracaoMov = idintegracaoMov;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public HefEstoqueMovs getIdestoqueMov() {
        return idestoqueMov;
    }

    public void setIdestoqueMov(HefEstoqueMovs idestoqueMov) {
        this.idestoqueMov = idestoqueMov;
    }

    public HefIntegracaoEstoque getIdintegracaoEstoque() {
        return idintegracaoEstoque;
    }

    public void setIdintegracaoEstoque(HefIntegracaoEstoque idintegracaoEstoque) {
        this.idintegracaoEstoque = idintegracaoEstoque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idintegracaoMov != null ? idintegracaoMov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEstoqueMovintegrado)) {
            return false;
        }
        HefEstoqueMovintegrado other = (HefEstoqueMovintegrado) object;
        if ((this.idintegracaoMov == null && other.idintegracaoMov != null) || (this.idintegracaoMov != null && !this.idintegracaoMov.equals(other.idintegracaoMov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEstoqueMovintegrado[ idintegracaoMov=" + idintegracaoMov + " ]";
    }
    
}
