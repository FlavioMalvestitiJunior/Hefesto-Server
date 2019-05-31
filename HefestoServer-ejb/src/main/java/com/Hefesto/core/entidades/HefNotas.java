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
@Table(name = "hef_notas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefNotas.findAll", query = "SELECT h FROM HefNotas h"),
    @NamedQuery(name = "HefNotas.findByIdnotas", query = "SELECT h FROM HefNotas h WHERE h.idnotas = :idnotas"),
    @NamedQuery(name = "HefNotas.findByVersao", query = "SELECT h FROM HefNotas h WHERE h.versao = :versao"),
    @NamedQuery(name = "HefNotas.findByDatarelease", query = "SELECT h FROM HefNotas h WHERE h.datarelease = :datarelease"),
    @NamedQuery(name = "HefNotas.findByNotas", query = "SELECT h FROM HefNotas h WHERE h.notas = :notas")})
public class HefNotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotas")
    private Long idnotas;
    @Size(max = 700)
    @Column(name = "versao")
    private String versao;
    @Column(name = "datarelease")
    @Temporal(TemporalType.DATE)
    private Date datarelease;
    @Size(max = 2147483647)
    @Column(name = "notas")
    private String notas;

    public HefNotas() {
    }

    public HefNotas(Long idnotas) {
        this.idnotas = idnotas;
    }

    public Long getIdnotas() {
        return idnotas;
    }

    public void setIdnotas(Long idnotas) {
        this.idnotas = idnotas;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Date getDatarelease() {
        return datarelease;
    }

    public void setDatarelease(Date datarelease) {
        this.datarelease = datarelease;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotas != null ? idnotas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefNotas)) {
            return false;
        }
        HefNotas other = (HefNotas) object;
        if ((this.idnotas == null && other.idnotas != null) || (this.idnotas != null && !this.idnotas.equals(other.idnotas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefNotas[ idnotas=" + idnotas + " ]";
    }
    
}
