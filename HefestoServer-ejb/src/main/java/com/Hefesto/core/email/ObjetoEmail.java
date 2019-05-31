/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hefesto.core.email;

import com.Hefesto.core.utils.Messages;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author rettore
 * @author Flavio
 *
 * Adaptado para funcionar com Hefesto
 */
public class ObjetoEmail implements Callable<Boolean> {

    private Long idemail = 0L;
    private StringBuilder mensagem = new StringBuilder();
    private String EndPara;
    private List<String> CopiaPara = new ArrayList<>();
    private List<String> CopiaOPara = new ArrayList<>();
    private String Endde;
    private String assunto;
    private List<AnexoEmail> anexos = new ArrayList<>();

    public Long getIdemail() {
        return idemail;
    }

    public void setIdemail(Long idemail) {
        this.idemail = idemail;
    }

    public String getEndPara() {
        return EndPara;
    }

    public ObjetoEmail(String para, String de, StringBuilder msg, String assunto) {
        this.EndPara = para;
        this.Endde = de;
        this.mensagem = msg;
        this.assunto = assunto;
        this.anexos.addAll(anexos);
    }

    public ObjetoEmail addCopiaOculta(String cco) {
        if (!CopiaOPara.contains(cco)) {
            CopiaOPara.add(cco);
        }
        return this;
    }

    public ObjetoEmail addCopiaPara(String cc) {
        if (!CopiaPara.contains(cc)) {
            CopiaPara.add(cc);
        }
        return this;
    }

    public ObjetoEmail addAnexo(AnexoEmail anx) {
        anexos.add(anx);
        return this;
    }

    @Override
    public Boolean call() throws Exception {
        Properties prop;
        Authenticator auth;
        Session session;
        MimeMessage msg;
        MimeBodyPart anexo, corpo;
        Multipart mp = new MimeMultipart();
        try {
            System.out.println("Enviando email");
            prop = new Properties();
            //Serhmatica
            prop.put("mail.smtp.host", Messages.getMessage("email.auth.smtp"));
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.auth", "true");

            session = Session.getInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Messages.getMessage("email.auth.user"), Messages.getMessage("email.auth.password"));
                }
            });
            msg = new MimeMessage(session);
            msg.setHeader("Disposition-Notification-To", Endde);
            //Corpo mensagem
            corpo = new MimeBodyPart();
            //corpo.setText(mensagem.toString());
            corpo.setContent(mensagem.toString(), "text/html; charset=ISO-8859-1; format=flowed");
            mp.addBodyPart(corpo);

            //Anexos
            for (AnexoEmail obj : anexos) {
                System.out.println("Anexando arquivo");
                anexo = new MimeBodyPart();
                try {
                    anexo.setDataHandler(new DataHandler(new FileDataSource(new File(SalvaArquivoTemporario(obj.getArquivo(), ZipUtil.Descompacta(obj.getConteudo()))))));
                } catch (ZipException e) {
                    anexo.setDataHandler(new DataHandler(new FileDataSource(new File(SalvaArquivoTemporario(obj.getArquivo(), (byte[]) obj.getConteudo())))));
                }
                anexo.setFileName(obj.getArquivo());
                mp.addBodyPart(anexo);
            }

            //Mensagem           
            msg.setFrom(new InternetAddress(Endde));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(EndPara));
            //CC
            for (String obj : CopiaPara) {
                msg.setRecipient(Message.RecipientType.CC, new InternetAddress(obj));
            }
            //CCO
            for (String obj : CopiaOPara) {
                msg.setRecipient(Message.RecipientType.BCC, new InternetAddress(obj));
            }
            msg.setSentDate(new Date());
            msg.setSubject(Messages.getMessage("sistem.nome") + " " + assunto);
            msg.setContent(mp);
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            anexo = null;
            mp = null;
            auth = null;
            msg = null;
            prop = null;
            session = null;
        }
    }

    /**
     * Salva um arquivo no diretorio temporario do Sistema Operacional
     *
     * @param o nome do arquivo
     * @param o arquivo em Bytes
     * @return a String contendo o Nome completo do arquivo no sistema
     * operacional.
     * @since 1.2
     */
    private String SalvaArquivoTemporario(String arquivo, byte[] objeto) throws Exception {
        FileChannel fil = null;
        String arquivotemp = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + arquivo;
        try {
            fil = new RandomAccessFile(arquivotemp, "rw").getChannel();
            ByteBuffer buf = ByteBuffer.wrap(objeto);
            fil.write(buf);
            fil.close();
            objeto = null;
            return arquivotemp;
        } catch (Exception e) {
            throw e;
        }
    }
}
