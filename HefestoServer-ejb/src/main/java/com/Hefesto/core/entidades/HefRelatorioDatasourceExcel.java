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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_relatorio_datasource_excel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefRelatorioDatasourceExcel.findAll", query = "SELECT h FROM HefRelatorioDatasourceExcel h"),
    @NamedQuery(name = "HefRelatorioDatasourceExcel.findByIddataSource", query = "SELECT h FROM HefRelatorioDatasourceExcel h WHERE h.iddataSource = :iddataSource")})
public class HefRelatorioDatasourceExcel implements Serializable {

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "datasource")
    private byte[] datasource;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddata_source")
    private Long iddataSource;
    @JoinColumn(name = "idrelatorio", referencedColumnName = "idrelatorio")
    @OneToOne
    private HefRelatorio idrelatorio;

    public HefRelatorioDatasourceExcel() {
    }

    public HefRelatorioDatasourceExcel(Long iddataSource) {
        this.iddataSource = iddataSource;
    }

    public Long getIddataSource() {
        return iddataSource;
    }

    public void setIddataSource(Long iddataSource) {
        this.iddataSource = iddataSource;
    }

    public HefRelatorio getIdrelatorio() {
        return idrelatorio;
    }

    public void setIdrelatorio(HefRelatorio idrelatorio) {
        this.idrelatorio = idrelatorio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddataSource != null ? iddataSource.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefRelatorioDatasourceExcel)) {
            return false;
        }
        HefRelatorioDatasourceExcel other = (HefRelatorioDatasourceExcel) object;
        if ((this.iddataSource == null && other.iddataSource != null) || (this.iddataSource != null && !this.iddataSource.equals(other.iddataSource))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefRelatorioDatasourceExcel[ iddataSource=" + iddataSource + " ]";
    }

    public byte[] getDatasource() {
        return datasource;
    }

    public void setDatasource(byte[] datasource) {
        this.datasource = datasource;
    }

}
