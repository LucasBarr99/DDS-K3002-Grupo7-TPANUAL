package CargaDeDatosCSV;

import Comunidades.Usuario;
import Entidades.LineaDeTransporte;

import java.util.List;

public class DatosParser {
  String tipo;
  String nombre;
  String linea;
  String estacionOrigen;
  String estacionDestino;
  MedioDeTransporte medioDeTransporte;

  public DatosParser(String tipo, String nombre, String linea, String estacionOrigen, String estacionDestino, MedioDeTransporte medioDeTransporte) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.linea = linea;
    this.estacionOrigen = estacionOrigen;
    this.estacionDestino = estacionDestino;
    this.medioDeTransporte = medioDeTransporte;
  }
}
