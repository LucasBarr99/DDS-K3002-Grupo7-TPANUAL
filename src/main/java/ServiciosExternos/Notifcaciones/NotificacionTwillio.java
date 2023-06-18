package ServiciosExternos.Notifcaciones;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class NotificacionTwillio extends Notificacion {

    public static final String ACCOUNT_SID = "ACa4ac2b9eb960cb4f962e085be1795676";
    public static final String AUTH_TOKEN = "06f9f5ad08e299c6982a5328e98144c7";

  @Override
  public void notificar(String mensaje, String numero, String mailDestino, String asunto) {
    this.enviarMensaje(numero, mensaje, asunto);
  }

  private void enviarMensaje(String numero, String mensaje, String asunto){
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      Message message = Message.creator(
              new PhoneNumber("whatsapp:"+numero),
              new PhoneNumber("whatsapp:+14155238886"),
              asunto+":"+" "+mensaje)

          .create();
    }

}
