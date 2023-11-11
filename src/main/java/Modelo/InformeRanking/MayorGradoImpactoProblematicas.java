package Modelo.InformeRanking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MayorGradoImpactoProblematicas")
public class MayorGradoImpactoProblematicas extends GeneradorInforme{
  @Override
  public Informe generarInforme() {
    return null;
  }
}
