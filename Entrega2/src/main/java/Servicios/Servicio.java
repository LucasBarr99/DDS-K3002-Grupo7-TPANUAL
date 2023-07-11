package Servicios;

import Comunidades.Miembro;
import Comunidades.Usuario;

import java.util.List;

public class Servicio {
  String descripcion;
  List<Servicio> subServicios;



  public Servicio(String descripcion, List<Servicio> subServicios) {
    this.descripcion = descripcion;
    this.subServicios = subServicios;
  }


}

