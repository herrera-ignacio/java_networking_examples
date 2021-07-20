import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {

    public static void main(String[] args) {
        String to = "receiver@example.com"; // Replace with target email
        String from  = "sender@example.com"; // Replace with sender email
        String password = "password"; // Replace with sender password
        // You need to allow unsecure apps from your google account settings!
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.auth", true);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Sent from SMTP Java App!");
            message.setText("Hello from Java");

            System.out.println("Sending email...");
            Transport.send(message);
        } catch (MessagingException messagingException) {
            System.out.println(messagingException.getMessage());
        }
    }
}
