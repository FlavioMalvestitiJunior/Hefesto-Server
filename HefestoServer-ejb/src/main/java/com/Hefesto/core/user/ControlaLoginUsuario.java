/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.user;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefLiberacaoTela;
import com.Hefesto.core.entidades.HefLiberacaoTela_;
import com.Hefesto.core.entidades.HefPerfil_;
import com.Hefesto.core.entidades.HefTelas_;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.entidades.HefUsuarioInfo_;
import com.Hefesto.core.entidades.HefUsuario_;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Stateless(mappedName = "hef/core/usr/ControlaLoginUsuario")
@Interceptors({HFLoggerInterceptor.class})
public class ControlaLoginUsuario extends HFEntityMananger implements ControlaLoginUsuarioRemote {

    /**
     * verifica se o usuario esta cadastrado caso não esteja retorna null
     *
     * @param login
     * @param senha
     * @return
     * @throws Exception
     */
    @Override
    public HefUsuarioInfo validaLogin(String login, String senha) throws Exception {
        List<HefUsuarioInfo> lista = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefUsuarioInfo> from = query.from(HefUsuarioInfo.class);
        query.select(from);
        query.where(cb.equal(from.get(HefUsuarioInfo_.idusuario).get(HefUsuario_.login), login),
                cb.equal(from.get(HefUsuarioInfo_.senha), senha),
                cb.equal(from.get(HefUsuarioInfo_.idativo), true),
                cb.equal(from.get(HefUsuarioInfo_.idusuarioCancelado), false));
        lista = em.createQuery(query).getResultList();

        if (lista.isEmpty()) {
            lista.add(null);
        }
        return lista.get(0);
    }

    @Override
    public byte[] getTelasUsuario(Long iusuario, Boolean telasMenu) throws Exception {
        Long idperfil = em.find(HefUsuarioInfo.class, iusuario).getIdperfil().getIdperfil();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefLiberacaoTela> from = query.from(HefLiberacaoTela.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();
        wr.add(cb.equal(from.get(HefLiberacaoTela_.idativo), true));
        wr.add(cb.equal(from.get(HefLiberacaoTela_.idperfil).get(HefPerfil_.idperfil), idperfil));
        if (telasMenu) {
            wr.add(cb.equal(from.get(HefLiberacaoTela_.idtela).get(HefTelas_.idativo), true));
        }
        query.where(wr.toArray(new Predicate[]{}));
        return Compactar(em.createQuery(query).getResultList());
    }

    @Override
    public void salvaPreferencia(HefUsuarioInfo usuario) throws Exception {
        em.createQuery("UPDATE HefUsuarioInfo  i set i.idativo = false WHERE i.idusuario =:idusuario")
                .setParameter("idusuario", usuario.getIdusuario()).executeUpdate();
        usuario.setIdusuarioInfo(null);
        usuario.setDthcadastro(new Date());
        usuario.setIdativo(true);
        merge(usuario, usuario.getIdusuario().getIdusuario(), "Salva/Atualiza");
    }
}
