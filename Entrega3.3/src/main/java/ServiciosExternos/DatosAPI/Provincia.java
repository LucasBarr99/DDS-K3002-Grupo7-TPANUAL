package ServiciosExternos.DatosAPI;

public class Provincia extends Localizacion {

  public Provincia(int id, String nombre, Coordenadas coordenadas) {
    super(id, nombre, coordenadas);
  }

  public Provincia(int id, String nombre) {
    super(id, nombre);
  }
}
