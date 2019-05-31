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
@Table(name = "hef_etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEtapa.findAll", query = "SELECT h FROM HefEtapa h"),
    @NamedQuery(name = "HefEtapa.findByIdetapa", query = "SELECT h FROM HefEtapa h WHERE h.idetapa = :idetapa"),
    @NamedQuery(name = "HefEtapa.findByCodigo", query = "SELECT h FROM HefEtapa h WHERE h.codigo = :codigo")})
public class HefEtapa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idetapa")
    private Long idetapa;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idetapa")
    private Collection<HefEtapaInfo> hefEtapaInfoCollection;
    @OneToMany(mappedBy = "idetapa")
    private Collection<HefItensProduzidos> hefItensProduzidosCollection;
    @OneToMany(mappedBy = "idetapa")
    private Collection<HefEtapaProduto> hefEtapaProdutoCollection;

    public HefEtapa() {
    }

    public HefEtapa(Long idetapa) {
        this.idetapa = idetapa;
    }

    public Long getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(Long idetapa) {
        this.idetapa = idetapa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public Collection<HefEtapaInfo> getHefEtapaInfoCollection() {
        return hefEtapaInfoCollection;
    }

    public void setHefEtapaInfoCollection(Collection<HefEtapaInfo> hefEtapaInfoCollection) {
        this.hefEtapaInfoCollection = hefEtapaInfoCollection;
    }

    @XmlTransient
    public Collection<HefItensProduzidos> getHefItensProduzidosCollection() {
        return hefItensProduzidosCollection;
    }

    public void setHefItensProduzidosCollection(Collection<HefItensProduzidos> hefItensProduzidosCollection) {
        this.hefItensProduzidosCollection = hefItensProduzidosCollection;
    }

    @XmlTransient
    public Collection<HefEtapaProduto> getHefEtapaProdutoCollection() {
        return hefEtapaProdutoCollection;
    }

    public void setHefEtapaProdutoCollection(Collection<HefEtapaProduto> hefEtapaProdutoCollection) {
        this.hefEtapaProdutoCollection = hefEtapaProdutoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapa != null ? idetapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEtapa)) {
            return false;
        }
        HefEtapa other = (HefEtapa) object;
        if ((this.idetapa == null && other.idetapa != null) || (this.idetapa != null && !this.idetapa.equals(other.idetapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEtapa[ idetapa=" + idetapa + " ]";
    }
    
}
