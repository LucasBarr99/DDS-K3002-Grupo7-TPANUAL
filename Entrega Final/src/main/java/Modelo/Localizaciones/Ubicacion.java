package Modelo.Localizaciones;

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

  public TipoLocalizacion getTipo() {
    return tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public int getLatitud() {
    return latitud;
  }

  public int getLongitud() {
    return longitud;
  }

  public boolean estaCercaDe(Ubicacion ubicacion){
    return ubicacion.getTipo() == getTipo() && ubicacion.getNombre().equals(getNombre());
  }
}
