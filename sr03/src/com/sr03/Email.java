package com.sr03;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;



public class Email{


	private String smtpHost = "smtps.utc.fr";

	private String user = "sr03p016";

	private String passwd = "GBtR5g6y";

	
	public boolean sendMessage (String from, String to, String subject, String corps) {	
	    String smtpHost = this.smtpHost;
	    String username = this.user;
	    String password = this.passwd;
	    Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.ssl.enable", "true");
	    Session session2 = Session.getDefaultInstance(props);
		try{
	         MimeMessage message = new MimeMessage(session2);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject(subject);
	         message.setText(corps);  
	         Transport tr = session2.getTransport("smtp");
	         tr.connect(smtpHost, username, password);
	         message.saveChanges();    	      
	         tr.sendMessage(message,message.getAllRecipients());
	         tr.close();    	             	         
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	 		return false;

	      }
		return true;
	}
}