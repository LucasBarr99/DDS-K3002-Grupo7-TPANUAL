package Servicios;

import Comunidades.Miembro;
import Comunidades.Usuario;
import Entidades.Entidad;
import Establecimientos.Establecimiento;
import Incidentes.EstadoIncidentes;
import Incidentes.Incidente;

import java.util.List;

public class Servicio {
  String descripcion;
  List<Servicio> subServicios;
  Entidad entidad;
  List<Incidente> incidentes;



  public Servicio(String descripcion, List<Servicio> subServicios, Entidad entidad) {
    this.descripcion = descripcion;
    this.subServicios = subServicios;
    this.entidad = entidad;
  }

  public void agregarIncidente(Incidente incidente){
    incidentes.add(incidente);
    entidad.reportarIncidente(incidente);
  }

}

