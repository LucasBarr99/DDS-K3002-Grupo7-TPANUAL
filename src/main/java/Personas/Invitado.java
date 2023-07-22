package Personas;

import Comunidades.Miembro;
import Incidentes.Incidente;
import Incidentes.NotificacionIncidente;
import Servicios.Servicio;
import ServiciosExternos.Notifcaciones.NotificacionJavaMail;

import java.time.LocalDateTime;
import java.util.List;

public class Invitado implements Interesado {
  String nombre;
  String apellido;
  String correo;
  List<Servicio> servicios;
  List<Miembro> membresias;
  List<NotificacionIncidente> notificacionesPendientes;
  NotificacionJavaMail servicioNotificacion;


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
