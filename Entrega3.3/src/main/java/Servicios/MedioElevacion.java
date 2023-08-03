package Servicios;

import Entidades.Entidad;
import Servicios.Servicio;

import java.util.List;

public class MedioElevacion extends Servicio {
  PuntoElevacion origen;
  PuntoElevacion destino;

  public MedioElevacion(String descripcion, List<Servicio> subServicios,Entidad entidad, PuntoElevacion origen, PuntoElevacion destino) {
    super(descripcion, subServicios, entidad);
    this.origen = origen;
    this.destino = destino;
  }
}
