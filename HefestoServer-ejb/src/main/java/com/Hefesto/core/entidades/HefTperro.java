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
@Table(name = "hef_tperro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefTperro.findAll", query = "SELECT h FROM HefTperro h"),
    @NamedQuery(name = "HefTperro.findByIdtperro", query = "SELECT h FROM HefTperro h WHERE h.idtperro = :idtperro"),
    @NamedQuery(name = "HefTperro.findByCodigo", query = "SELECT h FROM HefTperro h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefTperro.findByDescricao", query = "SELECT h FROM HefTperro h WHERE h.descricao = :descricao")})
public class HefTperro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtperro")
    private Long idtperro;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idtperro")
    private Collection<HefItemErro> hefItemErroCollection;

    public HefTperro() {
    }

    public HefTperro(Long idtperro) {
        this.idtperro = idtperro;
    }

    public Long getIdtperro() {
        return idtperro;
    }

    public void setIdtperro(Long idtperro) {
        this.idtperro = idtperro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<HefItemErro> getHefItemErroCollection() {
        return hefItemErroCollection;
    }

    public void setHefItemErroCollection(Collection<HefItemErro> hefItemErroCollection) {
        this.hefItemErroCollection = hefItemErroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtperro != null ? idtperro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefTperro)) {
            return false;
        }
        HefTperro other = (HefTperro) object;
        if ((this.idtperro == null && other.idtperro != null) || (this.idtperro != null && !this.idtperro.equals(other.idtperro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefTperro[ idtperro=" + idtperro + " ]";
    }
    
}
