/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "hef_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HefCargo.findAll", query = "SELECT h FROM HefCargo h"),
    @NamedQuery(name = "HefCargo.findByIdcargo", query = "SELECT h FROM HefCargo h WHERE h.idcargo = :idcargo"),
    @NamedQuery(name = "HefCargo.findByCodigo", query = "SELECT h FROM HefCargo h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HefCargo.findByDescricao", query = "SELECT h FROM HefCargo h WHERE h.descricao = :descricao"),
    @NamedQuery(name = "HefCargo.findBySalarioMedio", query = "SELECT h FROM HefCargo h WHERE h.salarioMedio = :salarioMedio"),
    @NamedQuery(name = "HefCargo.findByIdativo", query = "SELECT h FROM HefCargo h WHERE h.idativo = :idativo")})
public class HefCargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcargo")
    private Long idcargo;
    @Size(max = 250)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 700)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_medio")
    private BigDecimal salarioMedio;
    @Column(name = "idativo")
    private Boolean idativo;
    @OneToMany(mappedBy = "idcargo")
    private Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection;

    public HefCargo() {
    }

    public HefCargo(Long idcargo) {
        this.idcargo = idcargo;
    }

    public Long getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Long idcargo) {
        this.idcargo = idcargo;
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

    public BigDecimal getSalarioMedio() {
        return salarioMedio;
    }

    public void setSalarioMedio(BigDecimal salarioMedio) {
        this.salarioMedio = salarioMedio;
    }

    public Boolean getIdativo() {
        return idativo;
    }

    public void setIdativo(Boolean idativo) {
        this.idativo = idativo;
    }

    @XmlTransient
    public Collection<HefFuncionarioInfo> getHefFuncionarioInfoCollection() {
        return hefFuncionarioInfoCollection;
    }

    public void setHefFuncionarioInfoCollection(Collection<HefFuncionarioInfo> hefFuncionarioInfoCollection) {
        this.hefFuncionarioInfoCollection = hefFuncionarioInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcargo != null ? idcargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HefCargo)) {
            return false;
        }
        HefCargo other = (HefCargo) object;
        if ((this.idcargo == null && other.idcargo != null) || (this.idcargo != null && !this.idcargo.equals(other.idcargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hefesto.core.entidades.HefCargo[ idcargo=" + idcargo + " ]";
    }
    
}
