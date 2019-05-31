/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email;

import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.email.contatos.ControlaContatoEmailLocal;
import com.Hefesto.core.entidades.HefContatoEmail;
import com.Hefesto.core.entidades.HefEmail;
import com.Hefesto.core.entidades.HefEmailAnexo;
import com.Hefesto.core.entidades.HefEmailContato;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.paineldecontrole.ControlaUsuarioLocal;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import com.Hefesto.core.utils.Messages;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * EJB que efetua o controle de Envio de Emails
 *
 * @author Samuel Rettore
 * @version 1.0 Adapatado para funcionar com o Hefesto.
 */
@Stateless(mappedName = "hef/cor/email/ControlaEnvioEmail")
@Interceptors({HFLoggerInterceptor.class})
public class ControlaEnvioEmail extends HFEntityMananger implements ControlaEnvioEmailLocal, ControlaEnvioEmailRemote {

    @EJB
    private ControlaContatoEmailLocal controlaContatoEmailLocal;
    @EJB
    ControlaUsuarioLocal controlaUsuarioLocal;

    /**
     * Lista de contatos por usuario.
     *
     * @param idusuario
     * @return
     * @throws Exception
     */
    @Override
    public byte[] listaContatos(Long idusuario) throws Exception {
        return Compactar(controlaContatoEmailLocal.getListContatosLocal(Boolean.TRUE));
    }

    /**
     * Metodo que salva/atualiza Emails Mandar os anexos junto com os email para
     * salvar
     *
     * @param obj Srhemail objeto a ser salvo
     * @param usu Srhusuario Objeto do usuario que efetuou a operacao
     * @throws java.lang.Exception
     * @since 1.0
     */
    private void salvaEmail(HefEmail obj, Long idusuario, List<Long> contatos) throws Exception {
        HefUsuarioInfo usuinfo = controlaUsuarioLocal.buscaInfoAtivo(idusuario);
        HefEmailAnexo anexo;
        HefEmailContato cont;
        try {
            //Contatos
            obj.setHefEmailContatoCollection(new ArrayList<>());
            for (Long objc : contatos) {
                cont = new HefEmailContato();
                cont.setIdcontatoEmail(em.getReference(HefContatoEmail.class, objc));
                cont.setIdemail(obj);
                obj.getHefEmailContatoCollection().add(cont);
            }

            obj.setCorpoemail(geraHeader(geraAssinatura(obj.getCorpoemail(), usuinfo)));
            obj.setIdusuario(usuinfo.getIdusuario());
            obj.setDthemail(new Date());
            obj = em.merge(obj);
            em.flush();
            RegistraLog("OPERACAO", "Salva/Atualiza Email/Envio", obj, idusuario);
        } catch (Exception e) {
            throw e;
        } finally {
            obj = null;
            usuinfo = null;
            idusuario = null;
            em.clear();
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private String geraAssinatura(String corpoemail, HefUsuarioInfo usu) throws Exception {
        String Assinatura;
        Properties props = new Properties();
        try {
            Assinatura = "<br/><br/><b>" + usu.getNome() + "</b><br/>";
            Assinatura += "<b>" + usu.getIdfilial().getDescricao() + "</b><br/>";
            Assinatura += "E-mail: " + usu.getEmail() + "<br/>";
            Assinatura += "Telefone: " + usu.getTelefone() + "<br/></html>";
            return corpoemail + "<html><br/><br/>" + Assinatura;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String geraHeader(String corpoemail) throws Exception {
        String header;
        try {
            header = Messages.getMessage("sistem.nome") + " - Email <br/>";
            return header + corpoemail;
        } catch (Exception e) {
            throw e;
        }
    }
}
