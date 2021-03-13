/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.epmmop.bio.utils;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@Stateless
@LocalBean
public class EnvioMailServicio {

    @Resource(lookup = "java:jboss/envioMail", type = javax.mail.Session.class)
    private Session mailSession;

    public void enviarMail(String asunto, String destino, String cuerpo, String remitente) throws Exception {
        
        InternetAddress[] adrRemitente = this.getInternetAddress(remitente);
        
        MimeMessage message = new MimeMessage(this.mailSession);
        message.setFrom(new InternetAddress(remitente));
        message.setSubject(asunto,"utf-8");
       
        message.setRecipients(Message.RecipientType.BCC, this.getInternetAddress(destino));
        message.setText(cuerpo,"utf-8");  
        
        Transport.send(message);
        
    }

    private InternetAddress[] getInternetAddress(String destinatarios) throws AddressException, MessagingException {
        InternetAddress[] dirs = null;
        String[] pieces = destinatarios.split(";");
        List<String> list = Arrays.asList(pieces);
        dirs = new InternetAddress[list.size()];
        int i = 0;
        for (String m : list) {
            InternetAddress address = new InternetAddress(m);
            dirs[i++] = address;
        }
        return dirs;
    }

   
    
    public void enviarMailHTML(String asunto, String destino, String cuerpo, String remitente, String nombreSistema) throws Exception {
    	
        BodyPart texto = new MimeBodyPart();
        String cuerpoHTML="<!DOCTYPE html>"
        		+"<html lang=\"es\"> "
        		+"<head> "
        		+" <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" "
        		+ "integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"
        		+"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" "
        		+ "integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>"
        		  +"<meta charset=\"utf-8\">"
        		  +"<style>   "   

        		+".panel-default>.panel-heading {"
        		  +"  color: white;"
        		    +"background-color: #0055a4;"
        		  +" text-align: center;"
        		    +"border-color: #ddd;"
        		     +"font-family: 'Open Sans', sans-serif;"
        		     +"}"
        		+".panel-heading {"
        		    +"padding: 10px 15px;"
        		    +"border-bottom: 1px solid transparent;"
        		    +"border-top-left-radius: 3px;"
        		    +"border-top-right-radius: 3px;"
        		    +" font-family: 'Open Sans', sans-serif;"
        		    +"}"
        		+".panel-body {"
        		  +"  padding: 15px;"
        		    +"  background-color: #ffffff;"
        		     +"font-family: 'Open Sans', sans-serif;"
        		     +"} "
        		+"</style>"
        		+"</head>"
        		+"<body> "
        		+"<div>"
        		  +"<div class=\"panel-default\" >"
        		  +"<div class=\"panel-heading\">EPMMOP</div>"
        		  +" <div class=\"panel-body\">"
        		  +"    <h1 style=\"color:DodgerBlue\">"+nombreSistema+"</h1>"
        		    +cuerpo      
        				+"</div>"
        		  +"</div>"
        		+"</div>"
        		+"</body>"
        		+"</html>";
                
        
        
        texto.setContent(cuerpoHTML, "text/html; charset=utf-8");        
       System.out.println(cuerpoHTML);
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);

        MimeMessage message = new MimeMessage(this.mailSession);
        message.setFrom(new InternetAddress(remitente));
        message.setSubject(asunto,"utf-8");
        message.setRecipients(Message.RecipientType.BCC, this.getInternetAddress(destino));
        message.setContent(multiParte);

        Transport.send(message);
    }
}
