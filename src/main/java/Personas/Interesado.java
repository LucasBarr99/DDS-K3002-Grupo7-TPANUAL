package Personas;

import Incidentes.Incidente;
import Incidentes.NotificacionIncidente;

public interface Interesado {

  void notificar();
  void agregarNotificacionIncidente(NotificacionIncidente notificacionIncidente);
}
