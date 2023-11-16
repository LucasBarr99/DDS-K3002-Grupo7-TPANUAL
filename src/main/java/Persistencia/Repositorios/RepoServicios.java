package Persistencia.Repositorios;

import Modelo.Personas.Usuario;
import Modelo.Servicios.Servicio;

import java.util.ArrayList;
import java.util.List;

public class RepoServicios extends Repositorio<Servicio>{
  private static final RepoServicios INSTANCE = new RepoServicios();
  private List<Servicio> servicios = new ArrayList<>();

  public static RepoServicios instance() {
    return INSTANCE;
  }

  private RepoServicios() {
    super("Servicios");
  }

  public void agregarServicio(Servicio servicio) {
    entityManager().persist(servicio);
  }


  public Servicio obtenerServicio(int id) {

    String query = String.format("from Servicio where id='%s'", id);
    return (Servicio) entityManager().createQuery(query).getResultList().get(0);
  }

}
