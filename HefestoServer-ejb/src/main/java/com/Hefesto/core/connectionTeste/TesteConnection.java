/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.connectionTeste;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author Flavio
 */
@Interceptors({HFLoggerInterceptor.class})
@Stateless(mappedName = "hefesto/core/connection/TesteConnection")
public class TesteConnection extends HFEntityMananger implements TesteConnectionRemote {

    @Override
    public String olaEjb() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery query = cb.createQuery();
//        Root<HefUsuario> from = query.from(HefUsuario.class);
//        query.select(from);
        return "Ola meu tamanho é ";//.createQuery(query).getResultList().size();
    }
}
