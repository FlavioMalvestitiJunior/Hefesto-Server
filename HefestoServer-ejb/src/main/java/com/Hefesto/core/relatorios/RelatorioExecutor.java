/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.relatorios;

import com.Hefesto.core.annotation.EnviaParametroRelatorio;
import com.Hefesto.core.connection.HFEntityMananger;
import com.Hefesto.core.email.ObjetoEmail;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefRelatorio;
import com.Hefesto.core.entidades.HefRelatorioArq;
import com.Hefesto.core.entidades.HefRelatorioDatasourceExcel;
import com.Hefesto.core.paineldecontrole.ControlaFilialLocal;
import com.Hefesto.core.relatorios.objetos.ConstantesStatus;
import com.Hefesto.core.relatorios.objetos.ConverteJavaBean;
import com.Hefesto.core.relatorios.objetos.RelatorioFilaObj;
import com.Hefesto.core.utils.HFLoggerInterceptor;
import com.Hefesto.core.utils.Messages;
import com.hefesto.hefestocomponentes.HFUtils.HFFormatter;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import org.joda.time.LocalDate;

/**
 *
 * @author Flavio
 */
@Stateless(mappedName = "hef/cor/rel/RelatorioExecutor")
@Interceptors({HFLoggerInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RelatorioExecutor extends HFEntityMananger implements RelatorioExecutorLocal {

    @EJB
    private ProcessadorDeRelatorios processadorDeRelatorios;
    @EJB
    private ControlaFilialLocal controlaFilialLocal;

    @PersistenceUnit(unitName = "HefestoReportsPU")
    private EntityManagerFactory factory;

    @Override
    public boolean executa(RelatorioFilaObj obj) throws Exception {

        List<Object> lst;
        List<Object> lstQuery;
        List<Object> lstexcel;
        InputStream in;
        JasperReport jasper;
        DateFormat dfmt;
        Date hoje;
        JasperPrint jasperPrint;
        HefRelatorio rela = null;
        List<String> paramErro = new ArrayList<>();
        EntityManager em1 = factory.createEntityManager();
        try {
            obj.setStatus(ConstantesStatus.PROCESSANDO);
            processadorDeRelatorios.atualizaOBJ(obj);
            if (processadorDeRelatorios.contains(obj)) {
                ConverteJavaBean conversor = null;
                if (obj.isProcessaBean()) {
                    try {
                        Context c = new InitialContext();
                        conversor = (ConverteJavaBean) c.lookup("java:global/HefestoServer-ear-1.0/HefestoServer-ejb-1.0/" + obj.getConversor());
                    } catch (NamingException ne) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
                        throw new RuntimeException(ne);
                    }
                }
                //Cria Objeto e preenche para salvar.
                obj.setStatus(ConstantesStatus.PROCESSANDO);
                LocalDate dtLimpeza = new LocalDate(new Date());
                dtLimpeza = dtLimpeza.plusDays(2);
                rela = new HefRelatorio();
                rela.setDthgeracao(new Date());
                rela.setIdusuario(obj.getUsuario().getIdusuario());
                rela.setTitulo(obj.getTitulo());
                rela.setDthlimpeza(dtLimpeza.toDate());
                rela.setIdexpirar(obj.isIdExpirar());
                rela.setParametros(Compactar(obj.getParametros()));
                rela.setStatus(ConstantesStatus.PROCESSANDO);
                rela.setIdtelas(obj.getTela());
                rela = em.merge(rela);
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                em.clear();
                lstQuery = obj.getQuery() != null ? em.createQuery(obj.getQuery()).getResultList() : new ArrayList<>();
                // conversor JavaBeans
                lst = obj.isProcessaBean() ? conversor.converteLista(lstQuery, obj.getParametros()) : lstQuery;
                if (obj.isNeedsExcelDataSource()) {
                    lstexcel = conversor.excelDataSource(lstQuery, obj.getParametros());
                } else {
                    lstexcel = lst;
                }
                //Trata o relatorio e gera o mesmo.            
                if (obj.getJasper() != null) {
                    jasper = (JasperReport) JRLoader.loadObject(obj.getJasper());
                } else {
                    in = getClass().getResourceAsStream(obj.getRel());
                    jasper = (JasperReport) JRLoader.loadObject(in);
                }
                HefFilialInfo dadosEmpresa = controlaFilialLocal.buscaFilialInfo(obj.getUsuario().getIdfilial());
                //Data do relatorio.
                dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy, EEEE 'as' kk:mm:ss");
                hoje = Calendar.getInstance(Locale.getDefault()).getTime();
                Map<String, Object> params = new HashMap();
                params.put("titulo", rela.getTitulo());
                params.put("nome_empresa", dadosEmpresa.getIdfilial().getDescricao());
                params.put("cnpj_empresa", dadosEmpresa.getCnpj() == null ? ""
                        : HFFormatter.setMaskNumber(dadosEmpresa.getCnpj() + "", HFFormatter.CNPJ_MASK));
                params.put("parametros", "Hefesto RPC, Relatório emitido por " + obj.getUsuario().getNome() + " em " + dfmt.format(hoje));
                params.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em1);
                params.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
                int i = 1;
                for (String obj2 : obj.getSubrel()) {
                    params.put("subrel" + i, getClass().getResource(obj2));
                    paramErro.add("subrel" + i + " = " + getClass().getResource(obj2).toString());
                    i++;
                }

                if (obj.getParametros() != null) {
                    //Verifica parametros ao relatorio
                    for (Map.Entry obj3 : VerificaParametros(obj.getParametros()).entrySet()) {
                        params.put(obj3.getKey().toString(), obj3.getValue());
                        paramErro.add(obj3.getKey().toString() + " = " + obj3.getValue());
                    }
                    //Verifica demais parametros :)
                    for (Map.Entry obj3 : VerificaNaoParametros(obj.getParametros()).entrySet()) {
                        params.put(obj3.getKey().toString(), obj3.getValue());
                        paramErro.add(obj3.getKey().toString() + " = " + obj3.getValue());
                    }
                }
                jasperPrint = JasperFillManager.fillReport(jasper, params, new JRBeanCollectionDataSource(lst));
                em.flush();
                if (obj.getJasper() != null) {
                    obj.getJasper().delete();
                }
                //Atualiza Objeto
                HefRelatorioArq relaArq = new HefRelatorioArq();
                relaArq.setIdrelatorio(rela);
                relaArq.setArquivo(ZipUtil.Compactar(jasperPrint));
                merge(relaArq, obj.getUsuario().getIdusuario().getIdusuario(), "Relatorio/Salvo Arquivo");

                HefRelatorioDatasourceExcel excelDataSource = new HefRelatorioDatasourceExcel();
                excelDataSource.setIdrelatorio(rela);
                excelDataSource.setDatasource(ZipUtil.Compactar(lstexcel));
                merge(excelDataSource, obj.getUsuario().getIdusuario().getIdusuario(), "Relatorio/Salvo Excel Data Source");

                rela.setStatus(ConstantesStatus.CONCLUIDO);
                rela.setNupaginas(BigInteger.valueOf(jasperPrint.getPages().size()));

                rela = em.merge(rela);
                em.flush();

                processadorDeRelatorios.rmRelatorio(obj);
            }
            return true;

        } catch (Exception ex) {
            //Email Erro
            String erro = "Usuário: " + obj.getUsuario().getNome() + "<br/><br/>Parametros :<br/><br/>";
            for (String string : paramErro) {
                erro += string + "<br/>";
            }
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            filaEmail.addEmail(new ObjetoEmail(Messages.getMessage("email.erro", ""),
                    Messages.getMessage("email.remetente"),
                    new StringBuilder(erro + "<br/><br/>" + sw.toString().replace(" at", "<br/>")),
                    "Erro Relatório  : " + obj.getRel()));
            ex.printStackTrace();
            //Grava Relatorio com pobrema
            rela.setStatus(ConstantesStatus.ERRO);
            rela.setNupaginas(BigInteger.valueOf(0L));
            em.merge(rela);
            em.flush();
            if (obj.getJasper() != null) {
                obj.getJasper().delete();
            }
            RegistraLog("ERRO", "Erro ao processar relatorio", ex, obj.getUsuario().getIdusuario().getIdusuario());
            processadorDeRelatorios.rmRelatorio(obj);
            ex.printStackTrace();
            throw ex;
        } finally {
            em.clear();
        }
    }

    /**
     * Verifica quais parametros devem ser enviados ao relatorio.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    private Map<String, Object> VerificaParametros(Object obj) throws Exception {
        Map<String, Object> par = new HashMap<>();
        for (Field fld : obj.getClass().getDeclaredFields()) {
            fld.setAccessible(true);

            if (fld.isAnnotationPresent(EnviaParametroRelatorio.class
            )) {
                par.put(fld.getName(), fld.get(obj));
            }
        }
        return par;
    }

    /**
     * Verifica quais parametros devem ser enviados ao relatorio.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    private Map<String, Object> VerificaNaoParametros(Object obj) throws Exception {
        Map<String, Object> par = new HashMap<>();
        for (Field fld : obj.getClass().getDeclaredFields()) {
            fld.setAccessible(true);

            if (!fld.isAnnotationPresent(EnviaParametroRelatorio.class
            )) {
                par.put(fld.getName(), fld.get(obj));
            }
        }
        return par;
    }
}
