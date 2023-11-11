package ServiciosExternos.DatosGeoRef;

public class Coordenadas {
  float lat;
  float lon;

  public Coordenadas(float lat, float lon) {
    this.lat = lat;
    this.lon = lon;
  }

  public float getLatitud() {
    return lat;
  }

  public float getLongitud() {
    return lon;
  }
}
