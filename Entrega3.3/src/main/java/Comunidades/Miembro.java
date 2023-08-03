package Comunidades;

import Comunidades.Comunidad;
import Entidades.LineaDeTransporte;
import Incidentes.Incidente;
import Servicios.Servicio;
import ServiciosExternos.Notifcaciones.Notificacion;

import java.util.List;

public class Miembro {
  String nombre;
  String apellido;
  String correo;
  List<Comunidad> comunidades;
  List<LineaDeTransporte> lineasDeInteres;
  TipoUsuario tipo;
  Notificacion servicioNotificacion;




  void reportarIncidenteEn(Servicio servicio, Incidente incidente){
      servicio.agregarIncidente(incidente);
  }

  public List<Comunidad> getComunidades() {
    return comunidades;
  }

  public boolean estaEnAlgunaComunidadDe(List<Comunidad> listaComunidades){
    return comunidades.stream().anyMatch(comunidad -> listaComunidades.stream().anyMatch(comunidad1 -> comunidad.equals(comunidad1)));
  }

  public void notificarRevisionDeInicidente(Incidente incidente){
    servicioNotificacion.notificar(incidente.getDescripcion(),"",correo, "Se sugiere revisar el servicio " + incidente.nombre);
  }

}
