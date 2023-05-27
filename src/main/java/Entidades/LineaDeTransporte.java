package Entidades;

import Establecimientos.Estacion;
import Localizaciones.Localizacion;
import CargaDeDatosCSV.MedioDeTransporte;

import java.util.List;


public class LineaDeTransporte {
  String nombre;
  Estacion origen;
  Estacion destino;
  Localizacion localizacion;
  List<Estacion> estaciones;
  MedioDeTransporte medioTransporte;


}
