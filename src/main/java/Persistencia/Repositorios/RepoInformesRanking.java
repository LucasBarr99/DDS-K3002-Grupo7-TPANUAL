package Persistencia.Repositorios;

import Modelo.InformeRanking.Informe;
import Modelo.Servicios.Servicio;

import java.util.ArrayList;
import java.util.List;

public class RepoInformesRanking extends Repositorio<Informe>{
  private static final RepoInformesRanking INSTANCE = new RepoInformesRanking();
  private List<RepoInformesRanking> servicios = new ArrayList<>();

  public static RepoInformesRanking instance() {
    return INSTANCE;
  }

  private RepoInformesRanking() {
    super("InformeRankings");
  }

  public void agregarInforme(Informe informe) {
    entityManager().persist(informe);
  }


  public Informe obtenerInformeRecientePorCriterio(int idCriterio) {

    String query = String.format("from Informe where DATEDIFF(now(), fechaGeneracion) < 8 and idCriterio='%s'", idCriterio);
    return (Informe) entityManager().createQuery(query).getResultList().get(0);
  }





}
