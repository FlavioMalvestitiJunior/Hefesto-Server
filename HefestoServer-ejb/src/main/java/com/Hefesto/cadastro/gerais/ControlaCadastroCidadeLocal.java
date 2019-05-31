/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.cadastro.gerais;

import com.Hefesto.core.entidades.HefLogradouro;
import java.math.BigInteger;
import javax.ejb.Local;

/**
 *
 * @author Flavio
 */
@Local
public interface ControlaCadastroCidadeLocal {

    public HefLogradouro buscaCidadeCep(BigInteger cep) throws Exception;

}
