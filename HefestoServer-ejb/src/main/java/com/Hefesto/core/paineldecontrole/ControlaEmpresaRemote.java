/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefEmpresaInfo;
import java.math.BigInteger;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaEmpresaRemote {

    public byte[] listaEmpresas() throws Exception;

    public void salvarEmpresa(Long idusuarioInfo, HefEmpresaInfo empresa) throws Exception;

    public byte[] buscaLogradouro(BigInteger cep) throws Exception;
}
