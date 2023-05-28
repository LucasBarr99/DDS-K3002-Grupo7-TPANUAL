package Entidades;

import Comunidades.Usuario;
import Establecimientos.Sucursal;
import Localizaciones.Localizacion;

import java.util.List;

public class Organizacion extends Entidad {

List<Sucursal> sucursales;

  public Organizacion(String nombre, Localizacion localizacion, List<Usuario> interesados) {
    super(nombre, localizacion, interesados);
  }
}
