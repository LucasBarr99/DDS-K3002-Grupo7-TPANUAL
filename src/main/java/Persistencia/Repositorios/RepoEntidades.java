package Persistencia.Repositorios;

import Modelo.Entidades.Entidad;

import java.util.ArrayList;
import java.util.List;

public class RepoEntidades extends Repositorio<Entidad> {
  private static final RepoEntidades INSTANCE = new RepoEntidades();
  private List<Entidad> entidades = new ArrayList<>();

  public static RepoEntidades instance() {
    return INSTANCE;
  }

  private RepoEntidades() {
    super("Entidad");
  }

  public void agregarEntidad(Entidad entidad) {
    entityManager().persist(entidad);
  }

  public List<Entidad> obtenerEntidad(String nombre) {

    String query = String.format("from Entidad where nombre='%s'", nombre);
    return entityManager().createQuery(query).getResultList();
  }

  public Entidad obtenerEntidad(int id) {

    String query = String.format("from Entidad where id='%s'", id);
    return (Entidad) entityManager().createQuery(query).getResultList().get(0);
  }
}
