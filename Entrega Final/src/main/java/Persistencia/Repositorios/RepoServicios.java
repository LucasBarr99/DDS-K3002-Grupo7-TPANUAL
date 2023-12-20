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


  public Servicio obtenerServicio(String id) {

    String query = String.format("from Servicio where id='%s'", id);
    return (Servicio) entityManager().createQuery(query).getResultList().get(0);
  }

  public List<Servicio> obtenerServiciosDeComunidad(int idComunidad){
    String query = String.format("select distinct c from Comunidad c inner join c.servicioporcomunidad s where s.idcomunidad ='%s'",idComunidad);
    return entityManager().createQuery(query).getResultList();
  }

}
