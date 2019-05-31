/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefEmpresa;
import com.Hefesto.core.entidades.HefEmpresaInfo;
import com.Hefesto.core.entidades.HefUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flavio
 */
@Local
public interface ControlaEmpresaLocal {

    public HefEmpresa buscaEmpresaUsuario(HefUsuario usu) throws Exception;

    public List<HefEmpresaInfo> listaEmpresasInfoLocal() throws Exception;

    public List<HefEmpresaInfo> listaEmpresasLiberadasPerfilLocal(Long idPerfilUsuario) throws Exception;
}
