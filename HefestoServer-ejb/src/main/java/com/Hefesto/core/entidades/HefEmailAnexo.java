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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author Flavio
 */
@Entity
@Table(name = "hef_email_anexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefEmailAnexo.findAll", query = "SELECT h FROM HefEmailAnexo h"),
    @NamedQuery(name = "HefEmailAnexo.findByIdanexo", query = "SELECT h FROM HefEmailAnexo h WHERE h.idanexo = :idanexo"),
    @NamedQuery(name = "HefEmailAnexo.findByNomeArquivo", query = "SELECT h FROM HefEmailAnexo h WHERE h.nomeArquivo = :nomeArquivo")})
public class HefEmailAnexo implements Serializable {

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "arquivo")
    private byte[] arquivo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idanexo")
    private Long idanexo;
    @Size(max = 2147483647)
    @Column(name = "nome_arquivo")
    private String nomeArquivo;
    @JoinColumn(name = "idemail", referencedColumnName = "idemail")
    @ManyToOne
    private HefEmail idemail;

    public HefEmailAnexo() {
    }

    public HefEmailAnexo(Long idanexo) {
        this.idanexo = idanexo;
    }

    public Long getIdanexo() {
        return idanexo;
    }

    public void setIdanexo(Long idanexo) {
        this.idanexo = idanexo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public HefEmail getIdemail() {
        return idemail;
    }

    public void setIdemail(HefEmail idemail) {
        this.idemail = idemail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idanexo != null ? idanexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefEmailAnexo)) {
            return false;
        }
        HefEmailAnexo other = (HefEmailAnexo) object;
        if ((this.idanexo == null && other.idanexo != null) || (this.idanexo != null && !this.idanexo.equals(other.idanexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefEmailAnexo[ idanexo=" + idanexo + " ]";
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

}
