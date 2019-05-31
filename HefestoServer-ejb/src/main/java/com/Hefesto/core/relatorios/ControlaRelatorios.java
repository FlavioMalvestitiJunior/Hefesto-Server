/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.relatorios;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefPerfil_;
import com.Hefesto.core.entidades.HefRelatorio;
import com.Hefesto.core.entidades.HefRelatorioArq;
import com.Hefesto.core.entidades.HefRelatorioArq_;
import com.Hefesto.core.entidades.HefRelatorioDatasourceExcel;
import com.Hefesto.core.entidades.HefRelatorioDatasourceExcel_;
import com.Hefesto.core.entidades.HefRelatorio_;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.entidades.HefUsuarioInfo_;
import com.Hefesto.core.entidades.HefUsuario_;
import com.Hefesto.core.relatorios.objetos.ConstantesRelatorios;
import com.Hefesto.core.relatorios.objetos.FilaRelatorioObj;
import com.Hefesto.core.relatorios.objetos.RelatorioFilaObj;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Flavio
 */
@Stateless(mappedName = "hef/cor/rel/ControlaRelatorios")
@Interceptors({HFLoggerInterceptor.class})
public class ControlaRelatorios extends HFEntityMananger implements ControlaRelatoriosRemote, ControlaRelatoriosLocal {

    @EJB
    ProcessadorDeRelatorios processadorDeRelatorios;

    /**
     * Lista os 15 ultimos Relatórios.
     *
     * @param idusuario
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaRelatoriosUsuario(Long idusuario) throws Exception {
        return Compactar(buscaRelatorios(idusuario, true));
    }

    /**
     * Busca os Relatorios
     *
     * @param idusuario
     * @param limitar
     * @return
     * @throws Exception
     */
    private List<HefRelatorio> buscaRelatorios(Long idusuario, Boolean limitar) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefRelatorio> from = query.from(HefRelatorio.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();

        if (idusuario != null) {
            wr.add(cb.equal(from.get(HefRelatorio_.idusuario).get(HefUsuario_.idusuario), idusuario));
        }
        query.where(wr.toArray(new Predicate[]{}));
        if (limitar != null && limitar) {
            query.orderBy(cb.desc(from.get(HefRelatorio_.idrelatorio)));
            return em.createQuery(query).setMaxResults(15).getResultList();
        } else {
            return em.createQuery(query).getResultList();
        }
    }

    @Override
    public void teste(RelatorioFilaObj obj) throws Exception {
        System.out.println("Processando Relatório");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefUsuarioInfo> from = query.from(HefUsuarioInfo.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();
        if (obj.getUsuario().getIdperfil().getIdadministrador()) {
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idativo), true));
        } else {
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idativo), true));
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idfilial), obj.getUsuario().getIdfilial()));
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idperfil).get(HefPerfil_.idadministrador), false));

        }
        if (obj.getUsuario().getIdfilial() != null) {
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idfilial), obj.getUsuario().getIdfilial()));
        }
        query.where(wr.toArray(new Predicate[]{}));

        obj.setDias(2);
        obj.setQuery(query);
        obj.setRel(ConstantesRelatorios.TesteRelatorio);
        processadorDeRelatorios.processar(obj);
    }

    @Override
    public byte[] buscaArquivo(Long idRelatorio) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefRelatorioArq> from = query.from(HefRelatorioArq.class);
        query.select(from);
        query.where(cb.equal(from.get(HefRelatorioArq_.idrelatorio).get(HefRelatorio_.idrelatorio), idRelatorio));
        return Compactar(em.createQuery(query).getSingleResult());
    }

    @Override
    public byte[] buscaExcelDataSource(Long idRelatorio) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefRelatorioDatasourceExcel> from = query.from(HefRelatorioDatasourceExcel.class);
        query.select(from);
        query.where(cb.equal(from.get(HefRelatorioDatasourceExcel_.idrelatorio).get(HefRelatorio_.idrelatorio), idRelatorio));
        return Compactar(em.createQuery(query).getSingleResult());
    }

    @Override
    public boolean removerRelatorio(BigInteger relatorio, Long idUsuario) throws Exception {
        processadorDeRelatorios.removeRelatorio(relatorio, idUsuario);
        return true;
    }

    @Override
    public byte[] filaRelatorios(Long idUsuario) throws Exception {
        List<FilaRelatorioObj> lista = new ArrayList<>();
        if (idUsuario != null) {
            for (FilaRelatorioObj processando : processadorDeRelatorios.processando()) {
                if (processando.getIdUsuario() == idUsuario) {
                    lista.add(processando);
                }
            }
            return Compactar(lista);
        }
        return Compactar(processadorDeRelatorios.processando());
    }

    @Override
    public void clean() {
        List<HefRelatorio> relatorios = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefRelatorio> from = query.from(HefRelatorio.class);
        query.select(from);
        query.where(cb.lessThanOrEqualTo(from.get(HefRelatorio_.dthlimpeza), new Date()),
                cb.equal(from.get(HefRelatorio_.idexpirar), true));
        relatorios = em.createQuery(query).getResultList();

        for (HefRelatorio relatorio : relatorios) {
            if (relatorio.getHefRelatorioArq() != null) {
                em.remove(relatorio.getHefRelatorioArq());
            }
            if (relatorio.getHefRelatorioDatasourceExcel() != null) {
                em.remove(relatorio.getHefRelatorioDatasourceExcel());
            }
            em.remove(relatorio);
        }
    }

}
