package CargaDeDatosCSV;

public class DatosParser {
  String tipo;
  String nombre;
  String entidad;
  TipoEntidad tipoEntidad;

  public DatosParser(String tipo, String nombre, String entidad, TipoEntidad tipoEntidad) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.entidad = entidad;
    this.tipoEntidad = tipoEntidad;
  }

  public String getTipo() {
    return tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public String getEntidad() {
    return entidad;
  }

  public TipoEntidad getTipoEntidad() {
    return tipoEntidad;
  }
}
