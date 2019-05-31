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
@Table(name = "hef_liberacao_tela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefLiberacaoTela.findAll", query = "SELECT h FROM HefLiberacaoTela h"),
    @NamedQuery(name = "HefLiberacaoTela.findByIdliberacaoTela", query = "SELECT h FROM HefLiberacaoTela h WHERE h.idliberacaoTela = :idliberacaoTela"),
    @NamedQuery(name = "HefLiberacaoTela.findByWs", query = "SELECT h FROM HefLiberacaoTela h WHERE h.ws = :ws"),
    @NamedQuery(name = "HefLiberacaoTela.findByR", query = "SELECT h FROM HefLiberacaoTela h WHERE h.r = :r"),
    @NamedQuery(name = "HefLiberacaoTela.findByX", query = "SELECT h FROM HefLiberacaoTela h WHERE h.x = :x"),
    @NamedQuery(name = "HefLiberacaoTela.findByIdativo", query = "SELECT h FROM HefLiberacaoTela h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefLiberacaoTela.findByDthcadastro", query = "SELECT h FROM HefLiberacaoTela h WHERE h.dthcadastro = :dthcadastro"),
    @NamedQuery(name = "HefLiberacaoTela.findByWr", query = "SELECT h FROM HefLiberacaoTela h WHERE h.wr = :wr")})
public class HefLiberacaoTela implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idliberacao_tela")
    private Long idliberacaoTela;
    @Column(name = "ws")
    private Boolean ws;
    @Column(name = "r")
    private Boolean r;
    @Column(name = "x")
    private Boolean x;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @Column(name = "wr")
    private Boolean wr;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne
    private HefPerfil idperfil;
    @JoinColumn(name = "idtela", referencedColumnName = "idtela")
    @ManyToOne
    private HefTelas idtela;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefLiberacaoTela() {
    }

    public HefLiberacaoTela(Long idliberacaoTela) {
        this.idliberacaoTela = idliberacaoTela;
    }

    public Long getIdliberacaoTela() {
        return idliberacaoTela;
    }

    public void setIdliberacaoTela(Long idliberacaoTela) {
        this.idliberacaoTela = idliberacaoTela;
    }

    public Boolean getWs() {
        return ws;
    }

    public void setWs(Boolean ws) {
        this.ws = ws;
    }

    public Boolean getR() {
        return r;
    }

    public void setR(Boolean r) {
        this.r = r;
    }

    public Boolean getX() {
        return x;
    }

    public void setX(Boolean x) {
        this.x = x;
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

    public Boolean getWr() {
        return wr;
    }

    public void setWr(Boolean wr) {
        this.wr = wr;
    }

    public HefPerfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(HefPerfil idperfil) {
        this.idperfil = idperfil;
    }

    public HefTelas getIdtela() {
        return idtela;
    }

    public void setIdtela(HefTelas idtela) {
        this.idtela = idtela;
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
        hash += (idliberacaoTela != null ? idliberacaoTela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefLiberacaoTela)) {
            return false;
        }
        HefLiberacaoTela other = (HefLiberacaoTela) object;
        if ((this.idliberacaoTela == null && other.idliberacaoTela != null) || (this.idliberacaoTela != null && !this.idliberacaoTela.equals(other.idliberacaoTela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefLiberacaoTela[ idliberacaoTela=" + idliberacaoTela + " ]";
    }
    
}
