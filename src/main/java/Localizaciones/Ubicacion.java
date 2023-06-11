package Localizaciones;

public class Ubicacion {
  TipoLocalizacion tipo;
  String nombre;
  int latitud;
  int longitud;

  public Ubicacion(TipoLocalizacion tipo, String nombre, int latitud, int longitud) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.latitud = latitud;
    this.longitud = longitud;
  }
}
