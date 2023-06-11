package ServiciosExternos.DatosAPI;

public abstract class Localizacion {

  public int id;
  public String nombre;
  public Coordenadas coordenadas;

  public Localizacion(String nombre, Coordenadas coordenadas) {
    this.nombre = nombre;
    this.coordenadas = coordenadas;
  }
}
