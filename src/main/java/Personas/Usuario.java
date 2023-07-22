package Personas;

import Excepciones.ContraseñaInvalidaException;
import Incidentes.Incidente;
import Incidentes.NotificacionIncidente;
import Incidentes.RangoHorarioNotificacion;
import Localizaciones.Ubicacion;
import ServiciosExternos.Notifcaciones.Notificacion;
import Validadores.ValidadorContrasenias;
import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Interesado {
  public String nombre;
  public String contraseña;

  public Ubicacion ubicacion;
  public String correo;
  public String numero;
  public Notificacion servicioNotificacion;

  public List<NotificacionIncidente> notificacionesPendientes;

  public List<RangoHorarioNotificacion> rangosDeNotificacion;

  public Usuario(String nombre, String contrasenia, Ubicacion ubicacion, String correo, String numero, List<RangoHorarioNotificacion> rangosDeNotificacion) {
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
  }

  public boolean correspondeNotificar(){
    return !notificacionesPendientes.isEmpty() && rangosDeNotificacion.stream().anyMatch(rango -> rango.estaDentroDeRango(LocalTime.now()));
  }

  public void notificar() {
    if(this.correspondeNotificar()){
        for (int i = 0; i < notificacionesPendientes.size(); i++) {
          if (!notificacionesPendientes.get(i).getIncidente().estaCerrado()) {
            notificacionesPendientes.forEach(notificacion -> servicioNotificacion.notificar(notificacion.getDescripcion(),
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
