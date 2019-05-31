/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefFilial;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefPerfil_;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.entidades.HefUsuarioInfo_;
import com.Hefesto.core.entidades.HefUsuario_;
import com.Hefesto.core.utils.HFLoggerInterceptor;
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
@Interceptors({HFLoggerInterceptor.class})
@Stateless(mappedName = "hef/core/pndc/ControlaUsuario")
public class ControlaUsuario extends HFEntityMananger implements ControlaUsuarioRemote, ControlaUsuarioLocal {

    @EJB
    ControlaFilialLocal controlaFilialLocal;
    @EJB
    ControlaPerfilUsuarioLocal controlaPerfilUsuarioLocal;

    @Override
    public byte[] listaUsuarios(HefUsuarioInfo usuario) throws Exception {
        return Compactar(buscaUsuario(usuario, null));
    }

    /**
     * Lista os perfis conforme os parametros, se for usuario administrador traz
     * tudo senão traz somente os perfis da filial.
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
     * retorna os usuarios conforme os parametros.
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    private List<HefUsuarioInfo> buscaUsuario(HefUsuarioInfo usuario, HefFilial filial) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefUsuarioInfo> from = query.from(HefUsuarioInfo.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();
        wr.add(cb.equal(from.get(HefUsuarioInfo_.idativo), true));
        if (usuario.getIdperfil().getIdadministradorEmpresa()) {
            wr.add(from.get(HefUsuarioInfo_.idfilial).in(controlaFilialLocal.buscaFilialGerenciadasUsuario(usuario)));
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idperfil).get(HefPerfil_.idadministrador), false));
        } else if (usuario.getIdperfil().getIdgerente()) {
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idfilial), usuario.getIdfilial()));
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idperfil).get(HefPerfil_.idadministrador), false));
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idperfil).get(HefPerfil_.idadministradorEmpresa), false));
        }

        if (filial != null) {
            wr.add(cb.equal(from.get(HefUsuarioInfo_.idfilial), filial));
        }
        query.where(wr.toArray(new Predicate[]{}));
        return em.createQuery(query).getResultList();
    }

    /**
     * Salva o usuario e o retorna
     *
     * @param idUsuario
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public byte[] salvarUsuario(Long idUsuario, HefUsuarioInfo usuario) throws Exception {
        return Compactar(salvarUsuarioPrivate(idUsuario, usuario));
    }

    /**
     * Salva o usuario e o retorna
     *
     * @param idUsuario
     * @param usuario
     * @return
     * @throws Exception
     */
    private HefUsuarioInfo salvarUsuarioPrivate(Long idUsuario, HefUsuarioInfo usuario) throws Exception {
        if (usuario.getIdusuario().getIdusuario() != null) {
            em.createQuery("UPDATE HefUsuarioInfo i set i.idativo = false where i.idusuario=:usuario")
                    .setParameter("usuario", usuario.getIdusuario()).executeUpdate();
        }
        HefUsuario usu = merge(usuario.getIdusuario(), idUsuario, "Salva/Atualiza");
        usuario.setIdusuario(usu);
        usuario.setIdusuarioCadastro(em.getReference(HefUsuario.class, idUsuario));
        usuario.setIdativo(Boolean.TRUE);
        usuario.setIdusuarioSistema(Boolean.FALSE);
        usuario.setDthcadastro(new Date());
        usuario.setIdusuarioInfo(null);

        return merge(usuario, idUsuario, "Salvar/Atualizar");
    }

    /**
     * Salva o Usuario
     *
     * @param idUsuario
     * @param usuario
     * @throws Exception
     */
    @Override
    public void salvarUsuarioLocal(Long idUsuario, HefUsuarioInfo usuario) throws Exception {
        salvarUsuarioPrivate(idUsuario, usuario);
    }

    /**
     * Lista as Filiais cadastradas
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaFiliais(HefUsuarioInfo usuario) throws Exception {
        return Compactar(controlaFilialLocal.ListaFiliaisUsuarioLocal(usuario));
    }

    /**
     * Lista os Funcionarios da Filial
     *
     * @return @throws Exception
     */
    @Override
    public List<HefUsuarioInfo> listaUsuariosFilial(HefUsuarioInfo usuario, HefFilialInfo filial) throws Exception {
        return buscaUsuario(usuario, filial.getIdfilial());
    }

    @Override
    public HefUsuarioInfo buscaInfoAtivo(Long idusuario) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefUsuarioInfo> from = query.from(HefUsuarioInfo.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();
        wr.add(cb.equal(from.get(HefUsuarioInfo_.idativo), true));
        wr.add(cb.equal(from.get(HefUsuarioInfo_.idusuario).get(HefUsuario_.idusuario), idusuario));
        query.where(wr.toArray(new Predicate[]{}));
        return (HefUsuarioInfo) em.createQuery(query).getSingleResult();
    }

}
