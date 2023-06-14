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




  void reportarIncidenteEn(Servicio servicio){
    servicio.agregarIncidente(new Incidente());
  }

}
