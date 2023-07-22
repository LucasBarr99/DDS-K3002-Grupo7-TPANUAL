package Establecimientos;

import Entidades.Organizacion;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import Servicios.Servicio;
import com.sun.org.apache.xpath.internal.operations.Or;


import java.util.List;

public class Sucursal extends Establecimiento {
  String nombre;
  TipoSucursal tipoSucursal;
  Ubicacion ubicacion;
  List<Servicio> servicios;
  Organizacion organizacion;



  public Sucursal(String nombre, TipoSucursal tipoSucursal, Ubicacion ubicacion, Organizacion organizacion) {
    this.nombre = nombre;
    this.tipoSucursal = tipoSucursal;
    this.ubicacion = ubicacion;
    this.organizacion = organizacion;

  }



}
