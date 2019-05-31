/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email.contatos;

import com.Hefesto.core.entidades.HefContatoEmail;
import javax.ejb.Remote;

/**
 * Interface Remota do EJB Controla Advogado.
 *
 * @author Samuel Rettore
 * @version 1.0
 */
@Remote
public interface ControlaContatoEmailRemote {

    public byte[] getListContatos(java.lang.Boolean ativo) throws java.lang.Exception;

    public void salvaContatos(HefContatoEmail obj, Long idusuario) throws Exception;
}
