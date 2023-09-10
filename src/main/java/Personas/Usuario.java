package Personas;

import Comunidades.Comunidad;
import Excepciones.ContraseñaInvalidaException;
import Incidentes.Incidente;
import Incidentes.NotificacionIncidente;
import Incidentes.RangoHorarioNotificacion;
import Localizaciones.Ubicacion;
import Servicios.Servicio;
import ServiciosExternos.Notifcaciones.Notificacion;
import Validadores.ValidadorContrasenias;
import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;

import javax.persistence.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Usuario")
public class Usuario extends Interesado {


  @Embedded
  public Ubicacion ubicacion;

  public String contrasenia;
  public String numero;
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "ServicioNotificacionPorUsuario",
      joinColumns = { @JoinColumn(name = "idusuario") },
      inverseJoinColumns = { @JoinColumn(name = "idservicio") }
  )
  public List<Notificacion> serviciosDeNotificacion;

  @ElementCollection
  public List<RangoHorarioNotificacion> rangosDeNotificacion;


  public Usuario(String nombre, String contrasenia, Ubicacion ubicacion, String correo, String numero, List<Notificacion> serviciosDeNotificacion,List<RangoHorarioNotificacion> rangosDeNotificacion) {
    this.nombre = nombre;
    ValidadorContrasenias validador = new ValidadorContrasenias();
    try {
      validador.validarContrasenia(contrasenia);
    } catch (ContraseñaInvalidaException s) {
      // Enviar a componente que se encargue de mostarlo en pantalla;
    }

    this.contrasenia = contrasenia;
    this.ubicacion = ubicacion;
    this.correo = correo;
    this.numero = numero;
    this.serviciosDeNotificacion = serviciosDeNotificacion;
    this.rangosDeNotificacion = rangosDeNotificacion;
  }

  public Usuario() {

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

