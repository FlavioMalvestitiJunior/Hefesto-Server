/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.cron;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.email.EnvioEmail;
import com.Hefesto.core.email.ObjetoEmail;
import com.Hefesto.core.relatorios.ControlaRelatoriosLocal;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import com.Hefesto.core.utils.Messages;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author rettore
 */
@Stateless(mappedName = "ControlaCron")
@LocalBean
@Interceptors({HFLoggerInterceptor.class})
public class ControlaCron extends HFEntityMananger {

    @EJB
    private ControlaRelatoriosLocal controlaRelatoriosLocal;
    @EJB
    private EnvioEmail envioEmail;

    @Schedule(second = "0", minute = "0", hour = "0")
    private void Clean() throws Exception {
        try {
            controlaRelatoriosLocal.clean();
        } catch (Exception e) {
            throw e;
        }
    }

    @Schedule(minute = "*/5", hour = "*", persistent = true)
    private void EnviaEmail() throws Exception {
        try {
            System.out.println("Chama Envio de email");
            envioEmail.enviaEmails();
        } catch (Exception e) {
            filaEmail.addEmail(new ObjetoEmail(Messages.getMessage("email.erro", ""),
                    Messages.getMessage("email.remetente", ""),
                    new StringBuilder(e.getMessage()), "Monitor importacao / Erro Cron"));
            throw e;
        }
    }
}
