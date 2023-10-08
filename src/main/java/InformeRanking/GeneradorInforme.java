package InformeRanking;

import Entidades.Entidad;
import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo")
@Table(name = "CriterioInforme")
public abstract class GeneradorInforme extends EntidadPersistente {
  public abstract Informe generarInforme();

  public GeneradorInforme() {
  }
}
