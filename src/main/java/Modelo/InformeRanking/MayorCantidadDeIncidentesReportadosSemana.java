package Modelo.InformeRanking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cantidad")
public class MayorCantidadDeIncidentesReportadosSemana extends GeneradorInforme{
  @Override
  public Informe generarInforme() {
    return null;
  }

  public MayorCantidadDeIncidentesReportadosSemana(){

  }

  public MayorCantidadDeIncidentesReportadosSemana(String nombre){
    super(nombre);
  }
}
