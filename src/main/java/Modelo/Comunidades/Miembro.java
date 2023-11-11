package Modelo.Comunidades;

import Modelo.Incidentes.Incidente;
import Persistencia.EntidadPersistente;
import Modelo.Servicios.Servicio;
import ServiciosExternos.Notifcaciones.Notificacion;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Miembros")
public class Miembro extends EntidadPersistente {
  private String nombre;
  private String apellido;
  private String correo;

  @Transient
  private Comunidad comunidad;

  // Al final decidimos sacarlo ya que los servicios en los que va a estar interesado el miembro estan dados por
  // la comunidad a la que pertenece, entonces cada vez que ocurra un incidente en un servicio, la comunidad
  // va a notificar a sus miembros.
  //List<LineaDeTransporte> lineasDeInteres;

  @Enumerated
  TipoUsuario tipo;
  /*
  @OneToMany
  @JoinColumn(name = "id_miembro")
  */
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "ServicioNotificacionPorMiembro",
      joinColumns = { @JoinColumn(name = "idMiembro") },
      inverseJoinColumns = { @JoinColumn(name = "idServicio") }
  )
  List<Notificacion> serviciosDeNotificacion;


  public Miembro() {

  }

  public Miembro(String nombre, String apellido, String correo, Comunidad comunidades, TipoUsuario tipo, List<Notificacion> serviciosDeNotificacion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correo = correo;
    this.comunidad = comunidad;
    this.tipo = tipo;
    this.serviciosDeNotificacion = serviciosDeNotificacion;
  }

  void reportarIncidenteEn(Servicio servicio, Incidente incidente){
      servicio.agregarIncidente(incidente);
  }

  public Comunidad getComunidad() {
    return comunidad;
  }

  public boolean estaEnComunidad(Comunidad comunidadInvolucradaEnIncidente){
    return comunidadInvolucradaEnIncidente.sameId(getComunidad().getId());
  }

  public void notificarRevisionDeInicidente(Incidente incidente){
     serviciosDeNotificacion.forEach( servicio -> servicio.notificar(incidente.getDescripcion(),"",correo, "Se sugiere revisar el servicio " + incidente.nombre));
  }

}
