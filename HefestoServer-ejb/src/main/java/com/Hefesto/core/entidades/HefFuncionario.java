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
@Table(name = "hef_funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefFuncionario.findAll", query = "SELECT h FROM HefFuncionario h"),
    @NamedQuery(name = "HefFuncionario.findByIdfuncionario", query = "SELECT h FROM HefFuncionario h WHERE h.idfuncionario = :idfuncionario"),
    @NamedQuery(name = "HefFuncionario.findByCodigo", query = "SELECT h FROM HefFuncionario h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefFuncionario.findByNome", query = "SELECT h FROM HefFuncionario h WHERE h.nome = :nome"),
    @NamedQuery(name = "HefFuncionario.findByCpf", query = "SELECT h FROM HefFuncionario h WHERE h.cpf = :cpf"),
    @NamedQuery(name = "HefFuncionario.findByRg", query = "SELECT h FROM HefFuncionario h WHERE h.rg = :rg")})
public class HefFuncionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionario")
    private Long idfuncionario;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 700)
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private BigInteger cpf;
    @Size(max = 30)
    @Column(name = "rg")
    private String rg;
    @OneToMany(mappedBy = "idfuncionario")
    private Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection;
    @OneToMany(mappedBy = "idfuncionario")
    private Collection<HefItemErro> hefItemErroCollection;
    @OneToMany(mappedBy = "idfuncionario")
    private Collection<HefItensProduzidos> hefItensProduzidosCollection;
    @OneToMany(mappedBy = "idfuncionario")
    private Collection<HefLibFuncionarioUsuarios> hefLibFuncionarioUsuariosCollection;

    public HefFuncionario() {
    }

    public HefFuncionario(Long idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Long getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Long idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getCpf() {
        return cpf;
    }

    public void setCpf(BigInteger cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @XmlTransient
    public Collection<HefFuncionarioInfo> getHefFuncionarioInfoCollection() {
        return hefFuncionarioInfoCollection;
    }

    public void setHefFuncionarioInfoCollection(Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection) {
        this.hefFuncionarioInfoCollection = hefFuncionarioInfoCollection;
    }

    @XmlTransient
    public Collection<HefItemErro> getHefItemErroCollection() {
        return hefItemErroCollection;
    }

    public void setHefItemErroCollection(Collection<HefItemErro> hefItemErroCollection) {
        this.hefItemErroCollection = hefItemErroCollection;
    }

    @XmlTransient
    public Collection<HefItensProduzidos> getHefItensProduzidosCollection() {
        return hefItensProduzidosCollection;
    }

    public void setHefItensProduzidosCollection(Collection<HefItensProduzidos> hefItensProduzidosCollection) {
        this.hefItensProduzidosCollection = hefItensProduzidosCollection;
    }

    @XmlTransient
    public Collection<HefLibFuncionarioUsuarios> getHefLibFuncionarioUsuariosCollection() {
        return hefLibFuncionarioUsuariosCollection;
    }

    public void setHefLibFuncionarioUsuariosCollection(Collection<HefLibFuncionarioUsuarios> hefLibFuncionarioUsuariosCollection) {
        this.hefLibFuncionarioUsuariosCollection = hefLibFuncionarioUsuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncionario != null ? idfuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefFuncionario)) {
            return false;
        }
        HefFuncionario other = (HefFuncionario) object;
        if ((this.idfuncionario == null && other.idfuncionario != null) || (this.idfuncionario != null && !this.idfuncionario.equals(other.idfuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefFuncionario[ idfuncionario=" + idfuncionario + " ]";
    }
    
}
