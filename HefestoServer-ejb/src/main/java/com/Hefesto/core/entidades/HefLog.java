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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefLog.findAll", query = "SELECT h FROM HefLog h"),
    @NamedQuery(name = "HefLog.findByIdlog", query = "SELECT h FROM HefLog h WHERE h.idlog = :idlog"),
    @NamedQuery(name = "HefLog.findByDthlog", query = "SELECT h FROM HefLog h WHERE h.dthlog = :dthlog"),
    @NamedQuery(name = "HefLog.findByNmentidade", query = "SELECT h FROM HefLog h WHERE h.nmentidade = :nmentidade"),
    @NamedQuery(name = "HefLog.findByNumip", query = "SELECT h FROM HefLog h WHERE h.numip = :numip"),
    @NamedQuery(name = "HefLog.findByDescricao", query = "SELECT h FROM HefLog h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefLog.findByTplog", query = "SELECT h FROM HefLog h WHERE h.tplog = :tplog")})
public class HefLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlog")
    private Long idlog;
    @Column(name = "dthlog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthlog;
    @Size(max = 2147483647)
    @Column(name = "nmentidade")
    private String nmentidade;
    @Size(max = 2147483647)
    @Column(name = "numip")
    private String numip;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 500)
    @Column(name = "tplog")
    private String tplog;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefLog() {
    }

    public HefLog(Long idlog) {
        this.idlog = idlog;
    }

    public Long getIdlog() {
        return idlog;
    }

    public void setIdlog(Long idlog) {
        this.idlog = idlog;
    }

    public Date getDthlog() {
        return dthlog;
    }

    public void setDthlog(Date dthlog) {
        this.dthlog = dthlog;
    }

    public String getNmentidade() {
        return nmentidade;
    }

    public void setNmentidade(String nmentidade) {
        this.nmentidade = nmentidade;
    }

    public String getNumip() {
        return numip;
    }

    public void setNumip(String numip) {
        this.numip = numip;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTplog() {
        return tplog;
    }

    public void setTplog(String tplog) {
        this.tplog = tplog;
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
        hash += (idlog != null ? idlog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefLog)) {
            return false;
        }
        HefLog other = (HefLog) object;
        if ((this.idlog == null && other.idlog != null) || (this.idlog != null && !this.idlog.equals(other.idlog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefLog[ idlog=" + idlog + " ]";
    }
    
}
