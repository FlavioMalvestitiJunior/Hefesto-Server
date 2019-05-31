/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
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
@Table(name = "hef_filial_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefFilialInfo.findAll", query = "SELECT h FROM HefFilialInfo h"),
    @NamedQuery(name = "HefFilialInfo.findByIdfilialInfo", query = "SELECT h FROM HefFilialInfo h WHERE h.idfilialInfo = :idfilialInfo"),
    @NamedQuery(name = "HefFilialInfo.findByCodigo", query = "SELECT h FROM HefFilialInfo h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefFilialInfo.findByIdativo", query = "SELECT h FROM HefFilialInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefFilialInfo.findByDthcadastro", query = "SELECT h FROM HefFilialInfo h WHERE h.dthcadastro = :dthcadastro"),
    @NamedQuery(name = "HefFilialInfo.findByRua", query = "SELECT h FROM HefFilialInfo h WHERE h.rua = :rua"),
    @NamedQuery(name = "HefFilialInfo.findByNumero", query = "SELECT h FROM HefFilialInfo h WHERE h.numero = :numero"),
    @NamedQuery(name = "HefFilialInfo.findByBairro", query = "SELECT h FROM HefFilialInfo h WHERE h.bairro = :bairro"),
    @NamedQuery(name = "HefFilialInfo.findByComplemento", query = "SELECT h FROM HefFilialInfo h WHERE h.complemento = :complemento"),
    @NamedQuery(name = "HefFilialInfo.findByCnpj", query = "SELECT h FROM HefFilialInfo h WHERE h.cnpj = :cnpj"),
    @NamedQuery(name = "HefFilialInfo.findByEmail", query = "SELECT h FROM HefFilialInfo h WHERE h.email = :email"),
    @NamedQuery(name = "HefFilialInfo.findByTelefone", query = "SELECT h FROM HefFilialInfo h WHERE h.telefone = :telefone"),
    @NamedQuery(name = "HefFilialInfo.findByIdcancelada", query = "SELECT h FROM HefFilialInfo h WHERE h.idcancelada = :idcancelada")})
public class HefFilialInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfilial_info")
    private Long idfilialInfo;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @Size(max = 500)
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private BigInteger numero;
    @Size(max = 500)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 2147483647)
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "cnpj")
    private BigInteger cnpj;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private BigInteger telefone;
    @Column(name = "idcancelada")
    private Boolean idcancelada;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private HefEmpresa idempresa;
    @JoinColumn(name = "idfilial", referencedColumnName = "idfilial")
    @ManyToOne
    private HefFilial idfilial;
    @JoinColumn(name = "idlogradouro", referencedColumnName = "idlogradouro")
    @ManyToOne
    private HefLogradouro idlogradouro;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefFilialInfo() {
    }

    public HefFilialInfo(Long idfilialInfo) {
        this.idfilialInfo = idfilialInfo;
    }

    public Long getIdfilialInfo() {
        return idfilialInfo;
    }

    public void setIdfilialInfo(Long idfilialInfo) {
        this.idfilialInfo = idfilialInfo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public BigInteger getCnpj() {
        return cnpj;
    }

    public void setCnpj(BigInteger cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getTelefone() {
        return telefone;
    }

    public void setTelefone(BigInteger telefone) {
        this.telefone = telefone;
    }

    public Boolean getIdcancelada() {
        return idcancelada;
    }

    public void setIdcancelada(Boolean idcancelada) {
        this.idcancelada = idcancelada;
    }

    public HefEmpresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(HefEmpresa idempresa) {
        this.idempresa = idempresa;
    }

    public HefFilial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(HefFilial idfilial) {
        this.idfilial = idfilial;
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
        hash += (idfilialInfo != null ? idfilialInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefFilialInfo)) {
            return false;
        }
        HefFilialInfo other = (HefFilialInfo) object;
        if ((this.idfilialInfo == null && other.idfilialInfo != null) || (this.idfilialInfo != null && !this.idfilialInfo.equals(other.idfilialInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefFilialInfo[ idfilialInfo=" + idfilialInfo + " ]";
    }
    
}
