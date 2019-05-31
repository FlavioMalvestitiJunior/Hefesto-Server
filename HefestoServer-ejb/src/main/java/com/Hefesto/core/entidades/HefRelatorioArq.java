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
@Table(name = "hef_relatorio_arq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefRelatorioArq.findAll", query = "SELECT h FROM HefRelatorioArq h"),
    @NamedQuery(name = "HefRelatorioArq.findByIdrelatorioArq", query = "SELECT h FROM HefRelatorioArq h WHERE h.idrelatorioArq = :idrelatorioArq")})
public class HefRelatorioArq implements Serializable {

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "arquivo")
    private byte[] arquivo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrelatorio_arq")
    private Long idrelatorioArq;
    @JoinColumn(name = "idrelatorio", referencedColumnName = "idrelatorio")
    @OneToOne
    private HefRelatorio idrelatorio;

    public HefRelatorioArq() {
    }

    public HefRelatorioArq(Long idrelatorioArq) {
        this.idrelatorioArq = idrelatorioArq;
    }

    public Long getIdrelatorioArq() {
        return idrelatorioArq;
    }

    public void setIdrelatorioArq(Long idrelatorioArq) {
        this.idrelatorioArq = idrelatorioArq;
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
        hash += (idrelatorioArq != null ? idrelatorioArq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefRelatorioArq)) {
            return false;
        }
        HefRelatorioArq other = (HefRelatorioArq) object;
        if ((this.idrelatorioArq == null && other.idrelatorioArq != null) || (this.idrelatorioArq != null && !this.idrelatorioArq.equals(other.idrelatorioArq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefRelatorioArq[ idrelatorioArq=" + idrelatorioArq + " ]";
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

}
