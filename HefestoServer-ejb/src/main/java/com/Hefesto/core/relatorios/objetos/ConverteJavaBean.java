/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.relatorios.objetos;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que for�a a convers�o de uma List retornada pelo Relatorio em um
 * objeto JavaBean processavel Ele deve ser um EJB Local
 *
 * @author Rafael Felix
 * @author Flavio Malvestiti
 * @param <T>
 */
public interface ConverteJavaBean<T> extends Serializable {

    /**
     * Metodo que faz a convers�o.
     *
     * @param list vinda do relatorio
     * @param parametro
     * @return Lista convertida
     */
    public List<T> converteLista(List<Object> list, Object parametro);

    /**
     * retorna a lista para usar na exportação para excel.
     *
     * @param parametro
     * @return
     */
    public List<T> excelDataSource(List<Object> list, Object parametro);
}
