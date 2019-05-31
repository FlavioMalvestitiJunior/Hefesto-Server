/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefPerfil;
import com.Hefesto.core.entidades.HefPerfilEmpresa;
import com.Hefesto.core.entidades.HefPerfilEmpresa_;
import com.Hefesto.core.entidades.HefPerfil_;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.utils.HFLoggerInterceptor;
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
@Stateless(mappedName = "hef/core/pndc/ControlaPerfilUsusario")
public class ControlaPerfilUsuario extends HFEntityMananger implements ControlaPerfilUsuarioRemote, ControlaPerfilUsuarioLocal {

    @EJB
    ControlaEmpresaLocal controlaEmpresa;
    @EJB
    ControlaFilialLocal controlaFilialLocal;

    @Override
    public byte[] listaPerfis(HefUsuarioInfo usuario) throws Exception {
        return Compactar(buscaPerfis(usuario));
    }

    /**
     * busca os Perfis aos quais o usuario tem acesso.
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    private List<HefPerfilEmpresa> buscaPerfis(HefUsuarioInfo usuario) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefPerfilEmpresa> from = query.from(HefPerfilEmpresa.class);
        query.select(from);
        if (usuario.getIdperfil().getIdadministrador()) {
            query.where(cb.equal(from.get(HefPerfilEmpresa_.idativo), true),
                    cb.equal(from.get(HefPerfilEmpresa_.idperfil).get(HefPerfil_.idativo), true));
        } else if (usuario.getIdperfil().getIdadministradorEmpresa()) {
            query.where(cb.equal(from.get(HefPerfilEmpresa_.idativo), true),
                    cb.equal(from.get(HefPerfilEmpresa_.idperfil).get(HefPerfil_.idativo), true),
                    cb.equal(from.get(HefPerfilEmpresa_.idperfil).get(HefPerfil_.idadministrador), false),
                    from.get(HefPerfilEmpresa_.idempresa).in(controlaEmpresa.buscaEmpresaUsuario(usuario.getIdusuario())));
        } else if (usuario.getIdperfil().getIdgerente()) {
            query.where(cb.equal(from.get(HefPerfilEmpresa_.idempresa), controlaEmpresa.buscaEmpresaUsuario(usuario.getIdusuario())),
                    cb.equal(from.get(HefPerfilEmpresa_.idativo), true),
                    cb.equal(from.get(HefPerfilEmpresa_.idperfil).get(HefPerfil_.idativo), true),
                    cb.equal(from.get(HefPerfilEmpresa_.idperfil).get(HefPerfil_.idadministrador), false),
                    cb.equal(from.get(HefPerfilEmpresa_.idperfil).get(HefPerfil_.idadministradorEmpresa), false));
        }
        return em.createQuery(query).getResultList();
    }

    public List<HefPerfilEmpresa> listaPerfisEmpresaLocal(HefUsuarioInfo usuario) throws Exception {
        return buscaPerfis(usuario);
    }

    /**
     * Desativa a Informação Antiga se Houver e salva a nova.
     *
     * @param idusuario
     * @param perfilEmpresa
     * @throws Exception
     */
    @Override
    public void salvarPerfilEmpresa(Long idusuario, HefPerfilEmpresa perfilEmpresa) throws Exception {
        em.createQuery("UPDATE HefPerfilEmpresa pf SET pf.idativo = false WHERE pf.idperfilEmpresa =:perfilEmpresa")
                .setParameter("perfilEmpresa", perfilEmpresa.getIdperfilEmpresa()).executeUpdate();

        HefPerfil perfil = perfilEmpresa.getIdperfil();
        perfil.setDthcadastro(new Date());
        perfil.setIdusuario(em.getReference(HefUsuario.class, idusuario));
        perfil.setIdativo(Boolean.TRUE);
        perfil = merge(perfil, idusuario, "Salva/Atualiza");
        perfilEmpresa.setIdperfil(perfil);
        perfilEmpresa.setIdativo(Boolean.TRUE);
        perfilEmpresa.setIdperfilEmpresa(null);
        perfilEmpresa.setDthcadastro(new Date());
        perfilEmpresa.setIdusuario(em.getReference(HefUsuario.class, idusuario));

        merge(perfilEmpresa, idusuario, "Salva/Atualiza");
    }

    /**
     * Desativa a Informação.
     *
     * @param idusuario
     * @param PerfilEmpresa
     * @throws Exception
     */
    @Override
    public void removerPerfilEmpresa(Long idusuario, HefPerfilEmpresa PerfilEmpresa) throws Exception {
        em.createQuery("UPDATE HefPerfilEmpresa pe SET pe.idativo =false  WHERE pe.idperfilEmpresa =:pefilEmpresa")
                .setParameter("pefilEmpresa", PerfilEmpresa.getIdperfilEmpresa()).executeUpdate();
        em.createQuery("UPDATE HefPerfil SET idativo =false  WHERE idperfil =:perfil")
                .setParameter("perfil", PerfilEmpresa.getIdperfil().getIdperfil()).executeUpdate();

        PerfilEmpresa.setIdativo(Boolean.FALSE);
        PerfilEmpresa.setIdperfilEmpresa(null);
        PerfilEmpresa.setDthcadastro(new Date());
        PerfilEmpresa.setIdusuario(em.getReference(HefUsuario.class, idusuario));

        merge(PerfilEmpresa, idusuario, "Salva/Atualiza");
    }

    @Override
    public byte[] listaFiliais() throws Exception {
        return Compactar(controlaFilialLocal.ListaFiliaisLocal());
    }

    private List<HefPerfil> buscaPerfilPrivate(HefUsuarioInfo usuario) throws Exception {
        List<HefPerfil> perfis = new ArrayList<>();
        for (HefPerfilEmpresa p : buscaPerfis(usuario)) {
            perfis.add(p.getIdperfil());
        }
        return perfis;
    }

    @Override
    public List<HefPerfil> listaPerfisLocal(HefUsuarioInfo usuario) throws Exception {
        return buscaPerfilPrivate(usuario);
    }

    @Override
    public byte[] buscaEmpresaUsuario(HefUsuario idusuario) throws Exception {
        return Compactar(controlaEmpresa.buscaEmpresaUsuario(idusuario));
    }

    @Override
    public byte[] listaEmpresas() throws Exception {
        return Compactar(controlaEmpresa.listaEmpresasInfoLocal());
    }

}
