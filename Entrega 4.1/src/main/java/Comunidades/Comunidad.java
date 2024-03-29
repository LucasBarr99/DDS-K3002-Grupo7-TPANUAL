package Comunidades;

import Incidentes.Incidente;
import Persistencia.EntidadPersistente;
import Personas.Usuario;
import Servicios.Servicio;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Comunidades")
public class Comunidad extends EntidadPersistente {

  @OneToMany(cascade = { CascadeType.ALL })
  @JoinColumn(name = "idcomunidad")
  private List<Miembro> miembros;
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "ServicioPorComunidad",
      joinColumns = { @JoinColumn(name = "idcomunidad") },
      inverseJoinColumns = { @JoinColumn(name = "idservicio") }
  )
  private List<Servicio> serviciosDeInteres;
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "idcomunidadadmin")
  private List <Usuario> administradores;


  public Comunidad(List<Miembro> miembros, List<Servicio> serviciosDeInteres, List<Usuario> administradores) {
    this.miembros = miembros;
    this.serviciosDeInteres = serviciosDeInteres;
    this.administradores = administradores;
  }

  public Comunidad(){}


}
