package Persistencia;

import Comunidades.Miembro;
import Entidades.Entidad;
import Entidades.Organizacion;
import Incidentes.EstadoIncidentes;
import Incidentes.Incidente;
import Localizaciones.TipoLocalizacion;
import Localizaciones.Ubicacion;
import Servicios.Servicio;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PruebaPersistencia implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    new PruebaPersistencia().run();
  }

  void run(){
    withTransaction(() -> {
      List<Ubicacion> ubicaciones = new ArrayList<>();

      Ubicacion ubicacion1 = new Ubicacion(TipoLocalizacion.MUNICIPIO,"Cochabamba", 1,1);

      ubicaciones.add(ubicacion1);


      Entidad org = new Organizacion("Cacho S.A", ubicaciones,null, null );

      Servicio servicio1= new Servicio("Baño de Hombres",null, org);
      Servicio servicio2= new Servicio("Baño de Hombres",null, org);


      List<Servicio> servicios = new ArrayList<>();

      servicios.add(servicio1);
      servicios.add(servicio2);


      org.setServicios(servicios);

      Miembro miembro1 = new Miembro();

      Incidente incidente1 = new Incidente("Incidente1", LocalDate.of(2023,9,25),LocalDate.of(2023,9,26), servicio1, "Se revento un banitory", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente2 = new Incidente("Incidente2", LocalDate.of(2023,9,25),LocalDate.of(2023,9,26), servicio1, "Detonaron un inhonodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente3 = new Incidente("Incidente3", LocalDate.of(2023,9,25),LocalDate.of(2023,9,26), servicio1, "Exploto un bide", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente4 = new Incidente("Incidente4", LocalDate.of(2023,9,25),LocalDate.of(2023,9,26), servicio1, "Hay uno durmiendo en el inhonodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente5 = new Incidente("Incidente5", LocalDate.of(2023,9,25),LocalDate.of(2023,9,26), servicio1, "Reventaron los azulejos de los baños", EstadoIncidentes.CERRADO, miembro1);

      persist(org);
      servicios.forEach(servicio -> persist(servicio));
      persist(miembro1);
      persist(incidente1);
      persist(incidente2);
      persist(incidente3);
      persist(incidente4);
      persist(incidente5);
      persist(incidente6);
      persist(incidente7);
      persist(incidente8);
      persist(incidente9);
      persist(incidente10);
      persist(incidente11);
      persist(incidente12);
      persist(incidente13);
      persist(incidente14);


    });
  }

}
