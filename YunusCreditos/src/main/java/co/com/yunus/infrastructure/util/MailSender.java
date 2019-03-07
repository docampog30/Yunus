package co.com.yunus.infrastructure.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	
	static Properties props = new Properties();
	
	static {
		props.put("mail.smtp.host", "mail.coopeceja.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465"); 
	}
    Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("servicios@coopeceja.com","alexrosco2017");
            }
        });
	
	public void enviarMailAbono(String destinatario,String asunto,String contenido) {
	    
	    try {

	    	MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("servicios@coopeceja.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(destinatario));
	        message.setSubject(asunto);
	        message.setText(contenido, "utf-8", "html"); 	

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
}
