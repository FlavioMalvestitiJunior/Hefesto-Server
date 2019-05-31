/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.relatorios;

import com.Hefesto.core.relatorios.objetos.RelatorioFilaObj;
import javax.ejb.Local;

/**
 *
 * @author Flavio
 */
@Local
public interface RelatorioExecutorLocal {

    public boolean executa(RelatorioFilaObj obj) throws Exception;

}
