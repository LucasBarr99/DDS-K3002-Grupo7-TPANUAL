package Modelo.InformeRanking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GradoImpacto")
public class MayorGradoImpactoProblematicas extends GeneradorInforme{
  @Override
  public Informe generarInforme() {
    return null;
  }

  public MayorGradoImpactoProblematicas(){

  }
  public MayorGradoImpactoProblematicas(String nombre){
    super(nombre);
  }
}
