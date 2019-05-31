/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefNotas;
import com.Hefesto.core.entidades.HefNotas_;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author Flavio
 */
@Interceptors({HFLoggerInterceptor.class})
@Stateless(mappedName = "hef/core/pndc/ControlaNotasDaVersao")
public class ControlaNotasDaVersao extends HFEntityMananger implements ControlaNotasDaVersaoRemote {

    @Override
    public byte[] buscaNotaDaVersao(String versao) throws Exception {
        List<HefNotas> notas = buscaListaNotasDaVersao(versao, false);
        if (notas.isEmpty()) {
            return null;
        }
        return Compactar(notas.get(0));
    }

    @Override
    public void salvarNota(HefNotas notas, Long idUsuario) throws Exception {
        notas.setDatarelease(new Date());
        merge(notas, idUsuario, "Nota Versão");
    }

    private List<HefNotas> buscaListaNotasDaVersao(String versao, Boolean ordered) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefNotas> from = query.from(HefNotas.class);
        query.select(from);
        if (versao != null && !versao.isEmpty()) {
            query.where(cb.equal(from.get(HefNotas_.versao), versao));
        }
        if (ordered) {
            query.orderBy(cb.desc(from.get(HefNotas_.idnotas)));
        }
        return em.createQuery(query).getResultList();
    }

    @Override
    public byte[] listaNotaDaVersao() throws Exception {
        return Compactar(buscaListaNotasDaVersao(null, true));
    }

    public byte[] buscaUltimaNota() throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefNotas> from = query.from(HefNotas.class);
        query.select(from);
        Subquery subq = query.subquery(Long.class);
        Root<HefNotas> subfrom = subq.from(HefNotas.class);
        subq.select(cb.max(subfrom.get(HefNotas_.idnotas)));
        query.where(from.get(HefNotas_.idnotas).in(subq));
        return Compactar(em.createQuery(query).getSingleResult());
    }

}
