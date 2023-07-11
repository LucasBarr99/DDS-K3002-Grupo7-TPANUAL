package Establecimientos;

import Personas.Interesado;
import Personas.Usuario;
import Localizaciones.Ubicacion;
import Servicios.Servicio;

import java.util.List;

public abstract class Establecimiento {
  String nombre;
  Ubicacion ubicacion;
  List<Servicio> servicios;
  List<Interesado> interesados;




}
