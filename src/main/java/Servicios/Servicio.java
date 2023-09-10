package Servicios;

import Comunidades.Comunidad;
import Comunidades.Miembro;
import Entidades.Entidad;
import Incidentes.EstadoIncidentes;
import Incidentes.Incidente;
import Persistencia.EntidadPersistente;
import Repositorios.RepoIncidentes;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "Servicios")
public class Servicio extends EntidadPersistente {
  String descripcion;
  @Transient
  List<Servicio> subServicios;
  @Transient
  Entidad entidad;
  @Transient
  List<Incidente> incidentes;

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


  public void agregarIncidente(Incidente incidente){
    incidentes.add(incidente);
    RepoIncidentes.getInstance().agregarIncidente(incidente);
    entidad.reportarIncidente(incidente);
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
}

