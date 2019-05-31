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
@Table(name = "hef_lib_funcionario_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefLibFuncionarioUsuarios.findAll", query = "SELECT h FROM HefLibFuncionarioUsuarios h"),
    @NamedQuery(name = "HefLibFuncionarioUsuarios.findByIdfuncionarioUsuario", query = "SELECT h FROM HefLibFuncionarioUsuarios h WHERE h.idfuncionarioUsuario = :idfuncionarioUsuario"),
    @NamedQuery(name = "HefLibFuncionarioUsuarios.findByDthcadastro", query = "SELECT h FROM HefLibFuncionarioUsuarios h WHERE h.dthcadastro = :dthcadastro"),
    @NamedQuery(name = "HefLibFuncionarioUsuarios.findByIdativo", query = "SELECT h FROM HefLibFuncionarioUsuarios h WHERE h.idativo = :idativo")})
public class HefLibFuncionarioUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionario_usuario")
    private Long idfuncionarioUsuario;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIME)
    private Date dthcadastro;
    @Column(name = "idativo")
    private Boolean idativo;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne
    private HefFuncionario idfuncionario;
    @JoinColumn(name = "idusuarios", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuarios;
    @JoinColumn(name = "idusuariocadastro", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuariocadastro;

    public HefLibFuncionarioUsuarios() {
    }

    public HefLibFuncionarioUsuarios(Long idfuncionarioUsuario) {
        this.idfuncionarioUsuario = idfuncionarioUsuario;
    }

    public Long getIdfuncionarioUsuario() {
        return idfuncionarioUsuario;
    }

    public void setIdfuncionarioUsuario(Long idfuncionarioUsuario) {
        this.idfuncionarioUsuario = idfuncionarioUsuario;
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

    public HefFuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(HefFuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public HefUsuario getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(HefUsuario idusuarios) {
        this.idusuarios = idusuarios;
    }

    public HefUsuario getIdusuariocadastro() {
        return idusuariocadastro;
    }

    public void setIdusuariocadastro(HefUsuario idusuariocadastro) {
        this.idusuariocadastro = idusuariocadastro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncionarioUsuario != null ? idfuncionarioUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefLibFuncionarioUsuarios)) {
            return false;
        }
        HefLibFuncionarioUsuarios other = (HefLibFuncionarioUsuarios) object;
        if ((this.idfuncionarioUsuario == null && other.idfuncionarioUsuario != null) || (this.idfuncionarioUsuario != null && !this.idfuncionarioUsuario.equals(other.idfuncionarioUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefLibFuncionarioUsuarios[ idfuncionarioUsuario=" + idfuncionarioUsuario + " ]";
    }
    
}
