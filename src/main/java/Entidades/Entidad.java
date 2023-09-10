package Entidades;

import Incidentes.NotificacionIncidente;
import Persistencia.EntidadPersistente;
import Personas.Interesado;
import Personas.Usuario;
import Incidentes.Incidente;
import Localizaciones.Ubicacion;
import Servicios.Servicio;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoEntidad")
@Table(name = "Entidad")
public class Entidad extends EntidadPersistente {
  @Column(name = "NombreEntidad")
  String nombre;

  @OneToMany(cascade = { CascadeType.ALL })
  @JoinColumn(name = "idEstablecimiento")
  List<Servicio> servicios;

  @ElementCollection
  List<Ubicacion> ubicacion;

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

  public Entidad(){}
  public void reportarIncidente(Incidente incidente){
    interesados.forEach(interesado -> interesado.agregarNotificacionIncidente(new NotificacionIncidente(incidente, incidente.nombre, incidente.descripcion)));
  }

  public void notificarInteresados(){
    interesados.forEach(interesado -> interesado.notificar());
  }
}
