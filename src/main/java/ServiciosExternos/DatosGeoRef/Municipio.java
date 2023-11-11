package ServiciosExternos.DatosGeoRef;

public class Municipio extends Localizacion{

  public Provincia provincia;
  public Municipio(int id, String nombre, Coordenadas coordenadas, Provincia provincia) {
    super(id, nombre, coordenadas);
    this.provincia = provincia;
  }
}
