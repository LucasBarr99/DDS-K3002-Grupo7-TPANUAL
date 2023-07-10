package Entidades;

import Personas.Interesado;
import Personas.Usuario;
import Establecimientos.Sucursal;
import Localizaciones.Ubicacion;

import java.util.List;

public class Organizacion extends Entidad {

  List<Sucursal> sucursales;

  public Organizacion(String nombre, List<Ubicacion> ubicacion, List<Interesado> interesados) {
    super(nombre, ubicacion, interesados);
  }
}
