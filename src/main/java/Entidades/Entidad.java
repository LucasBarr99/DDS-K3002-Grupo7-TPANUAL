package Entidades;

import Comunidades.Usuario;
import Localizaciones.Ubicacion;

import java.util.List;

public class Entidad {
  String nombre;
  List<Ubicacion> ubicacion;
  List<Usuario> interesados;

  public Entidad(String nombre, List<Ubicacion> ubicacion, List<Usuario> interesados) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.interesados = interesados;
  }
}
