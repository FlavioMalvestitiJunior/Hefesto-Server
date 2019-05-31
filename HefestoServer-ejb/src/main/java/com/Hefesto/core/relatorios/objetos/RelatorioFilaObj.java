/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.relatorios.objetos;

import com.Hefesto.core.entidades.HefTelas;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.relatorios.RelatorioExecutorLocal;
import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Flavio
 */
public class RelatorioFilaObj implements Callable<RelatorioFilaObj>, Serializable {

    private RelatorioExecutorLocal relatorioExecutor;
    private Object parametros;
    private String titulo;
    private HefTelas tela;
    private HefUsuarioInfo usuario;
    private Integer dias;
    private CriteriaQuery query;
    private boolean processaBean = false;
    private boolean idExpirar = true;
    private boolean needsExcelDataSource = false;
    private String conversor;
    private String status = ConstantesStatus.ESPERA;
    private File jasper;
    private String rel;
    private List<String> subrel = new ArrayList<>();
    private BigInteger PID;
    private Date dathRel;

    @Override
    public RelatorioFilaObj call() {
        try {
            relatorioExecutor.executa(this);
            return this;
        } catch (Exception ex) {
            return this;
        }
    }

    public RelatorioExecutorLocal getRelatorioExecutor() {
        return relatorioExecutor;
    }

    public void setRelatorioExecutor(RelatorioExecutorLocal relatorioExecutor) {
        this.relatorioExecutor = relatorioExecutor;
    }

    public Object getParametros() {
        return parametros;
    }

    public void setParametros(Object parametros) {
        this.parametros = parametros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public HefTelas getTela() {
        return tela;
    }

    public void setTela(HefTelas tela) {
        this.tela = tela;
    }

    public HefUsuarioInfo getUsuario() {
        return usuario;
    }

    public void setUsuario(HefUsuarioInfo usuario) {
        this.usuario = usuario;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public CriteriaQuery getQuery() {
        return query;
    }

    public void setQuery(CriteriaQuery query) {
        this.query = query;
    }

    public boolean isProcessaBean() {
        return processaBean;
    }

    public void setProcessaBean(boolean processaBean) {
        this.processaBean = processaBean;
    }

    public String getConversor() {
        return conversor;
    }

    public void setConversor(String conversor) {
        this.conversor = conversor;
    }

    public boolean isNeedsExcelDataSource() {
        return needsExcelDataSource;
    }

    public void setNeedsExcelDataSource(boolean needsExcelDataSource) {
        this.needsExcelDataSource = needsExcelDataSource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIdExpirar() {
        return idExpirar;
    }

    public void setIdExpirar(boolean idExpirar) {
        this.idExpirar = idExpirar;
    }

    public File getJasper() {
        return jasper;
    }

    public void setJasper(File jasper) {
        this.jasper = jasper;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public List<String> getSubrel() {
        return subrel;
    }

    public void setSubrel(List<String> subrel) {
        this.subrel = subrel;
    }

    public void setID(BigInteger PIDR) {
        this.PID = PIDR;
    }

    public BigInteger getID() {
        return PID;
    }

    public Date getDthRel() {
        return dathRel;
    }

    public void setDthRel(Date date) {
        this.dathRel = date;
    }

}
