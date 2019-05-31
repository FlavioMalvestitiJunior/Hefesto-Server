/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email.contatos;

import com.Hefesto.core.entidades.HefContatoEmail;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface local do EJB de controle de Contatos.
 *
 * @author Samuel Rettore
 * @version 1.0
 */
@Local
public interface ControlaContatoEmailLocal {

    public List<HefContatoEmail> getListContatosLocal(Boolean ativo) throws Exception;
}
