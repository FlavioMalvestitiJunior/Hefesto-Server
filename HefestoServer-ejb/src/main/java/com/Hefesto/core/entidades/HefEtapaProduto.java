/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_etapa_produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEtapaProduto.findAll", query = "SELECT h FROM HefEtapaProduto h"),
    @NamedQuery(name = "HefEtapaProduto.findByIdetapaProduto", query = "SELECT h FROM HefEtapaProduto h WHERE h.idetapaProduto = :idetapaProduto"),
    @NamedQuery(name = "HefEtapaProduto.findByIdetapaFinal", query = "SELECT h FROM HefEtapaProduto h WHERE h.idetapaFinal = :idetapaFinal")})
public class HefEtapaProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idetapa_produto")
    private Long idetapaProduto;
    @Column(name = "idetapa_final")
    private Boolean idetapaFinal;
    @JoinColumn(name = "idetapa", referencedColumnName = "idetapa")
    @ManyToOne
    private HefEtapa idetapa;
    @JoinColumn(name = "idproduto_info", referencedColumnName = "idproduto_info")
    @ManyToOne
    private HefProdutoInfo idprodutoInfo;

    public HefEtapaProduto() {
    }

    public HefEtapaProduto(Long idetapaProduto) {
        this.idetapaProduto = idetapaProduto;
    }

    public Long getIdetapaProduto() {
        return idetapaProduto;
    }

    public void setIdetapaProduto(Long idetapaProduto) {
        this.idetapaProduto = idetapaProduto;
    }

    public Boolean getIdetapaFinal() {
        return idetapaFinal;
    }

    public void setIdetapaFinal(Boolean idetapaFinal) {
        this.idetapaFinal = idetapaFinal;
    }

    public HefEtapa getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(HefEtapa idetapa) {
        this.idetapa = idetapa;
    }

    public HefProdutoInfo getIdprodutoInfo() {
        return idprodutoInfo;
    }

    public void setIdprodutoInfo(HefProdutoInfo idprodutoInfo) {
        this.idprodutoInfo = idprodutoInfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapaProduto != null ? idetapaProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEtapaProduto)) {
            return false;
        }
        HefEtapaProduto other = (HefEtapaProduto) object;
        if ((this.idetapaProduto == null && other.idetapaProduto != null) || (this.idetapaProduto != null && !this.idetapaProduto.equals(other.idetapaProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEtapaProduto[ idetapaProduto=" + idetapaProduto + " ]";
    }
    
}
