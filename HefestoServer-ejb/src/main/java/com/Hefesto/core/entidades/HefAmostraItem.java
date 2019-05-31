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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Type;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_amostra_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefAmostraItem.findAll", query = "SELECT h FROM HefAmostraItem h"),
    @NamedQuery(name = "HefAmostraItem.findByIdamostra", query = "SELECT h FROM HefAmostraItem h WHERE h.idamostra = :idamostra")})
public class HefAmostraItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idamostra")
    private Long idamostra;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "amostra")
    private byte[] amostra;
    @OneToMany(mappedBy = "idamostra")
    private Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection;

    public HefAmostraItem() {
    }

    public HefAmostraItem(Long idamostra) {
        this.idamostra = idamostra;
    }

    public Long getIdamostra() {
        return idamostra;
    }

    public void setIdamostra(Long idamostra) {
        this.idamostra = idamostra;
    }

    public byte[] getAmostra() {
        return amostra;
    }

    public void setAmostra(byte[] amostra) {
        this.amostra = amostra;
    }

    @XmlTransient
    public Collection<HefItemPedidoInfo> getHefItemPedidoInfoCollection() {
        return hefItemPedidoInfoCollection;
    }

    public void setHefItemPedidoInfoCollection(Collection<HefItemPedidoInfo> hefItemPedidoInfoCollection) {
        this.hefItemPedidoInfoCollection = hefItemPedidoInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idamostra != null ? idamostra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefAmostraItem)) {
            return false;
        }
        HefAmostraItem other = (HefAmostraItem) object;
        if ((this.idamostra == null && other.idamostra != null) || (this.idamostra != null && !this.idamostra.equals(other.idamostra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefAmostraItem[ idamostra=" + idamostra + " ]";
    }

}
