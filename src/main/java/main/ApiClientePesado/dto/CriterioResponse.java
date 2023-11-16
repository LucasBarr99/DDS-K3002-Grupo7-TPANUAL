package main.ApiClientePesado.dto;

import Modelo.InformeRanking.GeneradorInforme;
import Modelo.InformeRanking.MayorGradoImpactoProblematicas;
import com.google.protobuf.GeneratedMessage;

import java.util.List;

public class CriterioResponse {

  List<GeneradorInforme> criterios;

  public CriterioResponse(){

  }
  public CriterioResponse(List<GeneradorInforme> criterios) {
    this.criterios = criterios;
  }

  public List<GeneradorInforme> getCriterios() {
    return criterios;
  }

  public void setCriterios(List<GeneradorInforme> criterios) {
    this.criterios = criterios;
  }
}
