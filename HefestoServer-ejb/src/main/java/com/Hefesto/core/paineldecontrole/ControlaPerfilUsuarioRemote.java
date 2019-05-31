/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefPerfilEmpresa;
import com.Hefesto.core.entidades.HefUsuario;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaPerfilUsuarioRemote {

    public byte[] listaPerfis(HefUsuarioInfo usuario) throws Exception;

    public void salvarPerfilEmpresa(Long idusuario, HefPerfilEmpresa perfilEmpresa) throws Exception;

    public void removerPerfilEmpresa(Long idusuario, HefPerfilEmpresa perfilEmpresa) throws Exception;

    public byte[] listaFiliais() throws Exception;

    public byte[] buscaEmpresaUsuario(HefUsuario idusuario) throws Exception;

    public byte[] listaEmpresas() throws Exception;

}
