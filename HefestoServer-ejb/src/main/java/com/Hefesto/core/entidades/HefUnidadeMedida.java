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
@Table(name = "hef_unidade_medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefUnidadeMedida.findAll", query = "SELECT h FROM HefUnidadeMedida h"),
    @NamedQuery(name = "HefUnidadeMedida.findByIdunidade", query = "SELECT h FROM HefUnidadeMedida h WHERE h.idunidade = :idunidade"),
    @NamedQuery(name = "HefUnidadeMedida.findByCodigo", query = "SELECT h FROM HefUnidadeMedida h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefUnidadeMedida.findByDescricao", query = "SELECT h FROM HefUnidadeMedida h WHERE h.descricao = :descricao")})
public class HefUnidadeMedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idunidade")
    private Long idunidade;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idunidade")
    private Collection<HefProdutoInfo> hefProdutoInfoCollection;

    public HefUnidadeMedida() {
    }

    public HefUnidadeMedida(Long idunidade) {
        this.idunidade = idunidade;
    }

    public Long getIdunidade() {
        return idunidade;
    }

    public void setIdunidade(Long idunidade) {
        this.idunidade = idunidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<HefProdutoInfo> getHefProdutoInfoCollection() {
        return hefProdutoInfoCollection;
    }

    public void setHefProdutoInfoCollection(Collection<HefProdutoInfo> hefProdutoInfoCollection) {
        this.hefProdutoInfoCollection = hefProdutoInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idunidade != null ? idunidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefUnidadeMedida)) {
            return false;
        }
        HefUnidadeMedida other = (HefUnidadeMedida) object;
        if ((this.idunidade == null && other.idunidade != null) || (this.idunidade != null && !this.idunidade.equals(other.idunidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefUnidadeMedida[ idunidade=" + idunidade + " ]";
    }
    
}
