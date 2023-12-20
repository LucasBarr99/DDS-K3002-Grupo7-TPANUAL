package Modelo.Incidentes;

import Persistencia.EntidadPersistente;
import Modelo.Servicios.Servicio;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class NotificacionIncidente extends EntidadPersistente {

  @OneToOne
  public Incidente incidente;
  public String asunto;
  public String descripcion;

  public NotificacionIncidente(Incidente incidente, String asunto, String descripcion) {
    this.incidente = incidente;
    this.asunto = asunto;
    this.descripcion = descripcion;
  }

  public NotificacionIncidente() {

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
