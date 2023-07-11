package Servicios;

import Servicios.Servicio;

import java.util.List;

public class MedioElevacion extends Servicio {
  PuntoElevacion origen;
  PuntoElevacion destino;

  public MedioElevacion(String descripcion, List<Servicio> subServicios, PuntoElevacion origen, PuntoElevacion destino) {
    super(descripcion, subServicios);
    this.origen = origen;
    this.destino = destino;
  }
}
