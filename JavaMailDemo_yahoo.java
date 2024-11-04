/* 
 * This test was created by Swathi Kalahastri
 * to verify JavaMail functionality
 * for Yahoo Mail using app authentication with an email ID and password.
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

public class JavaMailDemo_yahoo {
	   public static void main(String[] args) {
	        // Sender's Yahoo email credentials
	        final String senderEmail = "xxx@yahoo.co.in"; // Your Yahoo email
	        final String senderPassword = "xxxxxxx"; // Your Yahoo password

	        // Yahoo SMTP server properties
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
	        props.put("mail.smtp.port", "587");

	        // Set up the mail session
	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });

	        try {
	            // Compose the message
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress("nnn@g.luck")); // Change this to the recipient's email
	            message.setSubject("Test Email from Yahoo");
	            message.setText("This is a test email sent from JavaMail using Yahoo SMTP.");
	            //message.setHeader("Return-Path", "noreply@yourdomain.com");


	            // Enable debugging for more information
	            session.setDebug(true);

	            // Send the message
	            Transport.send(message);
	            System.out.println("Email sent successfully!");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
}