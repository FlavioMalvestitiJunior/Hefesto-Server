/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.entidades.HefEmail;
import com.Hefesto.core.entidades.HefEmailAnexo;
import com.Hefesto.core.entidades.HefEmailContato;
import com.Hefesto.core.entidades.HefEmail_;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.paineldecontrole.ControlaUsuarioLocal;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rettore
 */
@Stateless(mappedName = "EnvioEmail")
@LocalBean
@Interceptors({HFLoggerInterceptor.class})
public class EnvioEmail extends HFEntityMananger {

    @EJB
    ControlaUsuarioLocal controlaUsuarioLocal;

    public void enviaEmails() throws Exception {
        List<HefEmail> lst;
        ObjetoEmail eml = null;
        List<Long> lt;
        try {
            filaEmail.verificaRetornos();
            lt = filaEmail.listaDadosFila();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery query = cb.createQuery();
            Root<HefEmail> from = query.from(HefEmail.class);
            query.select(from);
            List<Predicate> condicoes = new ArrayList<>();
            condicoes.add(cb.or(cb.notEqual(from.get(HefEmail_.idenviado), true), cb.isNull(from.get(HefEmail_.idenviado))));
            if (lt.size() > 0) {
                condicoes.add(from.get(HefEmail_.idemail).in(lt).not());
            }
            query.where(condicoes.toArray(new Predicate[]{}));
            lst = em.createQuery(query).getResultList();
            for (HefEmail email : lst) {
                for (HefEmailContato obj : email.getHefEmailContatoCollection()) {
                    if (eml == null) {
                        HefUsuarioInfo usuinfo = controlaUsuarioLocal.buscaInfoAtivo(email.getIdusuario().getIdusuario());
                        eml = new ObjetoEmail(obj.getIdcontatoEmail().getEmail(),
                                usuinfo.getEmail(),
                                new StringBuilder(email.getCorpoemail()), email.getAssunto());
                        eml.setIdemail(email.getIdemail());
                    } else {
                        eml = eml.addCopiaPara(obj.getIdcontatoEmail().getEmail());
                    }
                }
                for (HefEmailAnexo obja : email.getHefEmailAnexoCollection()) {
                    eml = eml.addAnexo(new AnexoEmail(obja.getNomeArquivo(), obja.getArquivo()));
                }
                filaEmail.addEmail(eml);
                eml = null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
