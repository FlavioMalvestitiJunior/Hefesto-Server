/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.utils;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Flavio
 */
public class HFLoggerInterceptor {

    /**
     * Metodo que intercepta as comunicações dos EJB com o container para fins
     * de monitorar os dados .
     *
     * @param invocationContext InvocationContext Invocation context dos EJB
     * @return Object
     * @throws java.lang.Exception
     * @since 1.0
     */
    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws Exception {
        long timeBefore = System.currentTimeMillis();
        String methodName = invocationContext.getMethod().getName();
        String className = invocationContext.getTarget().getClass().getName();
        String sistema = Messages.getMessage("sistem.nome");
        System.out.println(sistema + " - Chamando Metodo : " + className + "." + methodName);
        try {
            return invocationContext.proceed();
        } catch (Exception e) {
            System.out.println(sistema + " - Erro ao chamar metodo " + className + "." + methodName);
            System.out.println(sistema + " - Causa: " + e.getMessage());
            throw e;
        } finally {
            long timeAfter = System.currentTimeMillis();
            System.out.println(sistema + " - Metodo " + className + "." + methodName + " chamado em "
                    + (timeAfter - timeBefore) + " ms");
        }
    }
}
