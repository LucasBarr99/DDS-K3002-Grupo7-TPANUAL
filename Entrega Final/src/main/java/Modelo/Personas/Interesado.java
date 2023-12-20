package Modelo.Personas;

import Modelo.Comunidades.Miembro;
import Modelo.Incidentes.NotificacionIncidente;
import Persistencia.EntidadPersistente;
import Modelo.Servicios.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Interesado extends EntidadPersistente {

  public String nombre;

  public String correo;

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

  @OneToMany()
  @JoinColumn(name="idInteresado")
  public List<Usuario> usuarios = new ArrayList<Usuario>();


  public void notificar(){}
  public void agregarNotificacionIncidente(NotificacionIncidente notificacionIncidente){}

  public void agregarUsuario(Usuario user){
    this.usuarios.add(user);
  }


  public boolean tieneUsuario(Usuario usuario){
    return usuarios.contains(usuario);
  }


}
