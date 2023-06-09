package Servicios;

import Entidades.Entidad;
import Incidentes.Incidente;
import Repositorios.RepoIncidentes;

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
    RepoIncidentes.getInstance().agregarIncidente(incidente);
    entidad.reportarIncidente(incidente);
  }

  public String getDescripcion() {
    return descripcion;
  }
}

