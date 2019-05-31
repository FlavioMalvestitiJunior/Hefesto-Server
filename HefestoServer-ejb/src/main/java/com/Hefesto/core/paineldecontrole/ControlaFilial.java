/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.cadastro.gerais.ControlaCadastroCidadeLocal;
import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefEmpresa;
import com.Hefesto.core.entidades.HefFilial;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefFilialInfo_;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import com.Hefesto.core.utils.Messages;
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
@Interceptors({HFLoggerInterceptor.class})
@Stateless(mappedName = "hef/core/pndc/ControlaFilial")
public class ControlaFilial extends HFEntityMananger implements ControlaFilialLocal, ControlaFilialRemote {

    @EJB
    ControlaEmpresaLocal controlaEmpresaLocal;
    @EJB
    ControlaUsuarioLocal controlaUsuarioLocal;
    @EJB
    ControlaCadastroCidadeLocal cadastroCidadeLocal;

    /**
     * Lista todas as Filiais Info Ativas.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<HefFilialInfo> ListaFiliaisLocal() throws Exception {
        return buscaFiliaisPrivate(null, null, null, null);
    }

    /**
     * Busca as Filiais Info conforme os parametros
     *
     * @return
     * @throws Exception
     */
    private List<HefFilialInfo> buscaFiliaisPrivate(HefUsuarioInfo usuario, String codigo, HefFilial filial, HefEmpresa empresa) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefFilialInfo> from = query.from(HefFilialInfo.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();
        wr.add(cb.equal(from.get(HefFilialInfo_.idativo), true));

        if (usuario != null && usuario.getIdperfil().getIdgerente()) {
            wr.add(cb.equal(from.get(HefFilialInfo_.idfilial), usuario.getIdfilial()));
        } else if (usuario != null && usuario.getIdperfil().getIdadministradorEmpresa()) {
            wr.add(from.get(HefFilialInfo_.idfilial).in(buscaFilialGerenciadasUsuario(usuario)));
        }
        if (codigo != null) {
            wr.add(cb.equal(from.get(HefFilialInfo_.codigo), codigo));
        }
        if (filial != null) {
            wr.add(cb.equal(from.get(HefFilialInfo_.idfilial), filial));
        }
        if (empresa != null) {
            wr.add(cb.equal(from.get(HefFilialInfo_.idempresa), empresa));
        }
        query.where(wr.toArray(new Predicate[]{}));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<HefFilialInfo> ListaFiliaisUsuarioLocal(HefUsuarioInfo usuario) throws Exception {
        return buscaFiliaisPrivate(usuario, null, null, null);
    }

    @Override
    public byte[] listaFiliais(HefUsuarioInfo usuario) throws Exception {
        return Compactar(buscaFiliaisPrivate(usuario, null, null, null));
    }

    @Override
    public void salvarFilial(Long idusuarioInfo, HefFilialInfo filial) throws Exception {
        List<HefFilialInfo> fils = buscaFiliaisPrivate(null, filial.getCodigo(), null, null);
        if (!fils.isEmpty()) {
            if (filial.getIdfilial().getIdfilial() == null
                    || (!fils.get(0).getIdfilial().getIdfilial().equals(filial.getIdfilial().getIdfilial()))) {
                System.out.println("Filial Buscada: " + fils.get(0).getIdfilial().getIdfilial() + " OUTRA FILIAL" + filial.getIdfilial().getIdfilial());
                throw new Exception(Messages.getMessage("filial.erro.codigo"));
            }
        }
        HefUsuarioInfo usuario = em.find(HefUsuarioInfo.class, idusuarioInfo);
        List<HefUsuarioInfo> usuarios = new ArrayList<>();
        if (filial.getIdfilial().getIdfilial() != null) {
            em.createQuery("UPDATE HefFilialInfo i SET i.idativo = false where i.idfilial=:idfilial")
                    .setParameter("idfilial", filial.getIdfilial()).executeUpdate();
            usuarios = controlaUsuarioLocal.listaUsuariosFilial(usuario, filial);
        }
        HefFilial fil = merge(filial.getIdfilial(), usuario.getIdusuario().getIdusuario(), "Salvar/Atualizar");
        filial.setIdfilialInfo(null);
        filial.setDthcadastro(new Date());
        filial.setIdfilial(fil);
        filial.setIdativo(Boolean.TRUE);
        filial.setIdusuario(usuario.getIdusuario());
        merge(filial, usuario.getIdusuario().getIdusuario(), "Salvar/Atualizar");

        if (filial.getIdcancelada()) {
            for (HefUsuarioInfo usu : usuarios) {
                usu.setIdusuarioCancelado(filial.getIdcancelada());
                em.detach(usu);
                controlaUsuarioLocal.salvarUsuarioLocal(usuario.getIdusuario().getIdusuario(), usu);
            }
        }
    }

    @Override
    public byte[] buscaLogradouro(BigInteger cep) throws Exception {
        return Compactar(cadastroCidadeLocal.buscaCidadeCep(cep));
    }

    @Override
    public HefFilialInfo buscaFilialInfo(HefFilial idfilial) throws Exception {
        return buscaFiliaisPrivate(null, null, idfilial, null).get(0);
    }

    @Override
    public HefFilialInfo buscaFilialInfoId(Long idfilial) throws Exception {
        return buscaFiliaisPrivate(null, null, em.find(HefFilial.class, idfilial), null).get(0);
    }

    @Override
    public List<HefFilialInfo> buscaFiliaisEmpresa(HefEmpresa empresa) throws Exception {
        return buscaFiliaisPrivate(null, null, null, empresa);
    }

    @Override
    public void salvaFilialLocal(Long idusuarioInfo, HefFilialInfo filial) throws Exception {
        salvarFilial(idusuarioInfo, filial);
    }

    @Override
    public byte[] listaEmpresas() throws Exception {
        return Compactar(controlaEmpresaLocal.listaEmpresasInfoLocal());
    }

    @Override
    public byte[] buscaEmpresaUsuario(HefUsuario idusuario) throws Exception {
        return Compactar(controlaEmpresaLocal.buscaEmpresaUsuario(idusuario));
    }

    @Override
    public List<HefFilial> buscaFilialGerenciadasUsuario(HefUsuarioInfo usuInfo) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefFilialInfo> from = query.from(HefFilialInfo.class);
        query.select(from.get(HefFilialInfo_.idfilial));
        query.where(cb.equal(from.get(HefFilialInfo_.idempresa), controlaEmpresaLocal.buscaEmpresaUsuario(usuInfo.getIdusuario())));
        return em.createQuery(query).getResultList();
    }
}
