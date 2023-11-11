package Persistencia.Repositorios;

import Modelo.Personas.Interesado;

import java.util.ArrayList;
import java.util.List;

public class RepoInteresados extends Repositorio<Interesado> {
  private static final RepoInteresados INSTANCE = new RepoInteresados();
  private List<Interesado> estaciones = new ArrayList<>();

  public static RepoInteresados instance() {
    return INSTANCE;
  }

  private RepoInteresados() {
    super("Interesado");
  }

  public void agregarInteresado(Interesado interesado) {
    entityManager().persist(interesado);
  }

  public List<Interesado> obtenerInteresado(String nombre) {

    String query = String.format("from Interesado where nombre='%s'", nombre);
    return entityManager().createQuery(query).getResultList();
  }

  public Interesado obtenerInteresado(int id) {

    String query = String.format("from Interesado where id='%s'", id);
    return (Interesado) entityManager().createQuery(query).getResultList().get(0);
  }
  @Override
  public List<Interesado> todos()  {

    String query = String.format("from Interesado i Join Usuario u on i.id = u.id Join Invitado in on i.id = in.id");
    return entityManager().createQuery(query).getResultList();
  }

}
