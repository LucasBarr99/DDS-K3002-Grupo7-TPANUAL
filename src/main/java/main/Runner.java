package main;
import Modelo.Establecimientos.Estacion;
import Modelo.Localizaciones.TipoLocalizacion;
import Modelo.Localizaciones.Ubicacion;
import Modelo.PrestadoresDeServicios.Empresa;
import Modelo.PrestadoresDeServicios.PrestadorDeServicio;
import Persistencia.Repositorios.RepoEstaciones;
import Persistencia.Repositorios.RepoPrestadoresDeServicio;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Runner {

  public static void main(String[] args){

    Ubicacion ubicacion1 = new Ubicacion(TipoLocalizacion.MUNICIPIO,"Cochabamba", 1,1);

    Estacion estacion = new Estacion("Cochabamba", ubicacion1,null);
    PrestadorDeServicio prestador = new Empresa("UTN");

    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = em.getTransaction();

    transaction.begin();
    em.persist(estacion);
    transaction.commit();

    RepoPrestadoresDeServicio repo = RepoPrestadoresDeServicio.instance();

    repo.agregarPrestadorDeServicio(prestador);

    List<Estacion> estaciones = RepoEstaciones.instance().obtenerEstacion("Cochabamba");

    Estacion estacion1 = estaciones.get(0);


    System.out.println(estacion1.getId());

    System.out.println(estacion1.getNombre());



  }
}