/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefUsuarioInfo;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaUsuarioRemote {

    public byte[] listaUsuarios(HefUsuarioInfo usuario) throws Exception;

    public byte[] listaPerfis(HefUsuarioInfo usuario) throws Exception;

    public byte[] salvarUsuario(Long idUsuario, HefUsuarioInfo usuario) throws Exception;

    public byte[] listaFiliais(HefUsuarioInfo usuario) throws Exception;

}
