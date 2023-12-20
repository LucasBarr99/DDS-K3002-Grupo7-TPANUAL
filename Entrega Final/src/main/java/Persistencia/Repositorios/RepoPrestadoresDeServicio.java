package Persistencia.Repositorios;

import Modelo.Entidades.Entidad;
import Modelo.PrestadoresDeServicios.PrestadorDeServicio;

import java.util.ArrayList;
import java.util.List;

public class RepoPrestadoresDeServicio extends Repositorio<PrestadorDeServicio>{

    private static final Persistencia.Repositorios.RepoPrestadoresDeServicio INSTANCE = new Persistencia.Repositorios.RepoPrestadoresDeServicio();
    private List<PrestadorDeServicio> prestadoresDeServicio = new ArrayList<>();

    public static Persistencia.Repositorios.RepoPrestadoresDeServicio instance() {
      return INSTANCE;
    }

    private RepoPrestadoresDeServicio() {
      super("PrestadorDeServicio");
    }

    public void agregarPrestadorDeServicio(PrestadorDeServicio prestadorDeServicio) {
      entityManager().persist(prestadorDeServicio);
    }

    public List<PrestadorDeServicio> obtenerPrestadorDeServicio(String nombre) {

      String query = String.format("from PrestadorDeServicio where nombre='%s'", nombre);
      return entityManager().createQuery(query).getResultList();
    }

    public PrestadorDeServicio obtenerPrestadorDeServicio(int id) {

      String query = String.format("from PrestadorDeServicio where id='%s'", id);
      return (PrestadorDeServicio) entityManager().createQuery(query).getResultList().get(0);
    }

}
