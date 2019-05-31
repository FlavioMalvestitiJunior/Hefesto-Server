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
@Table(name = "hef_uf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefUf.findAll", query = "SELECT h FROM HefUf h"),
    @NamedQuery(name = "HefUf.findByIduf", query = "SELECT h FROM HefUf h WHERE h.iduf = :iduf"),
    @NamedQuery(name = "HefUf.findBySigla", query = "SELECT h FROM HefUf h WHERE h.sigla = :sigla"),
    @NamedQuery(name = "HefUf.findByDescricao", query = "SELECT h FROM HefUf h WHERE h.descricao = :descricao")})
public class HefUf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduf")
    private Long iduf;
    @Size(max = 250)
    @Column(name = "sigla")
    private String sigla;
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "idpais", referencedColumnName = "idpais")
    @ManyToOne
    private HefPais idpais;
    @OneToMany(mappedBy = "iduf")
    private Collection<HefCidade> hefCidadeCollection;

    public HefUf() {
    }

    public HefUf(Long iduf) {
        this.iduf = iduf;
    }

    public Long getIduf() {
        return iduf;
    }

    public void setIduf(Long iduf) {
        this.iduf = iduf;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HefPais getIdpais() {
        return idpais;
    }

    public void setIdpais(HefPais idpais) {
        this.idpais = idpais;
    }

    @XmlTransient
    public Collection<HefCidade> getHefCidadeCollection() {
        return hefCidadeCollection;
    }

    public void setHefCidadeCollection(Collection<HefCidade> hefCidadeCollection) {
        this.hefCidadeCollection = hefCidadeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduf != null ? iduf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefUf)) {
            return false;
        }
        HefUf other = (HefUf) object;
        if ((this.iduf == null && other.iduf != null) || (this.iduf != null && !this.iduf.equals(other.iduf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefUf[ iduf=" + iduf + " ]";
    }
    
}
