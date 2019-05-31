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
@Table(name = "hef_pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefPais.findAll", query = "SELECT h FROM HefPais h"),
    @NamedQuery(name = "HefPais.findByIdpais", query = "SELECT h FROM HefPais h WHERE h.idpais = :idpais"),
    @NamedQuery(name = "HefPais.findBySigla", query = "SELECT h FROM HefPais h WHERE h.sigla = :sigla"),
    @NamedQuery(name = "HefPais.findByDescricao", query = "SELECT h FROM HefPais h WHERE h.descricao = :descricao")})
public class HefPais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpais")
    private Long idpais;
    @Size(max = 250)
    @Column(name = "sigla")
    private String sigla;
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idpais")
    private Collection<HefUf> hefUfCollection;

    public HefPais() {
    }

    public HefPais(Long idpais) {
        this.idpais = idpais;
    }

    public Long getIdpais() {
        return idpais;
    }

    public void setIdpais(Long idpais) {
        this.idpais = idpais;
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

    @XmlTransient
    public Collection<HefUf> getHefUfCollection() {
        return hefUfCollection;
    }

    public void setHefUfCollection(Collection<HefUf> hefUfCollection) {
        this.hefUfCollection = hefUfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpais != null ? idpais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefPais)) {
            return false;
        }
        HefPais other = (HefPais) object;
        if ((this.idpais == null && other.idpais != null) || (this.idpais != null && !this.idpais.equals(other.idpais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefPais[ idpais=" + idpais + " ]";
    }
    
}
