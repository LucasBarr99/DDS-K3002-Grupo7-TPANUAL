package Repositorios;

import Incidentes.EstadoIncidentes;
import Incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoIncidentes {

  private static RepoIncidentes _repoIncidentes = null;
  private List<Incidente> incidentes = new ArrayList<Incidente>();

  private RepoIncidentes() {}

  public static RepoIncidentes getInstance() {
    if (_repoIncidentes == null) {
      _repoIncidentes = new RepoIncidentes();
      return _repoIncidentes;
    }
    else
      return _repoIncidentes;

  }

  public void agregarIncidente(Incidente incidente){
    this.incidentes.add(incidente);
  }

  public  List<Incidente> consultaIncidentesPorEstado(EstadoIncidentes estado){
    return this.incidentes.stream().filter(incidente -> incidente.getEstado() == estado).collect(Collectors.toList());
  }
}
