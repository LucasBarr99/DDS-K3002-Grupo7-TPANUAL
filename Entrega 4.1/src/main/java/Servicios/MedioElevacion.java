package Servicios;

import Entidades.Entidad;
import Servicios.Servicio;

import javax.persistence.*;
import java.util.List;
@Entity
@DiscriminatorValue("MedioElevacion")
public class MedioElevacion extends Servicio {
  @Enumerated
  PuntoElevacion origen;
  @Enumerated
  PuntoElevacion destino;

  public MedioElevacion(String descripcion, List<Servicio> subServicios,Entidad entidad, PuntoElevacion origen, PuntoElevacion destino) {
    super(descripcion, subServicios, entidad);
    this.origen = origen;
    this.destino = destino;
  }

  public MedioElevacion() {

  }
}
