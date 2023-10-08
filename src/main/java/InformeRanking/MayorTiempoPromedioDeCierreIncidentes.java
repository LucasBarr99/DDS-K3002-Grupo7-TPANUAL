package InformeRanking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MayorTiempoPromedioCierreIncidentes")
public class MayorTiempoPromedioDeCierreIncidentes extends GeneradorInforme {

  @Override
  public Informe generarInforme() {
    return null;
  }
}
