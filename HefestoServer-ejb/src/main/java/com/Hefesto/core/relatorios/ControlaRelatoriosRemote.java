/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.relatorios;

import com.Hefesto.core.relatorios.objetos.RelatorioFilaObj;
import java.math.BigInteger;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaRelatoriosRemote {

    public byte[] listaRelatoriosUsuario(Long idusuario) throws Exception;

    public byte[] buscaArquivo(Long idRelatorio) throws Exception;

    public byte[] buscaExcelDataSource(Long idRelatorio) throws Exception;

    public boolean removerRelatorio(BigInteger relatorio, Long idUsuario) throws Exception;

    public byte[] filaRelatorios(Long idUsuario) throws Exception;

    public void teste(RelatorioFilaObj obj) throws Exception;
}
