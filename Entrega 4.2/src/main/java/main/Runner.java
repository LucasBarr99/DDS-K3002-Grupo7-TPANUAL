package main;
import Establecimientos.Estacion;
import Repositorios.RepoEstaciones;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Runner {

  public static void main(String[] args){


    Estacion estacion = new Estacion("Cochabamba", null,null);

    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = em.getTransaction();

    transaction.begin();
    em.persist(estacion);
    transaction.commit();

    List<Estacion> estaciones = RepoEstaciones.instance().obtenerEstacion("Cochabamba");

    Estacion estacion1 = estaciones.get(0);


    System.out.println(estacion1.getId());

    System.out.println(estacion1.getNombre());



  }
}