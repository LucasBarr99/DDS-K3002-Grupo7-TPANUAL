package Establecimientos;

import Entidades.Organizacion;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import Servicios.Servicio;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.util.List;

@Entity
@DiscriminatorValue("Sucursal")
public class Sucursal extends Establecimiento{
  @Enumerated
  TipoSucursal tipoSucursal;
  @Transient
  Organizacion organizacion;

  public Sucursal(String nombre, TipoSucursal tipoSucursal, Ubicacion ubicacion, Organizacion organizacion) {
    this.nombre = nombre;
    this.tipoSucursal = tipoSucursal;
    this.ubicacion = ubicacion;
    this.organizacion = organizacion;

  }

  public Sucursal() {

  }
}
