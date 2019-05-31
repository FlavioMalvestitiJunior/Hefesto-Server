/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.connection;

//import HFEntidades.HefLog;
//import HFEntidades.HefUsu;
import com.Hefesto.core.email.ControlaFilaEmail;
import com.Hefesto.core.entidades.HefLog;
import com.Hefesto.core.entidades.HefUsuario;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jr
 */

public class HFEntityMananger {

    @EJB
    protected ControlaFilaEmail filaEmail;

    @PersistenceContext(unitName = "HefestoPU")
    protected EntityManager em;

    private final ZipUtil zip = new ZipUtil();

    /**
     * Faz o merge de um objeto na base de forma generica, entre transações.
     *
     * @param <T> Generics
     * @param object a ser salvo
     * @param usuario que efetou a chamada
     * @param nome da entidade para manter registro no log
     * @return object salvo
     * @throws Exception
     * @since 1.5
     */
    protected <T> T merge(T object, Long usuario, String nome) throws Exception {
        try {
            object = em.merge(object);
            em.flush();
            RegistraLog("Operação", "Salva/Atualiza " + nome, object, usuario);
            return object;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Faz o merge de um objeto na base de forma generica, entre transações.
     *
     * @param <T> Generics
     * @param object a ser salvo
     * @param usuario que efetou a chamada
     * @param nome da entidade para manter registro no log
     * @return object salvo
     * @throws Exception
     * @since 1.5
     */
    protected <T> T remove(T object, Long usuario, String nome) throws Exception {
        try {
            object = em.merge(object);
            em.remove(object);
            em.flush();
            RegistraLog("Operação", "remoção " + nome, object, usuario);
            return object;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Faz uma busca de uma lista usando uma namedQuery
     *
     * @param namedQuery String contendo a namedQuery
     * @return List
     * @throws Exception
     * @since 1.5
     */
    protected List createNamedQuery(String namedQuery) throws Exception {
        List retorno;
        try {
            retorno = em.createNamedQuery(namedQuery).getResultList();
            em.flush();
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Faz uma busca de uma lista usando uma query hql
     *
     * @param query String contendo a query em hql
     * @return List
     * @throws Exception
     * @since 1.5
     */
    protected List createQuery(String query) throws Exception {
        List retorno;
        try {
            //em.getTransaction().begin();
            retorno = em.createQuery(query).getResultList();
            em.flush();
            return retorno;
        } catch (Exception e) {
            //em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            retorno = null;
            em.clear();
        }
    }

    /**
     * Metodo que efetua a gravacao dos logs
     *
     * @param TpLog tipode de log a ser gravado
     * @param desc Descricao do log
     * @param obj Objeto a ser logado por reflection
     * @param usuario Usuario que esta efetuando a acao
     * @since 1.0
     *
     */
    protected void RegistraLog(String TpLog, String desc, Object obj, Long idusuario_log) {
        StringBuilder buf;
        HefLog log;
        HefUsuario usu;
        try {
            buf = new StringBuilder();
            buf.append(desc).append(" \n");
            log = new HefLog();
            if (idusuario_log != null) {
                usu = em.getReference(HefUsuario.class, idusuario_log);
                log.setIdusuario(usu);
            }
            log.setNmentidade(obj.getClass().getName());
            log.setNumip("192.192.192.192");
            log.setDescricao(TrataLog(obj, buf).toString());
            log.setTplog(TpLog);
            log.setDthlog(new Date());
            em.persist(log);
            em.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que fetua o tramento dos logs enviados pelos sistemas .
     *
     * @param obj Object objeto generico a ser tratado.
     * @param buf StringBuilder buffer der retorno para gravar os dados.
     * @return StringBuilder
     * @since 1.0
     */
    private StringBuilder TrataLog(Object obj, StringBuilder buf) throws Exception {
        try {
            Field[] f = obj.getClass().getDeclaredFields();
            buf.append("Classe:").append(obj.getClass().getName()).append(" \n");
            for (Field fd : f) {
                fd.setAccessible(true);
                if (fd.getType() != Collection.class) {
                    buf.append("Campo: ").append(fd.getName()).append(" = ").append(fd.get(obj)).append(" \n");
                }
            }
            f = null;
            return buf;
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw e;
        }
    }

    public byte[] Compactar(Object o) throws Exception {
        return zip.Compactar(o);
    }
}
