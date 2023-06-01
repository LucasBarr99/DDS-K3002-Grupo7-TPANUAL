package Entidades;

import Comunidades.Usuario;
import Establecimientos.Estacion;
import Localizaciones.Localizacion;

import java.util.List;


public class LineaDeTransporte extends Entidad {
  String nombre;
   Estacion origen;
   Estacion destino;
   Localizacion localizacion;
   List<Estacion> estaciones;
   MedioDeTransporte medioTransporte;


  public LineaDeTransporte(String nombre, List<Localizacion> localizacion, List<Usuario> interesados) {
    super(nombre, localizacion, interesados);
  }
}
