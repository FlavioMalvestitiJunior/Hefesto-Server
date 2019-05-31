/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.cadastro.gerais;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefCidade;
import com.Hefesto.core.entidades.HefCidade_;
import com.Hefesto.core.entidades.HefLogradouro;
import com.Hefesto.core.entidades.HefLogradouro_;
import com.Hefesto.core.entidades.HefPais;
import com.Hefesto.core.entidades.HefUf;
import com.Hefesto.core.entidades.HefUf_;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.math.BigInteger;
import java.util.ArrayList;
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
@Stateless(mappedName = "hef/cad/ger/ControlaCadastroCidade")
@Interceptors({HFLoggerInterceptor.class})
public class ControlaCadastroCidade extends HFEntityMananger implements ControlaCadastroCidadeRemote, ControlaCadastroCidadeLocal {

    /**
     * Lista os Paises Cadastrados.
     *
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaPaises() throws Exception {
        return Compactar(buscaPaisPrivate());
    }

    /**
     * Busca o pais conforme os parametros passados.
     *
     * @return
     */
    private List<HefPais> buscaPaisPrivate() throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefPais> from = query.from(HefPais.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }

    /**
     * Salva o Pais
     *
     * @param idUsuario
     * @param pais
     * @throws Exception
     */
    @Override
    public void salvaPais(Long idUsuario, HefPais pais) throws Exception {
        merge(pais, idUsuario, "Salva/Atualiza");
    }

    /**
     * Lista as Ufs do Pais.
     *
     * @param pais
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaUF(HefPais pais) throws Exception {
        return Compactar(buscaUfPrivate(pais));
    }

    /**
     * Carrega as Ufs conforme os parametros.
     *
     * @param pais
     * @return
     */
    private List<HefUf> buscaUfPrivate(HefPais pais) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefUf> from = query.from(HefUf.class);
        query.select(from);
        query.where(cb.equal(from.get(HefUf_.idpais), pais));
        return em.createQuery(query).getResultList();
    }

    /**
     * Salva o UF
     *
     * @throws Exception
     */
    @Override
    public void salvaUF(Long idUsuario, HefUf uf) throws Exception {
        merge(uf, idUsuario, "Salva/Atualiza");
    }

    /**
     * lista todas as cidades do Estado
     *
     * @param uf
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaCidades(HefUf uf) throws Exception {
        return Compactar(buscaCidadePrivate(uf));
    }

    /**
     * Busca as Cidades do Estado
     *
     * @param uf
     * @return
     * @throws Exception
     */
    private List<HefCidade> buscaCidadePrivate(HefUf uf) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefCidade> from = query.from(HefCidade.class);
        query.select(from);
        query.where(cb.equal(from.get(HefCidade_.iduf), uf));
        return em.createQuery(query).getResultList();
    }

    /**
     * Busca os Logradouros da CIdade
     *
     * @param cid
     * @return
     * @throws Exception
     */
    private List<HefLogradouro> buscaLogradouroPrivate(HefCidade cid, BigInteger cep) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<HefLogradouro> from = query.from(HefLogradouro.class);
        query.select(from);
        List<Predicate> wr = new ArrayList<>();

        if (cid != null) {
            wr.add(cb.equal(from.get(HefLogradouro_.idcidade), cid));
        }
        if (cep != null) {
            wr.add(cb.equal(from.get(HefLogradouro_.cep), cep));
        }

        query.where(wr.toArray(new Predicate[]{}));
        return em.createQuery(query).getResultList();
    }

    /**
     * Salva a Cidade.
     *
     * @param idUsuario
     * @param cidade
     * @throws Exception
     */
    @Override
    public void salvaCidade(Long idUsuario, HefCidade cidade) throws Exception {
        merge(cidade, idUsuario, "Salva/Atualiza");
    }

    /**
     * Salva o Logradouro
     *
     * @param idusuario
     * @param log
     * @throws Exception
     */
    @Override
    public void salvaLogradouro(Long idusuario, HefLogradouro log) throws Exception {
        merge(log, idusuario, "Salva/Atualiza");
    }

    /**
     * Lista os Logradouros da Cidade
     *
     * @param cid
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaLogradouros(HefCidade cid) throws Exception {
        return Compactar(buscaLogradouroPrivate(cid, null));
    }

    @Override
    public HefLogradouro buscaCidadeCep(BigInteger cep) throws Exception {
        List<HefLogradouro> logs = buscaLogradouroPrivate(null, cep);
        if (logs.isEmpty()) {
            return null;
        } else {
            return logs.get(0);
        }
    }

}
