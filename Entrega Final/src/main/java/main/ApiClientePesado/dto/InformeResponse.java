package main.ApiClientePesado.dto;

import Modelo.Entidades.Entidad;
import Modelo.InformeRanking.GeneradorInforme;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class InformeResponse {
  public String nombre;
  public String descripcion;
  public Date fechaGeneracion;

  public List<Entidad> entidades;

  public int criterio;

  public InformeResponse(){

  }

  public InformeResponse(String nombre, String descripcion, Date fechaGeneracion, List<Entidad> entidades, int criterio) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.fechaGeneracion = fechaGeneracion;
    this.entidades = entidades;
    this.criterio = criterio;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Date getFechaGeneracion() {
    return fechaGeneracion;
  }

  public void setFechaGeneracion(Date fechaGeneracion) {
    this.fechaGeneracion = fechaGeneracion;
  }

  public List<Entidad> getEntidades() {
    return entidades;
  }

  public void setEntidades(List<Entidad> entidades) {
    this.entidades = entidades;
  }

  public Integer getCriterio() {
    return criterio;
  }

  public void setCriterio(Integer criterio) {
    this.criterio = criterio;
  }
}
