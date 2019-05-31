/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "hef_funcionario_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefFuncionarioInfo.findAll", query = "SELECT h FROM HefFuncionarioInfo h"),
    @NamedQuery(name = "HefFuncionarioInfo.findByIdfuncionarioInfo", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.idfuncionarioInfo = :idfuncionarioInfo"),
    @NamedQuery(name = "HefFuncionarioInfo.findBySalario", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.salario = :salario"),
    @NamedQuery(name = "HefFuncionarioInfo.findByTelefone", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.telefone = :telefone"),
    @NamedQuery(name = "HefFuncionarioInfo.findByEmail", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.email = :email"),
    @NamedQuery(name = "HefFuncionarioInfo.findByRua", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.rua = :rua"),
    @NamedQuery(name = "HefFuncionarioInfo.findByNumero", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.numero = :numero"),
    @NamedQuery(name = "HefFuncionarioInfo.findByBairro", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.bairro = :bairro"),
    @NamedQuery(name = "HefFuncionarioInfo.findByIdativo", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefFuncionarioInfo.findByComplemento", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.complemento = :complemento"),
    @NamedQuery(name = "HefFuncionarioInfo.findByObservacao", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.observacao = :observacao"),
    @NamedQuery(name = "HefFuncionarioInfo.findByDthcadastro", query = "SELECT h FROM HefFuncionarioInfo h WHERE h.dthcadastro = :dthcadastro")})
public class HefFuncionarioInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionario_info")
    private Long idfuncionarioInfo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private BigDecimal salario;
    @Column(name = "telefone")
    private BigInteger telefone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 700)
    @Column(name = "email")
    private String email;
    @Size(max = 700)
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private BigInteger numero;
    @Size(max = 700)
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "idativo")
    private Boolean idativo;
    @Size(max = 2147483647)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idcargo", referencedColumnName = "idcargo")
    @ManyToOne
    private HefCargo idcargo;
    @JoinColumn(name = "idfilial", referencedColumnName = "idfilial")
    @ManyToOne
    private HefFilial idfilial;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne
    private HefFuncionario idfuncionario;
    @JoinColumn(name = "idlogradouro", referencedColumnName = "idlogradouro")
    @ManyToOne
    private HefLogradouro idlogradouro;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefFuncionarioInfo() {
    }

    public HefFuncionarioInfo(Long idfuncionarioInfo) {
        this.idfuncionarioInfo = idfuncionarioInfo;
    }

    public Long getIdfuncionarioInfo() {
        return idfuncionarioInfo;
    }

    public void setIdfuncionarioInfo(Long idfuncionarioInfo) {
        this.idfuncionarioInfo = idfuncionarioInfo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigInteger getTelefone() {
        return telefone;
    }

    public void setTelefone(BigInteger telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefCargo getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(HefCargo idcargo) {
        this.idcargo = idcargo;
    }

    public HefFilial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(HefFilial idfilial) {
        this.idfilial = idfilial;
    }

    public HefFuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(HefFuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public HefLogradouro getIdlogradouro() {
        return idlogradouro;
    }

    public void setIdlogradouro(HefLogradouro idlogradouro) {
        this.idlogradouro = idlogradouro;
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
        hash += (idfuncionarioInfo != null ? idfuncionarioInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefFuncionarioInfo)) {
            return false;
        }
        HefFuncionarioInfo other = (HefFuncionarioInfo) object;
        if ((this.idfuncionarioInfo == null && other.idfuncionarioInfo != null) || (this.idfuncionarioInfo != null && !this.idfuncionarioInfo.equals(other.idfuncionarioInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefFuncionarioInfo[ idfuncionarioInfo=" + idfuncionarioInfo + " ]";
    }
    
}
