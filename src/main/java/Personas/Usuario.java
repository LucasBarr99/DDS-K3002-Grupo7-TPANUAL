package Personas;

import Excepciones.ContraseñaInvalidaException;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import ServiciosExternos.Notifcaciones.Notificacion;
import Validadores.ValidadorContrasenias;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario implements Interesado{
  public String nombre;
  public String contraseña;

  public Ubicacion ubicacion;
  public String correo;
  public String numero;

  public LocalDateTime horarioNotificacion;

  public Notificacion servicioNotificacion;

  public Usuario(String nombre, String contraseña, Ubicacion ubicacion, String correo, String numero) {
    this.nombre = nombre;
    ValidadorContrasenias validador = new ValidadorContrasenias();
    try{
      validador.validarContrasenia(contraseña);
    }
    catch (ContraseñaInvalidaException s){
        // Enviar a componente que se encargue de mostarlo en pantalla;
    }

    this.contraseña = contraseña;
    this.ubicacion = ubicacion;
    this.correo = correo;
    this.numero = numero;
  }

  public void setHorarioNotificacion(LocalDateTime horarioNotificacion) {
    this.horarioNotificacion = horarioNotificacion;
  }

  public void notificarIncidente(Incidente incidente) {
    if (horarioNotificacion != null) {
      if (LocalDateTime.now().equals(horarioNotificacion)) { // VERIFICAR
        servicioNotificacion.notificar(incidente.getDescripcion(), numero, correo, incidente.nombre + " ha ocurrido en " + incidente.getServicio().getDescripcion());
      }
    }
    // TODO: VER QUE HACER ACA
  }
}
