package Incidentes;

import Servicios.Servicio;

public class NotificacionIncidente {
  public Incidente incidente;
  public String asunto;
  public String descripcion;

  public NotificacionIncidente(Incidente incidente, String asunto, String descripcion) {
    this.incidente = incidente;
    this.asunto = asunto;
    this.descripcion = descripcion;
  }

  public Incidente getIncidente() {
    return incidente;
  }

  public String getAsunto() {
    return asunto;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Servicio getServicio(){
    return incidente.getServicio();
  }
}
