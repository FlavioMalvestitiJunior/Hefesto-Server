/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefMenu;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.util.List;
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
@Stateless(mappedName = "hef/core/pndc/ControlaMenu")
public class ControlaMenu extends HFEntityMananger implements ControlaMenuLocal, ControlaMenuRemote {

    /**
     * Lista os Menus
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<HefMenu> listaMenusLocal() throws Exception {
        return buscaMenusPrivate();
    }

    /**
     * Faz a busca pelos menus
     *
     * @return
     */
    private List<HefMenu> buscaMenusPrivate() throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefMenu> from = query.from(HefMenu.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }

    @Override
    public byte[] listaMenus() throws Exception {
        return Compactar(buscaMenusPrivate());
    }

    @Override
    public void salvarMenu(Long idusuario, HefMenu menu) throws Exception {
        merge(menu, idusuario, "Salvar/Atualizar");
    }

    @Override
    public void removerMenu(Long idusuario, HefMenu menu) throws Exception {
        remove(menu, idusuario, "Excluir");
    }

}
