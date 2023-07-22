package Repositorios;

import Entidades.Entidad;
import Establecimientos.Establecimiento;
import Incidentes.EstadoIncidentes;
import Incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class RepoEstablecimientos {
  private static RepoEstablecimientos _repoEstablecimientos = null;
  private List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();

  private RepoEstablecimientos() {}

  public static RepoEstablecimientos getInstance() {
    if (_repoEstablecimientos == null) {
      _repoEstablecimientos = new RepoEstablecimientos();
      return _repoEstablecimientos;
    }
    else
      return _repoEstablecimientos;

  }

  public List<Establecimiento> getEstablecimientos(){
    return establecimientos;
  }

  public void agregarIncidente(Establecimiento establecimiento){
    this.establecimientos.add(establecimiento);
  }
}
