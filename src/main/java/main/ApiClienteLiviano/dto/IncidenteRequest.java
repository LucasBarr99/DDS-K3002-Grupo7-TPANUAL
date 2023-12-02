package main.ApiClienteLiviano.dto;

public class IncidenteRequest {

  String comunidad;
  String servicio;

  String descripcion;

  public String getComunidad() {
    return comunidad;
  }

  public String getServicio() {
    return servicio;
  }


  public String getDescripcion() {
    return descripcion;
  }

  public void setComunidad(String comunidad) {
    this.comunidad = comunidad;
  }

  public void setServicio(String servicio) {
    this.servicio = servicio;
  }


  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
