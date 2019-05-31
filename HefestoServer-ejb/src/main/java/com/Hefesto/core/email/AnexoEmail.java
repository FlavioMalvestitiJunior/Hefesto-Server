/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email;

import java.io.Serializable;

/**
 *
 * @author rettore
 */
public class AnexoEmail implements Serializable {

    private String arquivo;
    private byte[] conteudo;

    public AnexoEmail(String arquivo, byte[] conteudo) {
        this.arquivo = arquivo;
        this.conteudo = conteudo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }
}
