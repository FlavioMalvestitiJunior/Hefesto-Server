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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_usuario_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefUsuarioInfo.findAll", query = "SELECT h FROM HefUsuarioInfo h"),
    @NamedQuery(name = "HefUsuarioInfo.findByIdusuarioInfo", query = "SELECT h FROM HefUsuarioInfo h WHERE h.idusuarioInfo = :idusuarioInfo"),
    @NamedQuery(name = "HefUsuarioInfo.findByDthcadastro", query = "SELECT h FROM HefUsuarioInfo h WHERE h.dthcadastro = :dthcadastro"),
    @NamedQuery(name = "HefUsuarioInfo.findByNome", query = "SELECT h FROM HefUsuarioInfo h WHERE h.nome = :nome"),
    @NamedQuery(name = "HefUsuarioInfo.findBySenha", query = "SELECT h FROM HefUsuarioInfo h WHERE h.senha = :senha"),
    @NamedQuery(name = "HefUsuarioInfo.findByEmail", query = "SELECT h FROM HefUsuarioInfo h WHERE h.email = :email"),
    @NamedQuery(name = "HefUsuarioInfo.findByTelefone", query = "SELECT h FROM HefUsuarioInfo h WHERE h.telefone = :telefone"),
    @NamedQuery(name = "HefUsuarioInfo.findByIdusuarioSistema", query = "SELECT h FROM HefUsuarioInfo h WHERE h.idusuarioSistema = :idusuarioSistema"),
    @NamedQuery(name = "HefUsuarioInfo.findByIdativo", query = "SELECT h FROM HefUsuarioInfo h WHERE h.idativo = :idativo"),
    @NamedQuery(name = "HefUsuarioInfo.findByIdusuarioCancelado", query = "SELECT h FROM HefUsuarioInfo h WHERE h.idusuarioCancelado = :idusuarioCancelado")})
public class HefUsuarioInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario_info")
    private Long idusuarioInfo;
    @Column(name = "dthcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthcadastro;
    @Size(max = 250)
    @Column(name = "nome")
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "senha")
    private String senha;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private BigInteger telefone;
    @Column(name = "idusuario_sistema")
    private Boolean idusuarioSistema;
    @Column(name = "idativo")
    private Boolean idativo;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "preferencias")
    private byte[] preferencias;
    @Column(name = "idusuario_cancelado")
    private Boolean idusuarioCancelado;
    @JoinColumn(name = "idfilial", referencedColumnName = "idfilial")
    @ManyToOne
    private HefFilial idfilial;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne
    private HefPerfil idperfil;
    @JoinColumn(name = "idusuario_cadastro", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuarioCadastro;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;

    public HefUsuarioInfo() {
    }

    public HefUsuarioInfo(Long idusuarioInfo) {
        this.idusuarioInfo = idusuarioInfo;
    }

    public Long getIdusuarioInfo() {
        return idusuarioInfo;
    }

    public void setIdusuarioInfo(Long idusuarioInfo) {
        this.idusuarioInfo = idusuarioInfo;
    }

    public Date getDthcadastro() {
        return dthcadastro;
    }

    public void setDthcadastro(Date dthcadastro) {
        this.dthcadastro = dthcadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Boolean getIdusuarioSistema() {
        return idusuarioSistema;
    }

    public void setIdusuarioSistema(Boolean idusuarioSistema) {
        this.idusuarioSistema = idusuarioSistema;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }


    public Boolean getIdusuarioCancelado() {
        return idusuarioCancelado;
    }

    public void setIdusuarioCancelado(Boolean idusuarioCancelado) {
        this.idusuarioCancelado = idusuarioCancelado;
    }

    public HefFilial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(HefFilial idfilial) {
        this.idfilial = idfilial;
    }

    public HefPerfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(HefPerfil idperfil) {
        this.idperfil = idperfil;
    }

    public HefUsuario getIdusuarioCadastro() {
        return idusuarioCadastro;
    }

    public void setIdusuarioCadastro(HefUsuario idusuarioCadastro) {
        this.idusuarioCadastro = idusuarioCadastro;
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
        hash += (idusuarioInfo != null ? idusuarioInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefUsuarioInfo)) {
            return false;
        }
        HefUsuarioInfo other = (HefUsuarioInfo) object;
        if ((this.idusuarioInfo == null && other.idusuarioInfo != null) || (this.idusuarioInfo != null && !this.idusuarioInfo.equals(other.idusuarioInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefUsuarioInfo[ idusuarioInfo=" + idusuarioInfo + " ]";
    }

    public byte[] getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(byte[] preferencias) {
        this.preferencias = preferencias;
    }

}
