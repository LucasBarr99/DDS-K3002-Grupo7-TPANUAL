package Personas;

import Comunidades.Miembro;
import Incidentes.Incidente;
import Incidentes.NotificacionIncidente;
import Persistencia.EntidadPersistente;
import Servicios.Servicio;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Interesado extends EntidadPersistente {

  public String nombre;

  public String correo;


  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "id_interesado")
  private List<Miembro> membresias;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "Servicio_Por_Interesado",
      joinColumns = { @JoinColumn(name = "id_interesado") },
      inverseJoinColumns = { @JoinColumn(name = "id_servicio") }
  )
  public List<Servicio> serviciosDeInteres;
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "id_interesado")
  public List<NotificacionIncidente> notificacionesPendientes;
  public void notificar(){}
  public void agregarNotificacionIncidente(NotificacionIncidente notificacionIncidente){}
}
