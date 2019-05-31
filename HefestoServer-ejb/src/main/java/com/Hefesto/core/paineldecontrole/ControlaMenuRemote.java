/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefMenu;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaMenuRemote {

    public byte[] listaMenus() throws Exception;

    public void salvarMenu(Long idusuario, HefMenu menu) throws Exception;

    public void removerMenu(Long idusuario, HefMenu menu) throws Exception;

}
