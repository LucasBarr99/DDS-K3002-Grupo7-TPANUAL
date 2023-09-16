package Repositorios;

import Entidades.Entidad;
import Establecimientos.Establecimiento;
import Incidentes.EstadoIncidentes;
import Incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class RepoEstablecimientos extends Repositorio<Establecimiento> {
  private static final RepoEstablecimientos INSTANCE = new RepoEstablecimientos();
  private List<Establecimiento> entidades = new ArrayList<>();

  public static RepoEstablecimientos instance() {
    return INSTANCE;
  }

  private RepoEstablecimientos() {
    super("Establecimiento");
  }

  public void agregarEstablecimiento(Establecimiento establecimiento) {
    entityManager().persist(establecimiento);
  }

  public List<Establecimiento> obtenerEstablecimiento(String nombre) {

    String query = String.format("from Establecimiento where nombre='%s'", nombre);
    return entityManager().createQuery(query).getResultList();
  }

  public Establecimiento obtenerEstablecimiento(int id) {

    String query = String.format("from Establicimiento where id='%s'", id);
    return (Establecimiento) entityManager().createQuery(query).getResultList().get(0);
  }
}
