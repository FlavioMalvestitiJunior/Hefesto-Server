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
@Table(name = "hef_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefMenu.findAll", query = "SELECT h FROM HefMenu h"),
    @NamedQuery(name = "HefMenu.findByIdmenu", query = "SELECT h FROM HefMenu h WHERE h.idmenu = :idmenu"),
    @NamedQuery(name = "HefMenu.findByNome", query = "SELECT h FROM HefMenu h WHERE h.nome = :nome"),
    @NamedQuery(name = "HefMenu.findByCodigo", query = "SELECT h FROM HefMenu h WHERE h.codigo = :codigo")})
public class HefMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmenu")
    private Long idmenu;
    @Size(max = 250)
    @Column(name = "nome")
    private String nome;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "idmenu")
    private Collection<HefTelas> hefTelasCollection;
    @OneToMany(mappedBy = "idmenuPai")
    private Collection<HefMenu> hefMenuCollection;
    @JoinColumn(name = "idmenu_pai", referencedColumnName = "idmenu")
    @ManyToOne
    private HefMenu idmenuPai;

    public HefMenu() {
    }

    public HefMenu(Long idmenu) {
        this.idmenu = idmenu;
    }

    public Long getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Long idmenu) {
        this.idmenu = idmenu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public Collection<HefTelas> getHefTelasCollection() {
        return hefTelasCollection;
    }

    public void setHefTelasCollection(Collection<HefTelas> hefTelasCollection) {
        this.hefTelasCollection = hefTelasCollection;
    }

    @XmlTransient
    public Collection<HefMenu> getHefMenuCollection() {
        return hefMenuCollection;
    }

    public void setHefMenuCollection(Collection<HefMenu> hefMenuCollection) {
        this.hefMenuCollection = hefMenuCollection;
    }

    public HefMenu getIdmenuPai() {
        return idmenuPai;
    }

    public void setIdmenuPai(HefMenu idmenuPai) {
        this.idmenuPai = idmenuPai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefMenu)) {
            return false;
        }
        HefMenu other = (HefMenu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefMenu[ idmenu=" + idmenu + " ]";
    }
    
}
