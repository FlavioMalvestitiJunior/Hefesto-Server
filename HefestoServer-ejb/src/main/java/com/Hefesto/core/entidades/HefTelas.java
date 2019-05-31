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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "hef_telas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefTelas.findAll", query = "SELECT h FROM HefTelas h"),
    @NamedQuery(name = "HefTelas.findByIdtela", query = "SELECT h FROM HefTelas h WHERE h.idtela = :idtela"),
    @NamedQuery(name = "HefTelas.findByCodigoTela", query = "SELECT h FROM HefTelas h WHERE h.codigoTela = :codigoTela"),
    @NamedQuery(name = "HefTelas.findByClass1", query = "SELECT h FROM HefTelas h WHERE h.class1 = :class1"),
    @NamedQuery(name = "HefTelas.findByNome", query = "SELECT h FROM HefTelas h WHERE h.nome = :nome"),
    @NamedQuery(name = "HefTelas.findByTitulo", query = "SELECT h FROM HefTelas h WHERE h.titulo = :titulo"),
    @NamedQuery(name = "HefTelas.findByIdativo", query = "SELECT h FROM HefTelas h WHERE h.idativo = :idativo")})
public class HefTelas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtela")
    private Long idtela;
    @Column(name = "codigo_tela")
    private BigInteger codigoTela;
    @Size(max = 2147483647)
    @Column(name = "class")
    private String class1;
    @Size(max = 500)
    @Column(name = "nome")
    private String nome;
    @Size(max = 500)
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "idativo")
    private Boolean idativo;
    @OneToMany(mappedBy = "idtelas")
    private Collection<HefRelatorio> hefRelatorioCollection;
    @OneToMany(mappedBy = "idtela")
    private Collection<HefLiberacaoTela> hefLiberacaoTelaCollection;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne
    private HefMenu idmenu;

    public HefTelas() {
    }

    public HefTelas(Long idtela) {
        this.idtela = idtela;
    }

    public Long getIdtela() {
        return idtela;
    }

    public void setIdtela(Long idtela) {
        this.idtela = idtela;
    }

    public BigInteger getCodigoTela() {
        return codigoTela;
    }

    public void setCodigoTela(BigInteger codigoTela) {
        this.codigoTela = codigoTela;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    @XmlTransient
    public Collection<HefRelatorio> getHefRelatorioCollection() {
        return hefRelatorioCollection;
    }

    public void setHefRelatorioCollection(Collection<HefRelatorio> hefRelatorioCollection) {
        this.hefRelatorioCollection = hefRelatorioCollection;
    }

    @XmlTransient
    public Collection<HefLiberacaoTela> getHefLiberacaoTelaCollection() {
        return hefLiberacaoTelaCollection;
    }

    public void setHefLiberacaoTelaCollection(Collection<HefLiberacaoTela> hefLiberacaoTelaCollection) {
        this.hefLiberacaoTelaCollection = hefLiberacaoTelaCollection;
    }

    public HefMenu getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(HefMenu idmenu) {
        this.idmenu = idmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtela != null ? idtela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefTelas)) {
            return false;
        }
        HefTelas other = (HefTelas) object;
        if ((this.idtela == null && other.idtela != null) || (this.idtela != null && !this.idtela.equals(other.idtela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefTelas[ idtela=" + idtela + " ]";
    }
    
}
