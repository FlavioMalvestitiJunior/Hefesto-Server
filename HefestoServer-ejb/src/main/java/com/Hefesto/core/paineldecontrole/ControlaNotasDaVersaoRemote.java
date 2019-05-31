/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefNotas;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaNotasDaVersaoRemote {

    public byte[] buscaNotaDaVersao(String message) throws Exception;

    public byte[] listaNotaDaVersao() throws Exception;

    public void salvarNota(HefNotas notas, Long idUsuario) throws Exception;

    public byte[] buscaUltimaNota() throws Exception;

}
