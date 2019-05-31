/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

/**
 *
 * @author rettore
 * @author Flavio
 *
 * Classe Original SrhCredi, Adaptado para funcionar com Hefesto
 */
@Singleton(name = "ControlaFilaEmail")
@LocalBean
@Interceptors({HFLoggerInterceptor.class})
public class ControlaFilaEmail extends HFEntityMananger {

    private ExecutorService envioPoll = Executors.newFixedThreadPool(1);
    private List<dadoEnviado> retorno = new ArrayList<>();

    public synchronized void addEmail(ObjetoEmail envioPoll) throws Exception {
        try {
            System.out.println("Adiciona email para " + envioPoll.getEndPara() + " Id mensagem = " + envioPoll.getIdemail());
            //System.out.println("Envio de email retorno = " + f.get());
            if (envioPoll.getIdemail().compareTo(0L) != 0) {
                if (!verificaSeExiste(envioPoll.getIdemail())) {
                    retorno.add(new dadoEnviado(this.envioPoll.submit(envioPoll), envioPoll.getIdemail()));
                }
            } else {
                this.envioPoll.submit(envioPoll);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public synchronized void verificaRetornos() throws Exception {
        dadoEnviado obj;
        List<dadoEnviado> retorno2 = new ArrayList<dadoEnviado>();
        try {
            retorno2.addAll(retorno);
            for (Iterator it = retorno2.iterator(); it.hasNext();) {
                obj = (dadoEnviado) it.next();
                System.out.println("Verificando objeto email = " + obj.getIdemail());
                if (obj.getDadoEnvio().isDone()) {
                    if (obj.getIdemail().compareTo(0L) != 0) {
                        System.out.println("Email " + obj.getIdemail() + ", status retorno =" + obj.getDadoEnvio().get());
                        em.createQuery("update HefEmail e set e.dthenviado=:data, idenviado='S' where e.idemail:idemail").
                                setParameter("idemail", obj.getIdemail()).
                                setParameter("data", new Date()).
                                executeUpdate();
                        retorno.remove(obj);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private boolean verificaSeExiste(Long id) throws Exception {
        Boolean ret = false;
        try {
            for (dadoEnviado obj : retorno) {
                if (obj.getIdemail().compareTo(id) == 0) {
                    ret = true;
                }
            }
            return ret;
        } catch (Exception e) {
            throw e;
        }
    }

    public synchronized List<Long> listaDadosFila() throws Exception {
        List<Long> list = new ArrayList<Long>();
        try {
            for (dadoEnviado obj : retorno) {
                list.add(obj.idemail);
            }
            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    class dadoEnviado {

        private Future<Boolean> dadoEnvio;
        private Long idemail;

        public dadoEnviado(Future<Boolean> dadoEnvio, Long idemail) {
            this.dadoEnvio = dadoEnvio;
            this.idemail = idemail;
        }

        public Future<Boolean> getDadoEnvio() {
            return dadoEnvio;
        }

        public void setDadoEnvio(Future<Boolean> dadoEnvio) {
            this.dadoEnvio = dadoEnvio;
        }

        public Long getIdemail() {
            return idemail;
        }

        public void setIdemail(Long idemail) {
            this.idemail = idemail;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
