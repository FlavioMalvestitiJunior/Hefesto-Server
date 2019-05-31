/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefEmpresa;
import com.Hefesto.core.entidades.HefFilial;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flavio
 */
@Local
public interface ControlaFilialLocal {

    public List<HefFilialInfo> ListaFiliaisLocal() throws Exception;

    public List<HefFilialInfo> ListaFiliaisUsuarioLocal(HefUsuarioInfo usuario) throws Exception;

    public HefFilialInfo buscaFilialInfo(HefFilial idfilial) throws Exception;

    public HefFilialInfo buscaFilialInfoId(Long idfilial) throws Exception;

    public List<HefFilialInfo> buscaFiliaisEmpresa(HefEmpresa empresa) throws Exception;

    public void salvaFilialLocal(Long idusuarioInfo, HefFilialInfo filial) throws Exception;

    public List<HefFilial> buscaFilialGerenciadasUsuario(HefUsuarioInfo usuInfo) throws Exception;
}
