/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.fornecedor;

import com.Hefesto.core.entidades.HefFornecedorInfo;
import java.math.BigInteger;
import javax.ejb.Remote;

/**
 *
 * @author flavio
 */
@Remote
public interface ControlaFornecedorRemote {

    public byte[] buscaFornecedor(String cod, Long idfilial) throws Exception;

    public byte[] listaEmpresas(Long idPerfil) throws Exception;

    public byte[] listaFornecedores(Long idusuarioInfo) throws Exception;

    public byte[] buscaLogradouro(BigInteger cep) throws Exception;

    public void salvar(Long idusuario, HefFornecedorInfo info) throws Exception;

}
