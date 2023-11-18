package Modelo.Personas;

import Modelo.Incidentes.NotificacionIncidente;
import Modelo.Incidentes.RangoHorarioNotificacion;
import Modelo.Localizaciones.Ubicacion;
import ServiciosExternos.Notifcaciones.Notificacion;

import javax.persistence.*;
import java.time.*;
import java.util.List;
@Entity
@Table(name = "Personas")
public class Persona extends Interesado {


  @Embedded
  public Ubicacion ubicacion;

  public String numero;
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "ServicioNotificacionPorPersona",
      joinColumns = { @JoinColumn(name = "idPersona") },
      inverseJoinColumns = { @JoinColumn(name = "idservicio") }
  )
  public List<Notificacion> serviciosDeNotificacion;

  @ElementCollection
  public List<RangoHorarioNotificacion> rangosDeNotificacion;

  @OneToOne
  private Usuario usuario;


  public Persona(String nombre, Ubicacion ubicacion, String correo, String numero, List<Notificacion> serviciosDeNotificacion, List<RangoHorarioNotificacion> rangosDeNotificacion) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.correo = correo;
    this.numero = numero;
    this.serviciosDeNotificacion = serviciosDeNotificacion;
    this.rangosDeNotificacion = rangosDeNotificacion;
  }

  public Persona() {

  }

  public boolean correspondeNotificar(){
    return !notificacionesPendientes.isEmpty() && rangosDeNotificacion.stream().anyMatch(rango -> rango.estaDentroDeRango(LocalTime.now()));
  }

  public void notificar() {
    if(this.correspondeNotificar()){
      for (NotificacionIncidente notificacionesPendiente : notificacionesPendientes) {
        if (!notificacionesPendiente.getIncidente().estaCerrado()) {
          serviciosDeNotificacion.forEach(servicio -> servicio.notificar(notificacionesPendiente.getDescripcion(),
                  numero, correo, notificacionesPendiente.getAsunto() + " ha ocurrido en " + notificacionesPendiente.getDescripcion()));
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

