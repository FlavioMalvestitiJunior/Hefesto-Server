/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email;

import javax.ejb.Remote;

/**
 *
 * @author rettore
 */
@Remote
public interface ControlaEnvioEmailRemote {

    public byte[] listaContatos(java.lang.Long idusuario) throws java.lang.Exception;
}
