/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefMenu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flavio
 */
@Local
public interface ControlaMenuLocal {

    public List<HefMenu> listaMenusLocal() throws Exception;
}
