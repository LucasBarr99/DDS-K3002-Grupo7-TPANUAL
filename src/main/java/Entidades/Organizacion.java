package Entidades;

import Comunidades.Usuario;
import Establecimientos.Sucursal;
import Localizaciones.Ubicacion;

import java.util.List;

public class Organizacion extends Entidad {

List<Sucursal> sucursales;

  public Organizacion(String nombre, List<Ubicacion> ubicacion, List<Usuario> interesados) {
    super(nombre, ubicacion, interesados);
  }
}
