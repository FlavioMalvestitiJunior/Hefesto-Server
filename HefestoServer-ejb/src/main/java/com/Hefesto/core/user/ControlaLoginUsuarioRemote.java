/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.user;

import com.Hefesto.core.entidades.HefUsuarioInfo;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaLoginUsuarioRemote {

    /**
     * verifica se o usuario esta cadastrado caso não esteja retorna null
     *
     * @param login
     * @param senha
     * @return
     * @throws Exception
     */
    public HefUsuarioInfo validaLogin(String login, String senha) throws Exception;

    public byte[] getTelasUsuario(Long idUduario, Boolean telasMenu) throws Exception;

    public void salvaPreferencia(HefUsuarioInfo usuario) throws Exception;

}
