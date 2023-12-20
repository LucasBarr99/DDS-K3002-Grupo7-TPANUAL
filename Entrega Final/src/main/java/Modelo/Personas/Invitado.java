package Modelo.Personas;

import Modelo.Incidentes.NotificacionIncidente;
import ServiciosExternos.Notifcaciones.NotificacionJavaMail;

import javax.persistence.*;

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
          NotificacionIncidente notificacion = notificacionesPendientes.get(i);
          servicioNotificacion.notificar(notificacion.getDescripcion(),
              "0", correo, notificacion.getAsunto() + " ha ocurrido en " + notificacion.getDescripcion());
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
