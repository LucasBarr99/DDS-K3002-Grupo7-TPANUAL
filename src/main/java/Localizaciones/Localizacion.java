package Localizaciones;

public class Localizacion {
  TipoLocalizacion tipo;
  String nombre;
  int latitud;
  int longitud;

  public Localizacion(TipoLocalizacion tipo, String nombre, int latitud, int longitud) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.latitud = latitud;
    this.longitud = longitud;
  }
}
