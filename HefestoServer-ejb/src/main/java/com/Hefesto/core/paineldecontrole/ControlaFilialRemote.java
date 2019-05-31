/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import java.math.BigInteger;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaFilialRemote {

    public byte[] listaFiliais(HefUsuarioInfo usuario) throws Exception;

    public void salvarFilial(Long idusuario, HefFilialInfo filial) throws Exception;

    public byte[] buscaLogradouro(BigInteger cep) throws Exception;

    public byte[] listaEmpresas() throws Exception;

    public byte[] buscaEmpresaUsuario(HefUsuario idusuario) throws Exception;

}
