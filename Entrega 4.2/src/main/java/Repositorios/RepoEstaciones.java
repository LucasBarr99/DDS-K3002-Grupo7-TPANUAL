package Repositorios;

import Establecimientos.Estacion;

import java.util.ArrayList;
import java.util.List;

public class RepoEstaciones extends Repositorio<Estacion> {
  private static final RepoEstaciones INSTANCE = new RepoEstaciones();
  private List<Estacion> estaciones = new ArrayList<>();

  public static RepoEstaciones instance() {
    return INSTANCE;
  }

  private RepoEstaciones() {
    super("Estacion");
  }

  public void agregarEstacion(Estacion estacion) {
    entityManager().persist(estacion);
  }

  public List<Estacion> obtenerEstacion(String nombre) {

    String query = String.format("from Estacion where nombre='%s'", nombre);
    return entityManager().createQuery(query).getResultList();
  }

  public Estacion obtenerEstacion(int id) {

    String query = String.format("from Estacion where id='%s'", id);
    return (Estacion) entityManager().createQuery(query).getResultList().get(0);
  }


}
