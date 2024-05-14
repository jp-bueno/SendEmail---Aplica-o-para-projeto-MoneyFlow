package br.com.jpbueno;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Mail {
    Session newSession = null;
    MimeMessage mimeMessage = new MimeMessage(newSession);
    public static void main(String[] args) throws MessagingException {

        Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail();
        mail.sendEmail();
    }

    private MimeMessage draftEmail() throws AddressException, MessagingException {

        // Insira o email:
        String[] emailReceiptients = {"***@gmail.com","***@gmail.com"};
        String emailSubject = "Fala ai seu cabeça de nós todos";
        String emailBody = "Esse é o corpo do EMAIL seu chupis";

        for (String emailReceiptient : emailReceiptients) {
            mimeMessage.addRecipients(Message.RecipientType.TO, emailReceiptient);
        }

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        mimeMessage.setContent(multipart);
        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }

    private void sendEmail() throws MessagingException {
        // Credenciais SMTP:
        String fromUser = "***#gmail.com";
        String fromPassword = "******";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser, fromPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent");
    }
}