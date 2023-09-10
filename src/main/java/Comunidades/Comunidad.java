package Comunidades;

import Incidentes.Incidente;
import Personas.Usuario;
import Servicios.Servicio;

import java.util.List;
@Entity
@Table(name = "Comunidades")
public class Comunidad extends EntidadPersistente {

  @OneToMany(cascade = { CascadeType.ALL })
  @JoinColumn(name = "id_comunidad")
  private List<Miembro> miembros;
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "Servicio_Por_Comunidad",
      joinColumns = { @JoinColumn(name = "id_comunidad") },
      inverseJoinColumns = { @JoinColumn(name = "id_servicio") }
  )
  private List<Servicio> serviciosDeInteres;
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "id_comunidad_admin")
  private List <Usuario> administradores;


  public Comunidad(List<Miembro> miembros, List<Servicio> serviciosDeInteres, List<Usuario> administradores) {
    this.miembros = miembros;
    this.serviciosDeInteres = serviciosDeInteres;
    this.administradores = administradores;
  }

  public Comunidad(){}


}
