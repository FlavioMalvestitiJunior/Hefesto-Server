/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefTelas;
import java.math.BigInteger;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaTelaRemote {

    /**
     * Disponibiliza na interface remota a lista das telas cadastradas.
     *
     * @return
     * @throws Exception
     */
    public byte[] listaTelasCadastradas() throws Exception;

    /**
     * Lista os Menus do Sistema
     *
     * @return
     * @throws Exception
     */
    public byte[] listaMenus() throws Exception;

    /**
     * Salva a tela no Banco de dados
     *
     * @param idusuario
     * @param tela
     * @throws Exception
     */
    public void salvarTela(Long idusuario, HefTelas tela) throws Exception;

    /**
     * Busca o Proximo codigo para a tela.
     *
     * @param idmenu
     * @return
     * @throws java.lang.Exception
     */
    public BigInteger buscaSequencialTela(Long idmenu) throws Exception;

    /**
     * Exclui a Tela Selecionada
     *
     * @param idusuario
     * @param tela
     * @throws java.lang.Exception
     */
    public void removerTela(Long idusuario, HefTelas tela) throws Exception;

}
