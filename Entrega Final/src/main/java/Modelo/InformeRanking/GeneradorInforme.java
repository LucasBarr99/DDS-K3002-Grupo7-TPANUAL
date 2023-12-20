package Modelo.InformeRanking;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Table(name = "CriterioInforme")
public abstract class GeneradorInforme extends EntidadPersistente {

  public String nombre;
  public abstract Informe generarInforme();

  public GeneradorInforme() {
  }
  public GeneradorInforme(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }
}
