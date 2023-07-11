package Entidades;

import Comunidades.Usuario;
import Localizaciones.Localizacion;

import java.util.List;

public class Entidad {
  String nombre;
  List<Localizacion> localizacion;
  List<Usuario> interesados;

  public Entidad(String nombre, List<Localizacion> localizacion, List<Usuario> interesados) {
    this.nombre = nombre;
    this.localizacion = localizacion;
    this.interesados = interesados;
  }
}
