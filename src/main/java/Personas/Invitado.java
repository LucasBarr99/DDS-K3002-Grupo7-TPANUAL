package Personas;

import Comunidades.Miembro;
import Incidentes.Incidente;
import Incidentes.NotificacionIncidente;
import Servicios.Servicio;
import ServiciosExternos.Notifcaciones.NotificacionJavaMail;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "Invitados")
public class Invitado extends Interesado {




  @OneToOne //Cambiar a OneTone cuando se sepa porque rompe
  private NotificacionJavaMail servicioNotificacion;

  public Invitado() {

  }

  public boolean correspondeNotificar(){
    return !notificacionesPendientes.isEmpty();
  }
  @Override
  public void notificar() {
    if(correspondeNotificar()){
      for (int i = 0; i < notificacionesPendientes.size(); i++) {
        if (!notificacionesPendientes.get(i).getIncidente().estaCerrado()) {
          notificacionesPendientes.forEach(notificacion -> servicioNotificacion.notificar(notificacion.getDescripcion(),
              "", correo, notificacion.getAsunto() + " ha ocurrido en " + notificacion.getDescripcion()));
        }
      }
      notificacionesPendientes.clear();
    }
  }
  @Override
  public void agregarNotificacionIncidente(NotificacionIncidente notificacionIncidente) {
    notificacionesPendientes.add(notificacionIncidente);
  }
}
