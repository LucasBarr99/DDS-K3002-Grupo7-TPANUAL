package Establecimientos;

import Localizaciones.Ubicacion;
import Servicios.Servicio;

import java.util.List;


public class Estacion extends Establecimiento {
  String nombre;
  Ubicacion ubicacion;
  List<Servicio> servicios;
  LineaDeTransporte lineaDeTransporte;

  public Estacion(String nombre, Ubicacion ubicacion, LineaDeTransporte lineaDeTransporte) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.lineaDeTransporte = lineaDeTransporte;
  }




}
