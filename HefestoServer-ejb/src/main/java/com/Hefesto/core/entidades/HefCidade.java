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
@Table(name = "hef_cidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefCidade.findAll", query = "SELECT h FROM HefCidade h"),
    @NamedQuery(name = "HefCidade.findByIdcidade", query = "SELECT h FROM HefCidade h WHERE h.idcidade = :idcidade"),
    @NamedQuery(name = "HefCidade.findByDescricao", query = "SELECT h FROM HefCidade h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefCidade.findBySigla", query = "SELECT h FROM HefCidade h WHERE h.sigla = :sigla"),
    @NamedQuery(name = "HefCidade.findByCodigoIbge", query = "SELECT h FROM HefCidade h WHERE h.codigoIbge = :codigoIbge")})
public class HefCidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcidade")
    private Long idcidade;
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 250)
    @Column(name = "sigla")
    private String sigla;
    @Column(name = "codigo_ibge")
    private BigInteger codigoIbge;
    @JoinColumn(name = "iduf", referencedColumnName = "iduf")
    @ManyToOne
    private HefUf iduf;
    @OneToMany(mappedBy = "idcidade")
    private Collection<HefLogradouro> hefLogradouroCollection;

    public HefCidade() {
    }

    public HefCidade(Long idcidade) {
        this.idcidade = idcidade;
    }

    public Long getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(Long idcidade) {
        this.idcidade = idcidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public BigInteger getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(BigInteger codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public HefUf getIduf() {
        return iduf;
    }

    public void setIduf(HefUf iduf) {
        this.iduf = iduf;
    }

    @XmlTransient
    public Collection<HefLogradouro> getHefLogradouroCollection() {
        return hefLogradouroCollection;
    }

    public void setHefLogradouroCollection(Collection<HefLogradouro> hefLogradouroCollection) {
        this.hefLogradouroCollection = hefLogradouroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcidade != null ? idcidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefCidade)) {
            return false;
        }
        HefCidade other = (HefCidade) object;
        if ((this.idcidade == null && other.idcidade != null) || (this.idcidade != null && !this.idcidade.equals(other.idcidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefCidade[ idcidade=" + idcidade + " ]";
    }
    
}
