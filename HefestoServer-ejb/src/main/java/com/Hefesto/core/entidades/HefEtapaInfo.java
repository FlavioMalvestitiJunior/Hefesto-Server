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
@Table(name = "hef_etapa_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEtapaInfo.findAll", query = "SELECT h FROM HefEtapaInfo h"),
    @NamedQuery(name = "HefEtapaInfo.findByIdetapaInfo", query = "SELECT h FROM HefEtapaInfo h WHERE h.idetapaInfo = :idetapaInfo"),
    @NamedQuery(name = "HefEtapaInfo.findByDescricao", query = "SELECT h FROM HefEtapaInfo h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefEtapaInfo.findByIdativo", query = "SELECT h FROM HefEtapaInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefEtapaInfo.findByDthcadastro", query = "SELECT h FROM HefEtapaInfo h WHERE h.dthcadastro = :dthcadastro")})
public class HefEtapaInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idetapa_info")
    private Long idetapaInfo;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idetapa", referencedColumnName = "idetapa")
    @ManyToOne
    private HefEtapa idetapa;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefEtapaInfo() {
    }

    public HefEtapaInfo(Long idetapaInfo) {
        this.idetapaInfo = idetapaInfo;
    }

    public Long getIdetapaInfo() {
        return idetapaInfo;
    }

    public void setIdetapaInfo(Long idetapaInfo) {
        this.idetapaInfo = idetapaInfo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefEtapa getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(HefEtapa idetapa) {
        this.idetapa = idetapa;
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
        hash += (idetapaInfo != null ? idetapaInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEtapaInfo)) {
            return false;
        }
        HefEtapaInfo other = (HefEtapaInfo) object;
        if ((this.idetapaInfo == null && other.idetapaInfo != null) || (this.idetapaInfo != null && !this.idetapaInfo.equals(other.idetapaInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEtapaInfo[ idetapaInfo=" + idetapaInfo + " ]";
    }
    
}
