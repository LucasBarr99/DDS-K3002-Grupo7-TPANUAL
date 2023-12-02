package Modelo.Comunidades;

import Modelo.Incidentes.Incidente;
import Modelo.Personas.Persona;
import Modelo.Personas.Usuario;
import Persistencia.EntidadPersistente;
import Modelo.Servicios.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Comunidades")
public class Comunidad extends EntidadPersistente {
  private String nombre;
  @OneToMany(cascade = { CascadeType.ALL })
  @JoinColumn(name = "idcomunidad")
  private List<Miembro> miembros;
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "ServicioPorComunidad",
      joinColumns = { @JoinColumn(name = "idcomunidad") },
      inverseJoinColumns = { @JoinColumn(name = "idservicio") }
  )
  private List<Servicio> serviciosDeInteres = new ArrayList<>();
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "idcomunidadadmin")
  private List <Usuario> administradores;



  public Comunidad(String nombre,List<Miembro> miembros, List<Servicio> serviciosDeInteres, List<Usuario> ListaAdministradores) {
    this.nombre = nombre;
    this.miembros = miembros;
    this.serviciosDeInteres = serviciosDeInteres;
    this.administradores = ListaAdministradores;
  }

  public Comunidad(){}


  public void agregarMiembro(Miembro miembro){
    miembros.add(miembro);
  }


  public List<Miembro> getMiembros() {
    return miembros;
  }

  public List<Servicio> getServiciosDeInteres() {
    return serviciosDeInteres;
  }

  public List<Usuario> getAdministradores() {
    return administradores;
  }

  public boolean tieneMiembros(List<Miembro> miembros1){
    return miembros.stream().anyMatch(miembro -> miembros1.contains(miembro));
  }

  public String getNombre() {
    return nombre;
  }

  public void agregarServicio(Servicio servicio){
    serviciosDeInteres.add(servicio);
  }

  public  void agregarServicios(List<Servicio> servicios){
    servicios.forEach(servicio -> serviciosDeInteres.add(servicio));
  }

  public void notificarIncidente(Incidente incidente){
    miembros.forEach(miembro -> miembro.notificarIncidente(incidente));
  }

}
