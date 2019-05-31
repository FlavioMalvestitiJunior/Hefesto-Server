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
@Table(name = "hef_cliente_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefClienteInfo.findAll", query = "SELECT h FROM HefClienteInfo h"),
    @NamedQuery(name = "HefClienteInfo.findByIdclienteInfo", query = "SELECT h FROM HefClienteInfo h WHERE h.idclienteInfo = :idclienteInfo"),
    @NamedQuery(name = "HefClienteInfo.findByNome", query = "SELECT h FROM HefClienteInfo h WHERE h.nome = :nome"),
    @NamedQuery(name = "HefClienteInfo.findByNomeFantasia", query = "SELECT h FROM HefClienteInfo h WHERE h.nomeFantasia = :nomeFantasia"),
    @NamedQuery(name = "HefClienteInfo.findByIdpessoafisica", query = "SELECT h FROM HefClienteInfo h WHERE h.idpessoafisica = :idpessoafisica"),
    @NamedQuery(name = "HefClienteInfo.findByNurg", query = "SELECT h FROM HefClienteInfo h WHERE h.nurg = :nurg"),
    @NamedQuery(name = "HefClienteInfo.findByIdativo", query = "SELECT h FROM HefClienteInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefClienteInfo.findByRua", query = "SELECT h FROM HefClienteInfo h WHERE h.rua = :rua"),
    @NamedQuery(name = "HefClienteInfo.findByBairro", query = "SELECT h FROM HefClienteInfo h WHERE h.bairro = :bairro"),
    @NamedQuery(name = "HefClienteInfo.findByNumero", query = "SELECT h FROM HefClienteInfo h WHERE h.numero = :numero"),
    @NamedQuery(name = "HefClienteInfo.findByEmail", query = "SELECT h FROM HefClienteInfo h WHERE h.email = :email"),
    @NamedQuery(name = "HefClienteInfo.findByTelefone", query = "SELECT h FROM HefClienteInfo h WHERE h.telefone = :telefone"),
    @NamedQuery(name = "HefClienteInfo.findByCelular", query = "SELECT h FROM HefClienteInfo h WHERE h.celular = :celular"),
    @NamedQuery(name = "HefClienteInfo.findBySite", query = "SELECT h FROM HefClienteInfo h WHERE h.site = :site"),
    @NamedQuery(name = "HefClienteInfo.findByDthcadastro", query = "SELECT h FROM HefClienteInfo h WHERE h.dthcadastro = :dthcadastro")})
public class HefClienteInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente_info")
    private Long idclienteInfo;
    @Size(max = 700)
    @Column(name = "nome")
    private String nome;
    @Size(max = 700)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Column(name = "idpessoafisica")
    private Boolean idpessoafisica;
    @Size(max = 30)
    @Column(name = "nurg")
    private String nurg;
    @Column(name = "idativo")
    private Boolean idativo;
    @Size(max = 700)
    @Column(name = "rua")
    private String rua;
    @Size(max = 700)
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "numero")
    private BigInteger numero;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 700)
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private BigInteger telefone;
    @Column(name = "celular")
    private BigInteger celular;
    @Size(max = 800)
    @Column(name = "site")
    private String site;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private HefCliente idcliente;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private HefEmpresa idempresa;
    @JoinColumn(name = "idlogradouro", referencedColumnName = "idlogradouro")
    @ManyToOne
    private HefLogradouro idlogradouro;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefClienteInfo() {
    }

    public HefClienteInfo(Long idclienteInfo) {
        this.idclienteInfo = idclienteInfo;
    }

    public Long getIdclienteInfo() {
        return idclienteInfo;
    }

    public void setIdclienteInfo(Long idclienteInfo) {
        this.idclienteInfo = idclienteInfo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Boolean getIdpessoafisica() {
        return idpessoafisica;
    }

    public void setIdpessoafisica(Boolean idpessoafisica) {
        this.idpessoafisica = idpessoafisica;
    }

    public String getNurg() {
        return nurg;
    }

    public void setNurg(String nurg) {
        this.nurg = nurg;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
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

    public BigInteger getCelular() {
        return celular;
    }

    public void setCelular(BigInteger celular) {
        this.celular = celular;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public HefCliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(HefCliente idcliente) {
        this.idcliente = idcliente;
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
        hash += (idclienteInfo != null ? idclienteInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefClienteInfo)) {
            return false;
        }
        HefClienteInfo other = (HefClienteInfo) object;
        if ((this.idclienteInfo == null && other.idclienteInfo != null) || (this.idclienteInfo != null && !this.idclienteInfo.equals(other.idclienteInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefClienteInfo[ idclienteInfo=" + idclienteInfo + " ]";
    }
    
}
