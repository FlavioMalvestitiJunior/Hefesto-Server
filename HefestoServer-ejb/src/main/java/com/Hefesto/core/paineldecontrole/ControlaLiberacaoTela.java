/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefLiberacaoTela;
import com.Hefesto.core.entidades.HefLiberacaoTela_;
import com.Hefesto.core.entidades.HefPerfil;
import com.Hefesto.core.entidades.HefTelas;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import com.hefesto.hefestocomponentes.HFPair.HFPair;
import java.util.ArrayList;
import java.util.Date;
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
@Stateless(mappedName = "hef/core/pndc/ControlaLiberacaoTela")
public class ControlaLiberacaoTela extends HFEntityMananger implements ControlaLiberacaoTelaRemote {

    @EJB
    ControlaPerfilUsuarioLocal controlaPerfilUsuarioLocal;

    /**
     * lista os perfis conforme as credenciais do usuario, se for gerente traz
     * somente os perfis disponiveis em sua filial, se for administrador traz
     * todos os perfis do sistema.
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaPerfis(HefUsuarioInfo usuario) throws Exception {
        return Compactar(controlaPerfilUsuarioLocal.listaPerfisLocal(usuario));
    }

    /**
     * lista as telas liberadas para o perfil.
     *
     * @param perfil
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaTelasLiberadasPerfil(HefPerfil perfil) throws Exception {
        return Compactar(buscaTelasLiberadasPerfilPrivate(perfil));
    }

    /**
     * lista as telas disponiveis para liberação conforme as credenciais do
     * usuario
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaTelasDisponiveis(HefUsuarioInfo usuario) throws Exception {
        return Compactar(buscaTelasDisponiveisPerfilPrivate(usuario));
    }

    /**
     * busca as telas liberadas para o perfil.
     *
     * @param perfil
     * @return
     */
    private List<HefLiberacaoTela> buscaTelasLiberadasPerfilPrivate(HefPerfil perfil) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefLiberacaoTela> from = query.from(HefLiberacaoTela.class);
        query.select(from);
        query.where(cb.equal(from.get(HefLiberacaoTela_.idperfil), perfil),
                cb.equal(from.get(HefLiberacaoTela_.idativo), true));
        return em.createQuery(query).getResultList();
    }

    /**
     * busca as telas para liberação conforme o perfil do usuario, se for
     * administrador traz todas as telas do sistema se for gerente traz somente
     * as que ele tem acesso.
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    private List<HefTelas> buscaTelasDisponiveisPerfilPrivate(HefUsuarioInfo usuario) throws Exception {
        List<HefTelas> telas = new ArrayList<>();
        if (usuario.getIdperfil().getIdadministrador()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery query = cb.createQuery();
            Root<HefTelas> from = query.from(HefTelas.class);
            query.select(from);
            telas = em.createQuery(query).getResultList();
        } else {
            for (HefLiberacaoTela telasLib : buscaTelasLiberadasPerfilPrivate(usuario.getIdperfil())) {
                telas.add(telasLib.getIdtela());
            }
        }
        return telas;
    }

    /**
     * Salva as alterações nas liberações das telas.
     *
     * @param idusuario
     * @param pair
     * @throws Exception
     */
    @Override
    public void salvarLiberacao(Long idusuario, HFPair<HefPerfil, List<HefLiberacaoTela>> pair) throws Exception {
        em.createQuery("UPDATE HefLiberacaoTela l set l.idativo = false where l.idperfil=:perfil").setParameter("perfil", pair.getOne()).executeUpdate();
        HefUsuario usu = em.find(HefUsuario.class, idusuario);
        for (HefLiberacaoTela t : pair.getTwo()) {
            t.setIdusuario(usu);
            t.setIdativo(true);
            t.setDthcadastro(new Date());
            t.setIdliberacaoTela(null);

            merge(t, idusuario, "Salva/Atualiza");
        }
    }

}
