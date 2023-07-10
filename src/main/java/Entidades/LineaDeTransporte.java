package Entidades;

import Personas.Usuario;
import Establecimientos.Estacion;
import Localizaciones.Ubicacion;

import java.util.List;


public class LineaDeTransporte extends Entidad {
  String nombre;
   Estacion origen;
   Estacion destino;
   Ubicacion ubicacion;
   List<Estacion> estaciones;
   MedioDeTransporte medioTransporte;


  public LineaDeTransporte(String nombre, List<Ubicacion> ubicacion, List<Usuario> interesados) {
    super(nombre, ubicacion, interesados);
  }


}
