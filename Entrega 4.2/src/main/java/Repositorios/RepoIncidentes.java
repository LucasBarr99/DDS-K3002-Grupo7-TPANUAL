package Repositorios;

import Establecimientos.Estacion;
import Incidentes.EstadoIncidentes;
import Incidentes.Incidente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoIncidentes extends Repositorio<Incidente> {

  private static final RepoIncidentes INSTANCE = new RepoIncidentes();
  private List<Incidente> incidentes = new ArrayList<>();

  public static RepoIncidentes instance() {
    return INSTANCE;
  }

  private RepoIncidentes() {
    super("Incidente");
  }

  public void agregarIncidente(Incidente incidente) {
    entityManager().persist(incidente);
  }

  public List<Incidente> obtenerIncidente(String nombre) {

    String query = String.format("from Incidente where nombre='%s'", nombre);
    return entityManager().createQuery(query).getResultList();
  }

  public Incidente obtenerIncidente(int id) {

    String query = String.format("from Incidente where id='%s'", id);
    return (Incidente) entityManager().createQuery(query).getResultList().get(0);
  }

  public Incidente obtenerIncidenteDeMiembro(int idMiembro) {

    String query = String.format("from Incidente where miembro_id='%s'", idMiembro);
    return (Incidente) entityManager().createQuery(query).getResultList().get(0);
  }

  public Incidente obtenerIncidenteDeServicio(int idServicio) {

    String query = String.format("from Incidente where servicioAfectado_id='%s'", idServicio);
    return (Incidente) entityManager().createQuery(query).getResultList().get(0);
  }

}

