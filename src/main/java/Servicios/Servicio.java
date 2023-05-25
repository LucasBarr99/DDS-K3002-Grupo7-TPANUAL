package Servicios;

import Comunidades.Miembro;
import Comunidades.Usuario;

import java.util.List;

public class Servicio {
  String descripcion;
  List<Servicio> subServicios;

  List<Miembro> personasInteresadas;

  public Servicio(String descripcion, List<Servicio> subServicios) {
    this.descripcion = descripcion;
    this.subServicios = subServicios;
  }

  void agregarInteresado(Miembro interesado){
    personasInteresadas.add(interesado);
  }
}
