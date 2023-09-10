package Entidades;

import Incidentes.NotificacionIncidente;
import Personas.Interesado;
import Personas.Usuario;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import Servicios.Servicio;

import java.util.List;

public class Entidad {
  String nombre;
  List<Ubicacion> ubicacion;
  List<Interesado> interesados;

  public Entidad(String nombre, List<Ubicacion> ubicacion, List<Interesado> interesados) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.interesados = interesados;
  }

  public void reportarIncidente(Incidente incidente){
    interesados.forEach(interesado -> interesado.agregarNotificacionIncidente(new NotificacionIncidente(incidente, incidente.nombre, incidente.descripcion)));
  }

  public void notificarInteresados(){
    interesados.forEach(interesado -> interesado.notificar());
  }
}
