package Modelo.Servicios;

import Modelo.Comunidades.Miembro;
import Modelo.Entidades.Entidad;
import Modelo.Incidentes.EstadoIncidentes;
import Modelo.Incidentes.Incidente;
import Persistencia.EntidadPersistente;
import Persistencia.Repositorios.RepoIncidentes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "Servicios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Servicio extends EntidadPersistente {

  String descripcion;
  @OneToMany
  @JoinColumn(name = "idServicio")
  List<Servicio> subServicios = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "idEntidad")
  Entidad entidad;

  @Transient
  List<Incidente> incidentes = new ArrayList<>();

  public Servicio() {

  }


  public void sugerirRevision(Miembro miembroANotificar){
   if(correspondeSugerenciaA(miembroANotificar)){
     miembroANotificar.notificarRevisionDeInicidente(incidenteMasRecienteDe(miembroANotificar));
   }

  }
  public Servicio(String descripcion, List<Servicio> subServicios, Entidad entidad) {
    this.descripcion = descripcion;
    this.subServicios = subServicios;
    this.entidad = entidad;
  }
  public Servicio(String descripcion, List<Servicio> subServicios) {
    this.descripcion = descripcion;
    this.subServicios = subServicios;
    this.entidad = entidad;
  }


  public void agregarIncidente(Incidente incidente){
    incidentes.add(incidente);
    entidad.reportarIncidente(incidente);
  }

  public void setDescripcion(String descripcionN){
    descripcion = descripcionN;
  }
  public String getDescripcion() {
    return descripcion;
  }

  public Incidente incidenteMasRecienteDe(Miembro miembro){
     return incidentesQueInteresanA(miembro).get(incidentesQueInteresanA(miembro).size() - 1);
  }

  public List<Incidente> incidentesAbiertos(){
    return incidentes.stream().filter(incidente -> incidente.getEstado() == EstadoIncidentes.ABIERTO).collect(Collectors.toList());
  }

  public List<Incidente> incidentesQueInteresanA(Miembro miembro){
    return incidentesAbiertos().stream().filter(incidente -> miembro.estaEnComunidad(incidente.comunidadInvolucradaEnIncidente())).collect(Collectors.toList());
  }
  public boolean correspondeSugerenciaA(Miembro miembroASugerir){
    return incidentesQueInteresanA(miembroASugerir).size() > 0;
  }

  public Entidad getEntidad(){
    return entidad;
  }

  public boolean perteneceAEntidad(Entidad entidad1){
    return entidad.equals(entidad1);
  }

  public boolean perteneceAalgunaEntidad(List<Entidad> entidades){
    return entidades.contains(entidad);
  }

}

