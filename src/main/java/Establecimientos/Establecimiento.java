package Establecimientos;

import Comunidades.Usuario;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import Servicios.Servicio;

import java.util.List;

public abstract class Establecimiento {
  String nombre;
  Ubicacion ubicacion;
  List<Servicio> servicios;
  List<Usuario> interesados;




}
