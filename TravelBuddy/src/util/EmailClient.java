package util;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailClient {
	
	private static String USERNAME = "slavicadj.ip2018@gmail.com";  
    private static String PASSWORD = "Ushallpass10"; 

    public static void sendMail(File attachmentFajl, String primalac) {

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }

        });

        try {

            InternetAddress[] adresa = InternetAddress.parse(primalac, true);
            
            MimeMessage poruka = new MimeMessage(session);         
			poruka.addHeader("Content-type", "text/HTML; charset=UTF-8");

            poruka.setRecipients(Message.RecipientType.TO, adresa);
            poruka.setSubject("Travel Tickets: karta");
            poruka.setSentDate(new Date());
            
            Multipart multipart = new MimeMultipart();
            
            BodyPart sadrzajPoruke = new MimeBodyPart();
            sadrzajPoruke.setText("U prilogu se nalazi Vaša karta u pdf formatu. Ukoliko želite, možete je odštampati, ili pokazati QR na Vašem mobilnom uređaju pri ulasku u vozilo. Hvala Vam što ste koristili Travel Buddy!");
            multipart.addBodyPart(sadrzajPoruke);

            BodyPart attachment = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFajl);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(attachmentFajl.getName());
            multipart.addBodyPart(attachment);

            poruka.setContent(multipart);
            Transport.send(poruka);

            System.out.println("Mail has been sent successfully");

        } catch (Exception mex) {

            System.out.println("Unable to send an email" + mex);

        }

    }
   

}
