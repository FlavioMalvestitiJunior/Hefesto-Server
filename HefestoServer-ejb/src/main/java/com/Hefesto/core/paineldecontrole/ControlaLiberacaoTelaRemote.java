/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.paineldecontrole;

import com.Hefesto.core.entidades.HefLiberacaoTela;
import com.Hefesto.core.entidades.HefPerfil;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.hefesto.hefestocomponentes.HFPair.HFPair;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Flavio
 */
@Remote
public interface ControlaLiberacaoTelaRemote {

    public byte[] listaPerfis(HefUsuarioInfo usuario) throws Exception;

    public byte[] listaTelasLiberadasPerfil(HefPerfil perfil) throws Exception;

    public byte[] listaTelasDisponiveis(HefUsuarioInfo usuario) throws Exception;

    public void salvarLiberacao(Long idusuario, HFPair<HefPerfil, List<HefLiberacaoTela>> pair) throws Exception;

}
