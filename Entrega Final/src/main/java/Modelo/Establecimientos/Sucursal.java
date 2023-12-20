package Modelo.Establecimientos;

import Modelo.Entidades.Organizacion;
import Modelo.Localizaciones.Ubicacion;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

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
