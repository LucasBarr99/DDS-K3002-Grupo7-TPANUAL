package ServiciosExternos.DatosAPI;

public abstract class Localizacion {

  public int id;
  public String nombre;
  public Coordenadas centroide;

  public Localizacion(int id,String nombre, Coordenadas coordenadas) {
    this.id =id;
    this.nombre = nombre;
    this.centroide = coordenadas;
  }
  public Localizacion(int id,String nombre) {
    this.id =id;
    this.nombre = nombre;
  }
}
