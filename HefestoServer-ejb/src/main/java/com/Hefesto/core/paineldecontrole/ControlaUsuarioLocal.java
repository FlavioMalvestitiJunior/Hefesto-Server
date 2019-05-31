/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flavio
 */
@Local
public interface ControlaUsuarioLocal {

    /**
     * Lista os Funcionarios da Filial
     *
     * @param usuario
     * @param filial
     * @return @throws Exception
     */
    public List<HefUsuarioInfo> listaUsuariosFilial(HefUsuarioInfo usuario, HefFilialInfo filial) throws Exception;

    /**
     * Salva o Usuario
     *
     * @param idUsuario
     * @param usuario
     * @throws Exception
     */
    public void salvarUsuarioLocal(Long idUsuario, HefUsuarioInfo usuario) throws Exception;

    /**
     * Busca a Informação ativa do Usuario
     *
     * @param idusuario
     * @return
     */
    public HefUsuarioInfo buscaInfoAtivo(Long idusuario);
}
