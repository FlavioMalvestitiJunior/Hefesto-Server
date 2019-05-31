/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.relatorios;

import com.Hefesto.core.relatorios.objetos.ConstantesStatus;
import com.Hefesto.core.relatorios.objetos.FilaRelatorioObj;
import com.Hefesto.core.relatorios.objetos.RelatorioFilaObj;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import com.Hefesto.core.utils.Messages;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.interceptor.Interceptors;

/**
 *
 * @author Flavio
 */
@Singleton(name = "ProcessadorDeRelatorios")
@LocalBean
@Interceptors({HFLoggerInterceptor.class})
public class ProcessadorDeRelatorios {

    @EJB
    RelatorioExecutorLocal RelatorioExecutorLocal;

    @Resource(lookup = "ProcessadorRelatoriosHefesto")
    private ManagedExecutorService threadPool;
    private ConcurrentHashMap<BigInteger, RelatorioFilaObj> filaRelatorios = new ConcurrentHashMap<>();
    private BigInteger PIDR = BigInteger.ONE;

    public void processar(RelatorioFilaObj relatorio) {
        relatorio.setDthRel(new Date());
        relatorio.setRelatorioExecutor(RelatorioExecutorLocal);
        relatorio.setID(PIDR);
        filaRelatorios.put(relatorio.getID(), relatorio);
        threadPool.submit(relatorio);
        PIDR = PIDR.add(BigInteger.ONE);
    }

    public void processado(RelatorioFilaObj relatorio) {
        filaRelatorios.remove(relatorio.getID());
    }

    public List<FilaRelatorioObj> processando() {
        List<FilaRelatorioObj> fila = new ArrayList<>();
        FilaRelatorioObj r;
        for (RelatorioFilaObj value : filaRelatorios.values()) {
            r = new FilaRelatorioObj();
            r.setPID(value.getID());
            r.setNome(value.getUsuario().getNome());
            r.setStatus(value.getStatus());
            r.setTitulo(value.getTitulo());
            r.setData(value.getDthRel());
            fila.add(r);
        }
        return fila;
    }

    public void atualizaOBJ(RelatorioFilaObj relatorio) {
        filaRelatorios.put(relatorio.getID(), relatorio);
    }

    public void rmRelatorio(RelatorioFilaObj relatorio) {
        filaRelatorios.remove(relatorio.getID());
    }

    public void removeRelatorio(BigInteger relatorio, Long idusuario) throws Exception {
        if (filaRelatorios.containsKey(relatorio)) {
            if (filaRelatorios.get(relatorio).getUsuario().getIdusuario().getIdusuario() == (idusuario)) {
                throw new Exception(Messages.getMessage("relatorio.usuario.erro"));
            }
            if (!filaRelatorios.get(relatorio).getStatus().equals(ConstantesStatus.ESPERA)) {
                throw new Exception(Messages.getMessage("relatorio.processando.erro"));
            }
            filaRelatorios.remove(relatorio);
        }
    }

    public boolean contains(RelatorioFilaObj relatorioFilaObj) throws Exception {
        return filaRelatorios.containsKey(relatorioFilaObj.getID());
    }
}
