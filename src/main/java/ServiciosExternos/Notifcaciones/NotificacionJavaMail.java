package ServiciosExternos.Notifcaciones;

import utils.TokenPropertiesUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.util.Properties;

@Entity
@DiscriminatorValue("Mail")
public class NotificacionJavaMail extends Notificacion {

  public NotificacionJavaMail() {

  }

  @Override
  public void notificar(String mensaje, String numero, String mailDestino, String asunto){
    this.enviarEmail(mailDestino, asunto, mensaje);
  }

  public void enviarEmail(String direccionMail, String asunto, String textoMail) {
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.socketFactory.port", "465");
    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    String username = TokenPropertiesUtil.getString("t.mailuser"); //mail emisor
    String password = TokenPropertiesUtil.getString("t.mailpass"); //constrasenia del mail emisor

    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    Message message = prepararMessage(session, username, direccionMail, asunto, textoMail);
    try {
      Transport.send(message);
      System.out.println("Done");
    }catch(Exception e){
      System.out.println(e.getMessage());
      throw new RuntimeException("No se pudo enviar el mail");
    }
  }
  private static Message prepararMessage(Session session,
                                         String myAccountEmail,
                                         String direccionMail,
                                         String asunto,
                                         String textoMail) {

    try{
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(myAccountEmail));
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(direccionMail));
      message.setSubject(asunto);
      message.setText(textoMail);
      return message;
    } catch(MessagingException e){
      throw new RuntimeException("No se pudo preparar el mensaje del mail");
    }

  }

}
