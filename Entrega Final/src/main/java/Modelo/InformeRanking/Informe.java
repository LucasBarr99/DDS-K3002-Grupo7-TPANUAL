package Modelo.InformeRanking;

import Modelo.Entidades.Entidad;
import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "InformeRankings")
public class Informe extends EntidadPersistente {

  private String nombre;
  private String descripcion;
  private Date fechaGeneracion;

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




  public Informe(String nombre, String descripcion, Date fechaGeneracion, List<Entidad> entidades, GeneradorInforme criterio) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.fechaGeneracion = fechaGeneracion;
    this.entidades = entidades;
    this.criterio = criterio;
  }

  public Informe() {

  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Date getFechaGeneracion() {
    return fechaGeneracion;
  }

  public List<Entidad> getEntidades() {
    return entidades;
  }

  public GeneradorInforme getCriterio() {
    return criterio;
  }
}
