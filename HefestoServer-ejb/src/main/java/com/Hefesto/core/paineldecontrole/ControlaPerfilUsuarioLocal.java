/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefPerfil;
import com.Hefesto.core.entidades.HefPerfilEmpresa;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flavio
 */
@Local
public interface ControlaPerfilUsuarioLocal {

    public List<HefPerfilEmpresa> listaPerfisEmpresaLocal(HefUsuarioInfo usuario) throws Exception;

    public List<HefPerfil> listaPerfisLocal(HefUsuarioInfo usuario) throws Exception;
}
