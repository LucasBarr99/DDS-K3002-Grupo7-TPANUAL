package Modelo.InformeRanking;

import Modelo.Entidades.Entidad;
import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "InformeRankings")
public class Informe extends EntidadPersistente {

  private String nombre;
  private String descripcion;
  private LocalDateTime fechaGeneracion;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "InformePorEntidad",
      joinColumns = { @JoinColumn(name = "idinforme") },
      inverseJoinColumns = { @JoinColumn(name = "identidad") }
  )
  @OrderColumn(name = "posicion")
  private List<Entidad> entidades;

  @ManyToOne
  @JoinColumn(name = "idCriterio")
  private GeneradorInforme criterio;




  public Informe(String nombre, String descripcion, LocalDateTime fechaGeneracion, List<Entidad> entidades) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.fechaGeneracion = fechaGeneracion;
    this.entidades = entidades;
  }

  public Informe() {

  }
}
