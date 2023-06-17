package Comunidades;

import Comunidades.Comunidad;
import Entidades.LineaDeTransporte;
import Incidentes.Incidente;
import Servicios.Servicio;

import java.util.List;

public class Miembro {
  String nombre;
  String apellido;
  String correo;
  List<Comunidad> comunidades;
  List<LineaDeTransporte> lineasDeInteres;
  TipoUsuario tipo;




  void reportarIncidenteEn(Servicio servicio, Incidente incidente){
      servicio.agregarIncidente(incidente);
  }

}
