package main.ApiClientePesado.dto;

import Modelo.Comunidades.Miembro;
import Modelo.Incidentes.EstadoIncidentes;
import Modelo.Servicios.Servicio;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

public class IncidentResponse {
  public String nombre;

  public Date fechaApertura;

  public Date fechaCierre;

  public String servicioAfectado;

  public String descripcion;

  public EstadoIncidentes estado;

  public String entidadServicio;
  public IncidentResponse(String nombre, Date fechaApertura, Date fechaCierre, String servicioAfectado, String descripcion, EstadoIncidentes estado, String entidadServicio) {
    this.nombre = nombre;
    this.fechaApertura = fechaApertura;
    this.fechaCierre = fechaCierre;
    this.servicioAfectado = servicioAfectado;
    this.descripcion = descripcion;
    this.estado = estado;
    this.entidadServicio = entidadServicio;
  }

  public String getNombre() {
    return nombre;
  }

  public Date getFechaApertura() {
    return fechaApertura;
  }

  public Date getFechaCierre() {
    return fechaCierre;
  }

  public String getServicioAfectado() {
    return servicioAfectado;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public EstadoIncidentes getEstado() {
    return estado;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setFechaApertura(Date fechaApertura) {
    this.fechaApertura = fechaApertura;
  }

  public void setFechaCierre(Date fechaCierre) {
    this.fechaCierre = fechaCierre;
  }

  public void setServicioAfectado(String servicioAfectado) {
    this.servicioAfectado = servicioAfectado;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setEstado(EstadoIncidentes estado) {
    this.estado = estado;
  }

  public String getEntidadServicio() {
    return entidadServicio;
  }

  public void setEntidadServicio(String entidadServicio) {
    this.entidadServicio = entidadServicio;
  }
}
