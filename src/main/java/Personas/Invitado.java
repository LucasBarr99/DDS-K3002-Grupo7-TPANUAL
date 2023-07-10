package Personas;

import Comunidades.Miembro;
import Incidentes.Incidente;
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
  NotificacionJavaMail servicioNotificacion;

  public void notificarIncidente(Incidente incidente) {
  // TODO: Decidir si el invitado puede elegir el horario al igual que el usuario
  }
}
