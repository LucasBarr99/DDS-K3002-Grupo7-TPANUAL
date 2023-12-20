package main.ApiClientePesado.Servicios;


import Modelo.InformeRanking.GeneradorInforme;
import Modelo.InformeRanking.Informe;
import Persistencia.Repositorios.RepoCriteriosInforme;
import Persistencia.Repositorios.RepoInformesRanking;
import main.ApiClientePesado.dto.CriterioResponse;
import main.ApiClientePesado.dto.InformeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRankingIncidentes {

  public CriterioResponse buscarCriterios(){
    List<GeneradorInforme> criterios = RepoCriteriosInforme.instance().obtenerCriterios();
    return new CriterioResponse(criterios);
  }

  public InformeResponse buscarInforme(int criterio){
    Informe informe = RepoInformesRanking.instance().obtenerInformeRecientePorCriterio(criterio);
    return new InformeResponse(informe.getNombre(), informe.getDescripcion(), informe.getFechaGeneracion(), informe.getEntidades(), criterio);
  }
}
