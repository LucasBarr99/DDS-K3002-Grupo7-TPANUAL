package Modelo.InformeRanking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MayorIncidentesReportadosSemana")
public class MayorCantidadDeIncidentesReportadosSemana extends GeneradorInforme{
  @Override
  public Informe generarInforme() {
    return null;
  }

  public MayorCantidadDeIncidentesReportadosSemana(){

  }
}
