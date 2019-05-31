/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email.contatos;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefContatoEmail;
import com.Hefesto.core.entidades.HefContatoEmail_;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * EJB que efetua o controle de Contatos
 *
 * @author Samuel Rettore
 * @version 1.2 Adaptado para funcionar com o Hefesto
 * @author Flavio
 */
@Interceptors({HFLoggerInterceptor.class})
@Stateless(mappedName = "hef/cor/email/ControlaContatoEmailBean")
public class ControlaContatoEmailBean extends HFEntityMananger implements ControlaContatoEmailRemote, ControlaContatoEmailLocal {

    /**
     * Metodo que busca uma Contatos.
     *
     * @return List<HefContatoEmail> lista de contos.
     * @since 1.0
     */
    @Override
    public List<HefContatoEmail> getListContatosLocal(Boolean ativo) throws Exception {
        return getListContatosPrivate(ativo);
    }

    /**
     * Metodo que busca uma Contatos.
     *
     * @return List<HefContatoEmail> lista de contos.
     * @since 1.0
     */
    private List<HefContatoEmail> getListContatosPrivate(Boolean ativo) throws Exception {
        List<HefContatoEmail> listContato;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery query = cb.createQuery();
            Root<HefContatoEmail> from = query.from(HefContatoEmail.class);
            query.select(from);
            if (ativo) {
                query.where(cb.equal(from.get(HefContatoEmail_.idativo), true));
            }
            listContato = em.createQuery(query).getResultList();
            return listContato;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public byte[] getListContatos(Boolean ativo) throws Exception {
        return Compactar(getListContatosPrivate(ativo));
    }

    /**
     * Metodo que salva/atualiza Contatos
     *
     * @param obj
     * @param adv HefContatoEmail objeto a ser salvo
     * @param usu HefUsuario Objeto do usuario que efetuou a operacao
     * @throws java.lang.Exception
     * @since 1.0
     */
    @Override
    public void salvaContatos(HefContatoEmail obj, Long idusuario) throws Exception {
        HefUsuario usuario;
        try {
            usuario = em.find(HefUsuario.class, idusuario);
            if (obj.getIdcontato() == null) {
                obj.setDthcadasdtro(new Date());
            }
            obj.setIdusuario(usuario);
            obj = em.merge(obj);
            em.flush();
            RegistraLog("OPERACAO", "Salva/Atualiza Contos", obj, idusuario);
        } catch (Exception e) {
            throw e;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
}
