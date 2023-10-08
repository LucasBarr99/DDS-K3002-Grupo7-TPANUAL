package Personas;

import Excepciones.ContraseñaInvalidaException;
import Incidentes.NotificacionIncidente;
import Incidentes.RangoHorarioNotificacion;
import Localizaciones.Ubicacion;
import ServiciosExternos.Notifcaciones.Notificacion;
import Validadores.ValidadorContrasenias;

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
        for (int i = 0; i < notificacionesPendientes.size(); i++) {
          if (!notificacionesPendientes.get(i).getIncidente().estaCerrado()) {
            NotificacionIncidente notificacion = notificacionesPendientes.get(i);
            serviciosDeNotificacion.forEach(servicio -> servicio.notificar(notificacion.getDescripcion(),
                numero, correo, notificacion.getAsunto() + " ha ocurrido en " + notificacion.getDescripcion()));
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

