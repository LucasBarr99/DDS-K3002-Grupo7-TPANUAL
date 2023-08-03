package Repositorios;

import Entidades.Entidad;
import Incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class RepoEntidades {
  private static RepoEntidades _repoEntidades = null;
  private List<Entidad> entidades = new ArrayList<Entidad>();

  private RepoEntidades() {}

  public static RepoEntidades getInstance() {
    if (_repoEntidades == null) {
      _repoEntidades = new RepoEntidades();
      return _repoEntidades;
    }
    else
      return _repoEntidades;

  }

  public List<Entidad> getEntidades(){
    return entidades;
  }

  public void agregarIncidente(Entidad entidad){
    this.entidades.add(entidad);
  }
}
