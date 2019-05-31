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
@Table(name = "hef_empresa_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEmpresaInfo.findAll", query = "SELECT h FROM HefEmpresaInfo h"),
    @NamedQuery(name = "HefEmpresaInfo.findByIdempresaInfo", query = "SELECT h FROM HefEmpresaInfo h WHERE h.idempresaInfo = :idempresaInfo"),
    @NamedQuery(name = "HefEmpresaInfo.findByCodigo", query = "SELECT h FROM HefEmpresaInfo h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefEmpresaInfo.findByIdativo", query = "SELECT h FROM HefEmpresaInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefEmpresaInfo.findByDthcadastro", query = "SELECT h FROM HefEmpresaInfo h WHERE h.dthcadastro = :dthcadastro"),
    @NamedQuery(name = "HefEmpresaInfo.findByRua", query = "SELECT h FROM HefEmpresaInfo h WHERE h.rua = :rua"),
    @NamedQuery(name = "HefEmpresaInfo.findByNumero", query = "SELECT h FROM HefEmpresaInfo h WHERE h.numero = :numero"),
    @NamedQuery(name = "HefEmpresaInfo.findByBairro", query = "SELECT h FROM HefEmpresaInfo h WHERE h.bairro = :bairro"),
    @NamedQuery(name = "HefEmpresaInfo.findByComplemento", query = "SELECT h FROM HefEmpresaInfo h WHERE h.complemento = :complemento"),
    @NamedQuery(name = "HefEmpresaInfo.findByEmail", query = "SELECT h FROM HefEmpresaInfo h WHERE h.email = :email"),
    @NamedQuery(name = "HefEmpresaInfo.findByTelefone", query = "SELECT h FROM HefEmpresaInfo h WHERE h.telefone = :telefone")})
public class HefEmpresaInfo implements Serializable {
    @Column(name = "idprodutora")
    private Boolean idprodutora;
    @Column(name = "cnpj")
    private BigInteger cnpj;
    @Column(name = "idcancelada")
    private Boolean idcancelada;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresa_info")
    private Long idempresaInfo;
    @Size(max = 700)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "idativo")
    private Boolean idativo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @Size(max = 700)
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private BigInteger numero;
    @Size(max = 700)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 2147483647)
    @Column(name = "complemento")
    private String complemento;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 700)
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private BigInteger telefone;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private HefEmpresa idempresa;
    @JoinColumn(name = "idlogradouro", referencedColumnName = "idlogradouro")
    @ManyToOne
    private HefLogradouro idlogradouro;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefEmpresaInfo() {
    }

    public HefEmpresaInfo(Long idempresaInfo) {
        this.idempresaInfo = idempresaInfo;
    }

    public Long getIdempresaInfo() {
        return idempresaInfo;
    }

    public void setIdempresaInfo(Long idempresaInfo) {
        this.idempresaInfo = idempresaInfo;
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

    public HefEmpresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(HefEmpresa idempresa) {
        this.idempresa = idempresa;
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
        hash += (idempresaInfo != null ? idempresaInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEmpresaInfo)) {
            return false;
        }
        HefEmpresaInfo other = (HefEmpresaInfo) object;
        if ((this.idempresaInfo == null && other.idempresaInfo != null) || (this.idempresaInfo != null && !this.idempresaInfo.equals(other.idempresaInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEmpresaInfo[ idempresaInfo=" + idempresaInfo + " ]";
    }

    public BigInteger getCnpj() {
        return cnpj;
    }

    public void setCnpj(BigInteger cnpj) {
        this.cnpj = cnpj;
    }

    public Boolean getIdcancelada() {
        return idcancelada;
    }

    public void setIdcancelada(Boolean idcancelada) {
        this.idcancelada = idcancelada;
    }

    public Boolean getIdprodutora() {
        return idprodutora;
    }

    public void setIdprodutora(Boolean idprodutora) {
        this.idprodutora = idprodutora;
    }
    
}
