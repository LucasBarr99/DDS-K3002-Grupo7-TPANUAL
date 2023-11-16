package Persistencia.Repositorios;

import Modelo.InformeRanking.GeneradorInforme;
import Modelo.InformeRanking.Informe;

import java.util.ArrayList;
import java.util.List;

public class RepoCriteriosInforme extends Repositorio<GeneradorInforme>{

  private static final RepoCriteriosInforme INSTANCE = new RepoCriteriosInforme();
  private List<RepoCriteriosInforme> criterios = new ArrayList<>();

  public static RepoCriteriosInforme instance() {
    return INSTANCE;
  }

  private RepoCriteriosInforme() {
    super("CriterioInforme");
  }

  public void agregarCriterio(GeneradorInforme criterio) {
    entityManager().persist(criterio);
  }


  public List<GeneradorInforme> obtenerCriterios() {

    String query = String.format("from GeneradorInforme");
    return entityManager().createQuery(query).getResultList();
  }

}
