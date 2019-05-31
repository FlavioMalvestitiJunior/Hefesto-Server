/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.utils;

/**
 *
 * @author Flavio
 */
public interface EJBConstants {

    /**
     * *****************************************************************************
     * Nucleo do Sistema
     * ****************************************************************************
     */
    //validação Login
    String TEST = "hefesto/core/connection/TesteConnection";
    String CONTROLA_USUARIO = "hef/core/usr/ControlaLoginUsuario";
    // Painel de Controle
    String PAINEL_DE_CONTROLE_TELA = "hef/core/pndc/ControlaTela";
    String PAINEL_DE_CONTROLE_MENU = "hef/core/pndc/ControlaMenu";
    String PAINEL_DE_CONTROLE_PERFIL = "hef/core/pndc/ControlaPerfilUsusario";
    String PAINEL_DE_CONTROLE_FILIAL = "hef/core/pndc/ControlaFilial";
    String PAINEL_DE_CONTROLE_EMPRESA = "hef/core/pndc/ControlaEmpresa";
    String PAINEL_DE_CONTROLE_LIBERACAO_TELA = "hef/core/pndc/ControlaLiberacaoTela";
    String PAINEL_DE_CONTROLE_USUARIO = "hef/core/pndc/ControlaUsuario";
    String PAINEL_DE_CONTROLE_NOTAS = "hef/core/pndc/ControlaNotasDaVersao";
    //Cadastros Gerais
    String CADASTROS_GERAIS_CIDADES = "hef/cad/ger/ControlaCadastroCidade";
    /**
     * *****************************************************************************
     * Relatórios do Sistema
     * ****************************************************************************
     */
    String RELATORIO_CONTROLA = "hef/cor/rel/ControlaRelatorios";
    /**
     * *****************************************************************************
     * Emails
     * ****************************************************************************
     */
    String EMAIL_CONTATO = "hef/cor/email/ControlaContatoEmailBean";
    String EMAIL_ENVIO = "hef/cor/email/ControlaEnvioEmail";
    /**
     * *****************************************************************************
     * Fornecedor
     * ****************************************************************************
     */
    String CONTROLA_FORNECEDOR = "hef/fornec/ControlaFornecedor";
}
