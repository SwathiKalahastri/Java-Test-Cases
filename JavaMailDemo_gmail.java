/* 
 * This test was created by Swathi Kalahastri
 * to verify JavaMail functionality
 * for Gmail using a regular email ID and password.
 */


package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailDemo_gmail {
	  public static void main(String[] args) {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true"); 
	        Session session = Session.getDefaultInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("xxxxx@gmail.com",  "xxxx");
	            }
	        });

	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("lawyerswathik@gmail"));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lawyerswathik@gmail.com"));
	            message.setSubject("Test Email with Return-Path");
	            message.setText("This is a test email.");

	            // Set the Return-Path header
	            message.setHeader("Return-Path", "bounce-handler@yourdomain.com");

	            Transport.send(message);
	            System.out.println("Email sent successfully!");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	}