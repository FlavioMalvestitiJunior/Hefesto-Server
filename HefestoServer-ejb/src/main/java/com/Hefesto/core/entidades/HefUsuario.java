/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "hef_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefUsuario.findAll", query = "SELECT h FROM HefUsuario h"),
    @NamedQuery(name = "HefUsuario.findByIdusuario", query = "SELECT h FROM HefUsuario h WHERE h.idusuario = :idusuario"),
    @NamedQuery(name = "HefUsuario.findByLogin", query = "SELECT h FROM HefUsuario h WHERE h.login = :login")})
public class HefUsuario implements Serializable {
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefEtapaInfo> hefEtapaInfoCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefPedidoInfo> hefPedidoInfoCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefFornecedorInfo> hefFornecedorInfoCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefProdutoInfo> hefProdutoInfoCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefClienteInfo> hefClienteInfoCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefEstoqueMovs> hefEstoqueMovsCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection;
    @OneToMany(mappedBy = "idusuarios")
    private Collection<HefLibFuncionarioUsuarios> hefLibFuncionarioUsuariosCollection;
    @OneToMany(mappedBy = "idusuariocadastro")
    private Collection<HefLibFuncionarioUsuarios> hefLibFuncionarioUsuariosCollection1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Long idusuario;
    @Size(max = 250)
    @Column(name = "login")
    private String login;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefRelatorio> hefRelatorioCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefPerfil> hefPerfilCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefLog> hefLogCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefPerfilEmpresa> hefPerfilEmpresaCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefEmpresaInfo> hefEmpresaInfoCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefLiberacaoTela> hefLiberacaoTelaCollection;
    @OneToMany(mappedBy = "idusuarioCadastro", fetch = FetchType.LAZY)
    private Collection<HefUsuarioInfo> hefUsuarioInfoCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefUsuarioInfo> hefUsuarioInfoCollection1;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefEmail> hefEmailCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefFilialInfo> hefFilialInfoCollection;
    @OneToMany(mappedBy = "idusuarioContato", fetch = FetchType.LAZY)
    private Collection<HefContatoEmail> hefContatoEmailCollection;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.LAZY)
    private Collection<HefContatoEmail> hefContatoEmailCollection1;

    public HefUsuario() {
    }

    public HefUsuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlTransient
    public Collection<HefRelatorio> getHefRelatorioCollection() {
        return hefRelatorioCollection;
    }

    public void setHefRelatorioCollection(Collection<HefRelatorio> hefRelatorioCollection) {
        this.hefRelatorioCollection = hefRelatorioCollection;
    }

    @XmlTransient
    public Collection<HefPerfil> getHefPerfilCollection() {
        return hefPerfilCollection;
    }

    public void setHefPerfilCollection(Collection<HefPerfil> hefPerfilCollection) {
        this.hefPerfilCollection = hefPerfilCollection;
    }

    @XmlTransient
    public Collection<HefLog> getHefLogCollection() {
        return hefLogCollection;
    }

    public void setHefLogCollection(Collection<HefLog> hefLogCollection) {
        this.hefLogCollection = hefLogCollection;
    }

    @XmlTransient
    public Collection<HefPerfilEmpresa> getHefPerfilEmpresaCollection() {
        return hefPerfilEmpresaCollection;
    }

    public void setHefPerfilEmpresaCollection(Collection<HefPerfilEmpresa> hefPerfilEmpresaCollection) {
        this.hefPerfilEmpresaCollection = hefPerfilEmpresaCollection;
    }

    @XmlTransient
    public Collection<HefEmpresaInfo> getHefEmpresaInfoCollection() {
        return hefEmpresaInfoCollection;
    }

    public void setHefEmpresaInfoCollection(Collection<HefEmpresaInfo> hefEmpresaInfoCollection) {
        this.hefEmpresaInfoCollection = hefEmpresaInfoCollection;
    }

    @XmlTransient
    public Collection<HefLiberacaoTela> getHefLiberacaoTelaCollection() {
        return hefLiberacaoTelaCollection;
    }

    public void setHefLiberacaoTelaCollection(Collection<HefLiberacaoTela> hefLiberacaoTelaCollection) {
        this.hefLiberacaoTelaCollection = hefLiberacaoTelaCollection;
    }

    @XmlTransient
    public Collection<HefUsuarioInfo> getHefUsuarioInfoCollection() {
        return hefUsuarioInfoCollection;
    }

    public void setHefUsuarioInfoCollection(Collection<HefUsuarioInfo> hefUsuarioInfoCollection) {
        this.hefUsuarioInfoCollection = hefUsuarioInfoCollection;
    }

    @XmlTransient
    public Collection<HefUsuarioInfo> getHefUsuarioInfoCollection1() {
        return hefUsuarioInfoCollection1;
    }

    public void setHefUsuarioInfoCollection1(Collection<HefUsuarioInfo> hefUsuarioInfoCollection1) {
        this.hefUsuarioInfoCollection1 = hefUsuarioInfoCollection1;
    }

    @XmlTransient
    public Collection<HefEmail> getHefEmailCollection() {
        return hefEmailCollection;
    }

    public void setHefEmailCollection(Collection<HefEmail> hefEmailCollection) {
        this.hefEmailCollection = hefEmailCollection;
    }

    @XmlTransient
    public Collection<HefFilialInfo> getHefFilialInfoCollection() {
        return hefFilialInfoCollection;
    }

    public void setHefFilialInfoCollection(Collection<HefFilialInfo> hefFilialInfoCollection) {
        this.hefFilialInfoCollection = hefFilialInfoCollection;
    }

    @XmlTransient
    public Collection<HefContatoEmail> getHefContatoEmailCollection() {
        return hefContatoEmailCollection;
    }

    public void setHefContatoEmailCollection(Collection<HefContatoEmail> hefContatoEmailCollection) {
        this.hefContatoEmailCollection = hefContatoEmailCollection;
    }

    @XmlTransient
    public Collection<HefContatoEmail> getHefContatoEmailCollection1() {
        return hefContatoEmailCollection1;
    }

    public void setHefContatoEmailCollection1(Collection<HefContatoEmail> hefContatoEmailCollection1) {
        this.hefContatoEmailCollection1 = hefContatoEmailCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefUsuario)) {
            return false;
        }
        HefUsuario other = (HefUsuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefUsuario[ idusuario=" + idusuario + " ]";
    }

    @XmlTransient
    public Collection<HefFuncionarioInfo> getHefFuncionarioInfoCollection() {
        return hefFuncionarioInfoCollection;
    }

    public void setHefFuncionarioInfoCollection(Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection) {
        this.hefFuncionarioInfoCollection = hefFuncionarioInfoCollection;
    }

    @XmlTransient
    public Collection<HefEtapaInfo> getHefEtapaInfoCollection() {
        return hefEtapaInfoCollection;
    }

    public void setHefEtapaInfoCollection(Collection<HefEtapaInfo> hefEtapaInfoCollection) {
        this.hefEtapaInfoCollection = hefEtapaInfoCollection;
    }

    @XmlTransient
    public Collection<HefPedidoInfo> getHefPedidoInfoCollection() {
        return hefPedidoInfoCollection;
    }

    public void setHefPedidoInfoCollection(Collection<HefPedidoInfo> hefPedidoInfoCollection) {
        this.hefPedidoInfoCollection = hefPedidoInfoCollection;
    }

    @XmlTransient
    public Collection<HefFornecedorInfo> getHefFornecedorInfoCollection() {
        return hefFornecedorInfoCollection;
    }

    public void setHefFornecedorInfoCollection(Collection<HefFornecedorInfo> hefFornecedorInfoCollection) {
        this.hefFornecedorInfoCollection = hefFornecedorInfoCollection;
    }

    @XmlTransient
    public Collection<HefProdutoInfo> getHefProdutoInfoCollection() {
        return hefProdutoInfoCollection;
    }

    public void setHefProdutoInfoCollection(Collection<HefProdutoInfo> hefProdutoInfoCollection) {
        this.hefProdutoInfoCollection = hefProdutoInfoCollection;
    }

    @XmlTransient
    public Collection<HefClienteInfo> getHefClienteInfoCollection() {
        return hefClienteInfoCollection;
    }

    public void setHefClienteInfoCollection(Collection<HefClienteInfo> hefClienteInfoCollection) {
        this.hefClienteInfoCollection = hefClienteInfoCollection;
    }

    @XmlTransient
    public Collection<HefEstoqueMovs> getHefEstoqueMovsCollection() {
        return hefEstoqueMovsCollection;
    }

    public void setHefEstoqueMovsCollection(Collection<HefEstoqueMovs> hefEstoqueMovsCollection) {
        this.hefEstoqueMovsCollection = hefEstoqueMovsCollection;
    }

    @XmlTransient
    public Collection<HefItemPedidoInfo> getHefItemPedidoInfoCollection() {
        return hefItemPedidoInfoCollection;
    }

    public void setHefItemPedidoInfoCollection(Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection) {
        this.hefItemPedidoInfoCollection = hefItemPedidoInfoCollection;
    }

    @XmlTransient
    public Collection<HefLibFuncionarioUsuarios> getHefLibFuncionarioUsuariosCollection() {
        return hefLibFuncionarioUsuariosCollection;
    }

    public void setHefLibFuncionarioUsuariosCollection(Collection<HefLibFuncionarioUsuarios> hefLibFuncionarioUsuariosCollection) {
        this.hefLibFuncionarioUsuariosCollection = hefLibFuncionarioUsuariosCollection;
    }

    @XmlTransient
    public Collection<HefLibFuncionarioUsuarios> getHefLibFuncionarioUsuariosCollection1() {
        return hefLibFuncionarioUsuariosCollection1;
    }

    public void setHefLibFuncionarioUsuariosCollection1(Collection<HefLibFuncionarioUsuarios> hefLibFuncionarioUsuariosCollection1) {
        this.hefLibFuncionarioUsuariosCollection1 = hefLibFuncionarioUsuariosCollection1;
    }

}
