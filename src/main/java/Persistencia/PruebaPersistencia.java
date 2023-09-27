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

import java.sql.Date;
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
      Entidad org2 = new Organizacion("UTN S.A", ubicaciones,null, null );
      Entidad org3 = new Organizacion("DDS S.A", ubicaciones,null, null );

      Servicio servicio1= new Servicio("Baño de Hombres",null, org);
      Servicio servicio2= new Servicio("Baño de Mujeres",null, org2);
      Servicio servicio3= new Servicio("Baño de Mujeres",null, org3);


      List<Servicio> serviciosOrg1 = new ArrayList<>();
      List<Servicio> serviciosOrg2 = new ArrayList<>();
      List<Servicio> serviciosOrg3 = new ArrayList<>();

      serviciosOrg1.add(servicio1);
      serviciosOrg2.add(servicio2);
      serviciosOrg3.add(servicio3);



      org.setServicios(serviciosOrg1);

      org2.setServicios(serviciosOrg2);

      Miembro miembro1 = new Miembro();

      Incidente incidente1 = new Incidente("Incidente1", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Se revento un banitory", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente2 = new Incidente("Incidente2", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Detonaron un inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente3 = new Incidente("Incidente3", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Exploto un bidet", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente4 = new Incidente("Incidente4", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Hay uno durmiendo en el inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente5 = new Incidente("Incidente5", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Reventaron los azulejos de los baños", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente6 = new Incidente("Incidente6", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "El lavamanos gotea", EstadoIncidentes.ABIERTO, miembro1);

      Incidente incidente7 = new Incidente("Incidente6", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio2, "Hay uno durmiendo en el inonodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente8 = new Incidente("Incidente7", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio2, "Reventaron los azulejos de los baños", EstadoIncidentes.CERRADO, miembro1);

      Incidente incidente9 = new Incidente("Incidente1", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Se revento un banitory", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente10 = new Incidente("Incidente2", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Detonaron un inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente11 = new Incidente("Incidente3", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Exploto un bidet", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente12 = new Incidente("Incidente4", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Hay uno durmiendo en el inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente13 = new Incidente("Incidente5", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Reventaron los azulejos de los baños", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente14 = new Incidente("Incidente6", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "El lavamanos gotea", EstadoIncidentes.CERRADO, miembro1);

      persist(org);
      persist(org2);
      persist(org3);
      serviciosOrg1.forEach(servicio -> persist(servicio));
      serviciosOrg2.forEach(servicio -> persist(servicio));
      serviciosOrg3.forEach(servicio -> persist(servicio));
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
