package Modelo.Personas;

import Modelo.Comunidades.Miembro;
import Modelo.Incidentes.NotificacionIncidente;
import Persistencia.EntidadPersistente;
import Modelo.Servicios.Servicio;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Interesado extends EntidadPersistente {

  public String nombre;

  public String correo;


  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "idinteresado")
  private List<Miembro> membresias;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "ServicioPorInteresado",
      joinColumns = { @JoinColumn(name = "idinteresado") },
      inverseJoinColumns = { @JoinColumn(name = "idservicio") }
  )
  public List<Servicio> serviciosDeInteres;
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "idinteresado")
  public List<NotificacionIncidente> notificacionesPendientes;
  public void notificar(){}
  public void agregarNotificacionIncidente(NotificacionIncidente notificacionIncidente){}
}
