package Modelo.Entidades;

import Modelo.Incidentes.NotificacionIncidente;
import Modelo.Incidentes.Incidente;
import Modelo.Localizaciones.Ubicacion;
import Modelo.Personas.Interesado;
import Modelo.Servicios.Servicio;
import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoEntidad")
@Table(name = "Entidad")
public class Entidad extends EntidadPersistente {
  @Column(name = "NombreEntidad")
  String nombre;
/*
  @OneToMany(cascade = { CascadeType.ALL })
  @JoinColumn(name = "idEstablecimiento")
  */
  @Transient
  List<Servicio> servicios;

  @ElementCollection
  List<Ubicacion> ubicacion = new ArrayList<>();

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
          name = "EntidadPorInteresado",
          joinColumns = { @JoinColumn(name = "idEntidad") },
          inverseJoinColumns = { @JoinColumn(name = "idInteresado") }
  )
  List<Interesado> interesados;

  public Entidad(String nombre, List<Ubicacion> ubicacion, List<Interesado> interesados) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.interesados = interesados;
  }
  public Entidad(String nombre, List<Ubicacion> ubicacion, List<Interesado> interesados, List<Servicio> servicios) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.interesados = interesados;
    this.servicios = servicios;
  }
  public Entidad(){}
  public void reportarIncidente(Incidente incidente){
    interesados.forEach(interesado -> interesado.agregarNotificacionIncidente(new NotificacionIncidente(incidente, incidente.nombre, incidente.descripcion)));
  }

  public void notificarInteresados(){
    interesados.forEach(interesado -> interesado.notificar());
  }

  public void setServicios(List<Servicio> servicios) {
    this.servicios = servicios;
  }

  public String getNombre() {
    return nombre;
  }

  public boolean estaEnUbicacion(Ubicacion ubicacion2){
    return ubicacion.stream().anyMatch(ubicacion1 -> ubicacion1.estaCercaDe(ubicacion2));
  }
}
