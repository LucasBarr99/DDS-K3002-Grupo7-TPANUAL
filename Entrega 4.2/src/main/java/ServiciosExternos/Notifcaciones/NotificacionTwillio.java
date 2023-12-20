package ServiciosExternos.Notifcaciones;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import utils.TokenPropertiesUtil;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.net.URI;
import java.math.BigDecimal;
@Entity
@DiscriminatorValue("Whatsapp")
public class NotificacionTwillio extends Notificacion {
  @Override
  public void notificar(String mensaje, String numero, String mailDestino, String asunto) {
    this.enviarMensaje(numero, mensaje, asunto);
  }

  private void enviarMensaje(String numero, String mensaje, String asunto){
      configurarTwillio();
      Message message = Message.creator(
              new PhoneNumber("whatsapp:"+numero),
              new PhoneNumber("whatsapp:"+TokenPropertiesUtil.getString("t.wappNumber")),
              asunto+":"+" "+mensaje)

          .create();
    }

  private void configurarTwillio() {
    Twilio.init(
        TokenPropertiesUtil.getString("t.wappSid"),
        TokenPropertiesUtil.getString("t.wappAuth")
    );
  }

}
