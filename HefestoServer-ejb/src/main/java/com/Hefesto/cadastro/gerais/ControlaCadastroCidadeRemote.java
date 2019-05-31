/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.cadastro.gerais;

import com.Hefesto.core.entidades.HefCidade;
import com.Hefesto.core.entidades.HefLogradouro;
import com.Hefesto.core.entidades.HefPais;
import com.Hefesto.core.entidades.HefUf;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaCadastroCidadeRemote {

    /**
     * Lista todos os Paises Cadastrados.
     *
     * @return
     * @throws Exception
     */
    public byte[] listaPaises() throws Exception;

    /**
     * Salva o Pais
     *
     * @param idUsuario
     * @param pais
     * @throws Exception
     */
    public void salvaPais(Long idUsuario, HefPais pais) throws Exception;

    /**
     * Carrega as Ufs do País
     *
     * @param pais
     * @return
     * @throws Exception
     */
    public byte[] listaUF(HefPais pais) throws Exception;

    /**
     * Salva a UF
     *
     * @param idUsuario
     * @param uf
     * @throws Exception
     */
    public void salvaUF(Long idUsuario, HefUf uf) throws Exception;

    /**
     * Lista todas as Cidades do Estado
     *
     * @param uf
     * @return
     * @throws Exception
     */
    public byte[] listaCidades(HefUf uf) throws Exception;

    /**
     * Salva a Cidade
     *
     * @param idUsuario
     * @param cidade
     * @throws Exception
     */
    public void salvaCidade(Long idUsuario, HefCidade cidade) throws Exception;

    /**
     * Salva o Logradouro
     *
     * @param idusuario
     * @param log
     * @throws Exception
     */
    public void salvaLogradouro(Long idusuario, HefLogradouro log) throws Exception;

    /**
     * Lista os Logradouros da Cidade
     *
     * @param cid
     * @return
     * @throws Exception
     */
    public byte[] listaLogradouros(HefCidade cid) throws Exception;
}
