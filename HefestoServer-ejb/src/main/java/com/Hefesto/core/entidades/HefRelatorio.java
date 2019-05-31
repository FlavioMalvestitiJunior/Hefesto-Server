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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "hef_relatorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefRelatorio.findAll", query = "SELECT h FROM HefRelatorio h"),
    @NamedQuery(name = "HefRelatorio.findByIdrelatorio", query = "SELECT h FROM HefRelatorio h WHERE h.idrelatorio = :idrelatorio"),
    @NamedQuery(name = "HefRelatorio.findByDthlimpeza", query = "SELECT h FROM HefRelatorio h WHERE h.dthlimpeza = :dthlimpeza"),
    @NamedQuery(name = "HefRelatorio.findByDthgeracao", query = "SELECT h FROM HefRelatorio h WHERE h.dthgeracao = :dthgeracao"),
    @NamedQuery(name = "HefRelatorio.findByTitulo", query = "SELECT h FROM HefRelatorio h WHERE h.titulo = :titulo"),
    @NamedQuery(name = "HefRelatorio.findByStatus", query = "SELECT h FROM HefRelatorio h WHERE h.status = :status"),
    @NamedQuery(name = "HefRelatorio.findByIdexpirar", query = "SELECT h FROM HefRelatorio h WHERE h.idexpirar = :idexpirar"),
    @NamedQuery(name = "HefRelatorio.findByNupaginas", query = "SELECT h FROM HefRelatorio h WHERE h.nupaginas = :nupaginas")})
public class HefRelatorio implements Serializable {

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "parametros")
    private byte[] parametros;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrelatorio")
    private Long idrelatorio;
    @Column(name = "dthlimpeza")
    @Temporal(TemporalType.DATE)
    private Date dthlimpeza;
    @Column(name = "dthgeracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthgeracao;
    @Size(max = 700)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 250)
    @Column(name = "status")
    private String status;
    @Column(name = "idexpirar")
    private Boolean idexpirar;
    @Column(name = "nupaginas")
    private BigInteger nupaginas;
    @JoinColumn(name = "idtelas", referencedColumnName = "idtela")
    @ManyToOne
    private HefTelas idtelas;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private HefUsuario idusuario;
    @OneToOne(mappedBy = "idrelatorio", fetch = FetchType.LAZY)
    private HefRelatorioArq hefRelatorioArq;
    @OneToOne(mappedBy = "idrelatorio", fetch = FetchType.LAZY)
    private HefRelatorioDatasourceExcel hefRelatorioDatasourceExcel;

    public HefRelatorio() {
    }

    public HefRelatorio(Long idrelatorio) {
        this.idrelatorio = idrelatorio;
    }

    public Long getIdrelatorio() {
        return idrelatorio;
    }

    public void setIdrelatorio(Long idrelatorio) {
        this.idrelatorio = idrelatorio;
    }

    public Date getDthlimpeza() {
        return dthlimpeza;
    }

    public void setDthlimpeza(Date dthlimpeza) {
        this.dthlimpeza = dthlimpeza;
    }

    public Date getDthgeracao() {
        return dthgeracao;
    }

    public void setDthgeracao(Date dthgeracao) {
        this.dthgeracao = dthgeracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIdexpirar() {
        return idexpirar;
    }

    public void setIdexpirar(Boolean idexpirar) {
        this.idexpirar = idexpirar;
    }

    public BigInteger getNupaginas() {
        return nupaginas;
    }

    public void setNupaginas(BigInteger nupaginas) {
        this.nupaginas = nupaginas;
    }

    public HefTelas getIdtelas() {
        return idtelas;
    }

    public void setIdtelas(HefTelas idtelas) {
        this.idtelas = idtelas;
    }

    public HefUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(HefUsuario idusuario) {
        this.idusuario = idusuario;
    }

    public HefRelatorioArq getHefRelatorioArq() {
        return hefRelatorioArq;
    }

    public void setHefRelatorioArq(HefRelatorioArq hefRelatorioArq) {
        this.hefRelatorioArq = hefRelatorioArq;
    }

    public HefRelatorioDatasourceExcel getHefRelatorioDatasourceExcel() {
        return hefRelatorioDatasourceExcel;
    }

    public void setHefRelatorioDatasourceExcel(HefRelatorioDatasourceExcel hefRelatorioDatasourceExcel) {
        this.hefRelatorioDatasourceExcel = hefRelatorioDatasourceExcel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrelatorio != null ? idrelatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefRelatorio)) {
            return false;
        }
        HefRelatorio other = (HefRelatorio) object;
        if ((this.idrelatorio == null && other.idrelatorio != null) || (this.idrelatorio != null && !this.idrelatorio.equals(other.idrelatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefRelatorio[ idrelatorio=" + idrelatorio + " ]";
    }

    public byte[] getParametros() {
        return parametros;
    }

    public void setParametros(byte[] parametros) {
        this.parametros = parametros;
    }

}
