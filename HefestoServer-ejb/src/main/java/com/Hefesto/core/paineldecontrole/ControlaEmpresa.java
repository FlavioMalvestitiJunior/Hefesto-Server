/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.cadastro.gerais.ControlaCadastroCidadeLocal;
import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefEmpresa;
import com.Hefesto.core.entidades.HefEmpresaInfo;
import com.Hefesto.core.entidades.HefEmpresaInfo_;
import com.Hefesto.core.entidades.HefEmpresa_;
import com.Hefesto.core.entidades.HefFilial;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefFilialInfo_;
import com.Hefesto.core.entidades.HefFilial_;
import com.Hefesto.core.entidades.HefPerfil;
import com.Hefesto.core.entidades.HefPerfilEmpresa;
import com.Hefesto.core.entidades.HefPerfilEmpresa_;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.entidades.HefUsuarioInfo_;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Flavio
 */
@Interceptors({HFLoggerInterceptor.class})
@Stateless(mappedName = "hef/core/pndc/ControlaEmpresa")
public class ControlaEmpresa extends HFEntityMananger implements ControlaEmpresaLocal, ControlaEmpresaRemote {

    @EJB
    ControlaCadastroCidadeLocal controlaCadastroCidadeLocal;
    @EJB
    ControlaFilialLocal controlaFilialLocal;

    @Override
    public HefEmpresa buscaEmpresaUsuario(HefUsuario usu) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefEmpresa> from = query.from(HefEmpresa.class);
        Join<HefEmpresa, HefFilialInfo> joinFilialInfo = from.join(HefEmpresa_.hefFilialInfoCollection);
        Join<HefFilialInfo, HefFilial> joinFilial = joinFilialInfo.join(HefFilialInfo_.idfilial);
        Join<HefFilial, HefUsuarioInfo> joinUsuario = joinFilial.join(HefFilial_.hefUsuarioInfoCollection);
        query.select(from);
        query.where(cb.equal(joinUsuario.get(HefUsuarioInfo_.idativo), true),
                cb.equal(joinFilialInfo.get(HefFilialInfo_.idativo), true),
                cb.equal(joinUsuario.get(HefUsuarioInfo_.idusuario), usu));

        return (HefEmpresa) em.createQuery(query).getSingleResult();
    }

    @Override
    public List<HefEmpresaInfo> listaEmpresasInfoLocal() throws Exception {
        return buscaEmpresaPrivate(null);
    }

    @Override
    public byte[] listaEmpresas() throws Exception {
        return Compactar(buscaEmpresaPrivate(null));
    }

    @Override
    public void salvarEmpresa(Long idusuarioInfo, HefEmpresaInfo empresa) throws Exception {
        List<HefEmpresaInfo> empres = buscaEmpresaPrivate(empresa.getCodigo());
        List<HefFilialInfo> filiais = new ArrayList<>();
        if (!empres.isEmpty()) {
            if (empresa.getIdempresa().getIdempresa() == null
                    || (!empres.get(0).getIdempresa().getIdempresa().equals(empresa.getIdempresa().getIdempresa()))) {
                throw new Exception(Messages.getMessage("empresa.erro.codigo"));
            }
        }
        HefUsuarioInfo usuario = em.find(HefUsuarioInfo.class, idusuarioInfo);
        if (empresa.getIdempresa().getIdempresa() != null) {
            em.createQuery("UPDATE HefEmpresaInfo i SET i.idativo = false where i.idempresa=:idempresa")
                    .setParameter("idempresa", empresa.getIdempresa()).executeUpdate();
            filiais = controlaFilialLocal.buscaFiliaisEmpresa(empresa.getIdempresa());
        }
        HefEmpresa emp = merge(empresa.getIdempresa(), usuario.getIdusuario().getIdusuario(), "Salvar/Atualizar");
        empresa.setIdempresaInfo(null);
        empresa.setDthcadastro(new Date());
        empresa.setIdempresa(emp);
        empresa.setIdativo(Boolean.TRUE);
        empresa.setIdusuario(usuario.getIdusuario());
        merge(empresa, usuario.getIdusuario().getIdusuario(), "Salvar/Atualizar");

        if (empresa.getIdcancelada()) {
            for (HefFilialInfo fils : filiais) {
                fils.setIdcancelada(empresa.getIdcancelada());
                em.detach(fils);
                controlaFilialLocal.salvaFilialLocal(usuario.getIdusuarioInfo(), fils);
            }
        }
    }

    @Override
    public byte[] buscaLogradouro(BigInteger cep) throws Exception {
        return Compactar(controlaCadastroCidadeLocal.buscaCidadeCep(cep));
    }

    private List<HefEmpresaInfo> buscaEmpresaPrivate(String Codigo) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefEmpresaInfo> from = query.from(HefEmpresaInfo.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();
        if (Codigo != null) {
            wr.add(cb.equal(from.get(HefEmpresaInfo_.codigo), Codigo));
        }
        wr.add(cb.equal(from.get(HefEmpresaInfo_.idativo), true));
        query.where(wr.toArray(new Predicate[]{}));
        return em.createQuery(query).getResultList();
    }

    public List<HefEmpresaInfo> listaEmpresasLiberadasPerfilLocal(Long idPerfilUsuario) throws Exception {
        return listaEmpresasLiberadasPerfilPrivate(idPerfilUsuario);
    }

    private List<HefEmpresaInfo> listaEmpresasLiberadasPerfilPrivate(Long idPerfilUsuario) throws Exception {
        HefPerfil perfil = em.find(HefPerfil.class, idPerfilUsuario);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefEmpresaInfo> from = query.from(HefEmpresaInfo.class);
        Join<HefEmpresaInfo, HefEmpresa> join1 = from.join(HefEmpresaInfo_.idempresa);
        Join<HefEmpresa, HefPerfilEmpresa> join2 = join1.join(HefEmpresa_.hefPerfilEmpresaCollection);
        query.select(from);
        query.distinct(true);
        List<Predicate> wr = new ArrayList<>();

        if (!perfil.getIdadministrador()) {
            wr.add(cb.equal(from.get(HefEmpresaInfo_.idativo), true));
            wr.add(cb.equal(from.get(HefEmpresaInfo_.idcancelada), false));
            wr.add(cb.equal(join2.get(HefPerfilEmpresa_.idperfil), perfil));
            wr.add(cb.equal(join2.get(HefPerfilEmpresa_.idativo), true));
        } else {
            wr.add(cb.equal(from.get(HefEmpresaInfo_.idativo), true));
            wr.add(cb.equal(from.get(HefEmpresaInfo_.idcancelada), false));
            wr.add(cb.equal(join2.get(HefPerfilEmpresa_.idativo), true));
        }
        query.where(wr.toArray(new Predicate[]{}));
        return em.createQuery(query).getResultList();
    }

}
