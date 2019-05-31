/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.fornecedor;

import com.Hefesto.cadastro.gerais.ControlaCadastroCidadeLocal;
import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefFornecedor;
import com.Hefesto.core.entidades.HefFornecedorInfo;
import com.Hefesto.core.entidades.HefFornecedorInfo_;
import com.Hefesto.core.entidades.HefFornecedor_;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.paineldecontrole.ControlaEmpresaLocal;
import com.Hefesto.core.paineldecontrole.ControlaFilialLocal;
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
 * @author flavio
 */
@Stateless(mappedName = "hef/fornec/ControlaFornecedor")
@Interceptors({HFLoggerInterceptor.class})
public class ControlaFornecedor extends HFEntityMananger implements ControlaFornecedorRemote {

    @EJB
    ControlaEmpresaLocal controlaEmpresaLocal;
    @EJB
    ControlaFilialLocal controlaFilialLocal;
    @EJB
    ControlaCadastroCidadeLocal controlaCadastroCidadeLocal;

    @Override
    public byte[] buscaFornecedor(String cod, Long idfilial) throws Exception {
        return Compactar(buscaFornecedorPrivate(cod, idfilial));
    }

    private HefFornecedorInfo buscaFornecedorPrivate(String cod, Long idfilial) throws Exception {
        HefFilialInfo fil = controlaFilialLocal.buscaFilialInfoId(idfilial);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefFornecedorInfo> from = query.from(HefFornecedorInfo.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();

        wr.add(cb.equal(from.get(HefFornecedorInfo_.idfornecedor).get(HefFornecedor_.codigoforncedor), cod));
        wr.add(cb.equal(from.get(HefFornecedorInfo_.idativo), true));
        query.where(wr.toArray(new Predicate[]{}));

        List<HefFornecedorInfo> lista = em.createQuery(query).getResultList();
        if (lista.isEmpty()) {
            return new HefFornecedorInfo();
        } else {
            return lista.get(0);
        }

    }

    private List<HefFornecedorInfo> buscaFornecedoresPrivate(Long idfilial,
            HefUsuarioInfo usuInfo) throws Exception {
        HefFilialInfo fil = controlaFilialLocal.buscaFilialInfoId(idfilial);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefFornecedorInfo> from = query.from(HefFornecedorInfo.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();

        if (!usuInfo.getIdperfil().getIdadministrador()) {
            wr.add(cb.equal(from.get(HefFornecedorInfo_.idempresa), fil.getIdempresa()));
        }
        wr.add(cb.equal(from.get(HefFornecedorInfo_.idativo), true));
        query.where(wr.toArray(new Predicate[]{}));

        List<HefFornecedorInfo> lista = em.createQuery(query).getResultList();
        return lista;
    }

    @Override
    public byte[] listaEmpresas(Long idPerfil) throws Exception {
        return Compactar(controlaEmpresaLocal.listaEmpresasLiberadasPerfilLocal(idPerfil));
    }

    @Override
    public byte[] listaFornecedores(Long idusuarioInfo) throws Exception {
        HefUsuarioInfo usuinfo = em.find(HefUsuarioInfo.class, idusuarioInfo);
        return Compactar(buscaFornecedoresPrivate(usuinfo.getIdfilial().getIdfilial(), usuinfo));
    }

    @Override
    public byte[] buscaLogradouro(BigInteger cep) throws Exception {
        return Compactar(controlaCadastroCidadeLocal.buscaCidadeCep(cep));
    }

    @Override
    public void salvar(Long idusuario, HefFornecedorInfo info) throws Exception {
        HefUsuario usu = em.find(HefUsuario.class, idusuario);

        HefFornecedor fornec = merge(info.getIdfornecedor(), idusuario, "Salva/Atualiza");
        info.setIdfornecedor(fornec);

        em.createQuery("UPDATE HefFornecedorInfo i set i.idativo = false WHERE i.idfornecedor=:fornec").
                setParameter("fornec", fornec).executeUpdate();

        info.setIdativo(Boolean.TRUE);
        info.setIdfornecedorInfo(null);
        info.setDthcadastro(new Date());
        info.setIdusuario(usu);

        merge(info, idusuario, "Salva/Atualiza");

    }

}
