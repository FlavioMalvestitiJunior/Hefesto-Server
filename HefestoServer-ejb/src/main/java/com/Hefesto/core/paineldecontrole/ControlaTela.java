/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefMenu_;
import com.Hefesto.core.entidades.HefTelas;
import com.Hefesto.core.entidades.HefTelas_;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Flavio
 */
@Interceptors({HFLoggerInterceptor.class})
@Stateless(mappedName = "hef/core/pndc/ControlaTela")
public class ControlaTela extends HFEntityMananger implements ControlaTelaRemote {

    @EJB
    private ControlaMenuLocal controlaMenuLocal;

    /**
     * Disponibiliza na interface remota a lista das telas cadastradas.
     *
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaTelasCadastradas() throws Exception {
        return Compactar(buscaTelasCadastradasPrivate());
    }

    /**
     * busca todas as telas cadastradas
     *
     * @return
     * @throws Exception
     */
    private List<HefTelas> buscaTelasCadastradasPrivate() throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefTelas> from = query.from(HefTelas.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }

    /**
     * Lista os Menus do Sistema
     *
     * @return
     * @throws Exception
     */
    public byte[] listaMenus() throws Exception {
        return Compactar(controlaMenuLocal.listaMenusLocal());
    }

    /**
     * Salva a Tela no Banco de Dados
     *
     * @param idusuario
     * @param tela
     * @throws Exception
     */
    @Override
    public void salvarTela(Long idusuario, HefTelas tela) throws Exception {
        merge(tela, idusuario, "Salva/Atualiza");
    }

    /**
     * Busca o Proximo codigo para tela de a cordo com o menu selecionado
     *
     * @param idmenu
     * @return
     * @throws Exception
     */
    @Override
    public BigInteger buscaSequencialTela(Long idmenu) throws Exception {
        List<BigInteger> lista;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefTelas> from = query.from(HefTelas.class);
        query.select(cb.max(from.get(HefTelas_.codigoTela)));
        query.where(cb.equal(from.get(HefTelas_.idmenu).get(HefMenu_.idmenu), idmenu));
        lista = em.createQuery(query).getResultList();
        if (lista.isEmpty()) {
            return BigInteger.ONE;
        } else {
            return lista.get(0).add(BigInteger.ONE);
        }
    }

    /**
     * Remove a Tela Selecionada.
     *
     * @param idusuario
     * @param tela
     * @throws Exception
     */
    @Override
    public void removerTela(Long idusuario, HefTelas tela) throws Exception {
        em.createQuery("Delete from HefLiberacaoTela lib where lib.idtela=:tela")
                .setParameter("tela", tela).executeUpdate();
        remove(tela, idusuario, "Deleta Tela");

    }

}
