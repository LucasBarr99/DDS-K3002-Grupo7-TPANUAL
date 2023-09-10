package Localizaciones;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Ubicacion  {
  @Enumerated
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

  public Ubicacion() {

  }
}
