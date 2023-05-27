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

  public String getTipo() {
    return tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public String getLinea() {
    return linea;
  }

  public String getEstacionOrigen() {
    return estacionOrigen;
  }

  public String getEstacionDestino() {
    return estacionDestino;
  }

  public MedioDeTransporte getMedioDeTransporte() {
    return medioDeTransporte;
  }
}
