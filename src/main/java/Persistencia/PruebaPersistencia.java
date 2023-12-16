package Persistencia;

import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.Comunidades.TipoMiembro;
import Modelo.Entidades.Entidad;
import Modelo.Entidades.Organizacion;
import Modelo.Incidentes.EstadoIncidentes;
import Modelo.Incidentes.Incidente;
import Modelo.Incidentes.RangoHorarioNotificacion;
import Modelo.InformeRanking.*;
import Modelo.Localizaciones.TipoLocalizacion;
import Modelo.Localizaciones.Ubicacion;
import Modelo.Personas.Interesado;
import Modelo.Personas.Persona;
import Modelo.Personas.TipoUsuario;
import Modelo.Personas.Usuario;
import Modelo.Servicios.Servicio;
import ServiciosExternos.Notifcaciones.Notificacion;
import ServiciosExternos.Notifcaciones.NotificacionJavaMail;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PruebaPersistencia implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    new PruebaPersistencia().run();
  }

  void run(){
    withTransaction(() -> {

      Notificacion servicioMail = new NotificacionJavaMail();
      List<Ubicacion> ubicaciones = new ArrayList<>();

      List<Ubicacion> ubicacionesOrg2 = new ArrayList<>();
      List<Ubicacion> ubicacionesOrg3 = new ArrayList<>();
      Ubicacion ubicacion1 = new Ubicacion(TipoLocalizacion.MUNICIPIO,"Cochabamba", 1,1);
      Ubicacion ubicacion2 = new Ubicacion(TipoLocalizacion.PROVINCIA, "Chimborazo",1,1);
      Ubicacion ubicacion3 =  new Ubicacion(TipoLocalizacion.DEPARTAMENTO, "Carachapay y Camarones", 2, 2);

      ubicacionesOrg2.add(ubicacion2);
      ubicacionesOrg3.add(ubicacion3);

      ubicaciones.add(ubicacion1);



      Usuario usuario1 = new Usuario("Lucas","TpAnual_028", TipoUsuario.ADMINCOMUNIDAD);
      Usuario usuario2 = new Usuario("Tomas","TpAnual_029", TipoUsuario.ADMINPRESTADORA);
      Usuario usuario3 = new Usuario("Franco","TpAnual_030", TipoUsuario.BASICO);
      Usuario usuario4 = new Usuario("Santiago","TpAnual_031", TipoUsuario.BASICO);
      Usuario usuario5 = new Usuario("lucas.barrientos2899","TpAnual_029", TipoUsuario.ADMINPRESTADORA);

      Interesado persona1 = new Persona("Lucas",ubicacion2,"Lucas@gmail.com","12345678", new ArrayList<>(), new ArrayList<>());
      persona1.agregarUsuario(usuario1);

      List<Usuario> adminsComunidad1 = new ArrayList<>();

      adminsComunidad1.add(usuario1);


      Comunidad comunidad1 = new Comunidad("Comunidad 1",new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
      Comunidad comunidad2 = new Comunidad("Comunidad 2",new ArrayList<>(), new ArrayList<>(),adminsComunidad1);

      List<Notificacion> serviciosNotificacionMiembros = new ArrayList<>();

      serviciosNotificacionMiembros.add(servicioMail);

      Miembro miembro1 = new Miembro("Cacho", "Antune", "lucas.barrientos2899@gmail.com",comunidad1,TipoMiembro.DE_SERVICIO, serviciosNotificacionMiembros);
      usuario2.agregarMembresia(miembro1);
      comunidad1.agregarMiembro(miembro1);

      Miembro miembro2 = new Miembro("Pepe", "Antune", "lucasmarcelobatalla01@gmail.com",comunidad1,TipoMiembro.OBSERVADOR, serviciosNotificacionMiembros);
      usuario4.agregarMembresia(miembro2);
      comunidad1.agregarMiembro(miembro2);

      Miembro miembro3 = new Miembro("Tito", "Antune", "lucas.barrientos2899@gmail.com",comunidad1,TipoMiembro.DE_SERVICIO, serviciosNotificacionMiembros);

      Miembro miembro4 = new Miembro("Raul", "Alutti", "Raul@gmail.com",comunidad2,TipoMiembro.DE_SERVICIO, new ArrayList<>());

      usuario1.agregarMembresia(miembro3);
      usuario1.agregarMembresia(miembro4);
      comunidad1.agregarMiembro(miembro3);
      comunidad2.agregarMiembro(miembro4);

      Entidad org = new Organizacion("Cacho S.A", ubicaciones,null, null );
      Entidad org2 = new Organizacion("UTN S.A", ubicacionesOrg2,null, null );
      Entidad org3 = new Organizacion("DDS S.A", ubicacionesOrg3,null, null );

      Servicio servicio1= new Servicio("Baño de Hombres Cacho S.A",null, org);
      Servicio servicio2= new Servicio("Baño de Mujeres UTN S.A",null, org2);
      Servicio servicio3= new Servicio("Baño de Mujeres DDS S.A",null, org3);


      List<Servicio> serviciosOrg1 = new ArrayList<>();
      List<Servicio> serviciosOrg2 = new ArrayList<>();
      List<Servicio> serviciosOrg3 = new ArrayList<>();

      serviciosOrg1.add(servicio1);
      serviciosOrg2.add(servicio2);
      serviciosOrg3.add(servicio3);

      comunidad1.agregarServicio(servicio1);
      comunidad1.agregarServicio(servicio2);
      comunidad1.agregarServicio(servicio3);

      org.setServicios(serviciosOrg1);
      org2.setServicios(serviciosOrg2);
      org3.setServicios(serviciosOrg3);

      Incidente incidente1 = new Incidente("Incidente1", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Se revento un banitory", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente2 = new Incidente("Incidente2", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Detonaron un inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente3 = new Incidente("Incidente3", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Exploto un bidet", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente4 = new Incidente("Incidente4", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Hay uno durmiendo en el inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente5 = new Incidente("Incidente5", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio1, "Reventaron los azulejos de los baños", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente6 = new Incidente("Incidente6", Date.valueOf(LocalDate.of(2023,9,25)),null, servicio1, "El lavamanos gotea", EstadoIncidentes.ABIERTO, miembro1);
      Incidente incidente15 = new Incidente("Incidente7", Date.valueOf(LocalDate.of(2023,9,22)),null, servicio1, "Detonaron el baño", EstadoIncidentes.ABIERTO, miembro1);
      Incidente incidente16 = new Incidente("Incidente16", Date.valueOf(LocalDate.of(2023,9,24)),null, servicio1, "Reventaron el espejo del baño", EstadoIncidentes.ABIERTO, miembro1);

      Incidente incidente7 = new Incidente("Incidente6", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio2, "Hay uno durmiendo en el inonodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente8 = new Incidente("Incidente7", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio2, "Reventaron los azulejos de los baños", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente9 = new Incidente("Incidente7", Date.valueOf(LocalDate.of(2023,9,25)),null, servicio2, "El espejo esta roto", EstadoIncidentes.ABIERTO, miembro1);


      Incidente incidente10 = new Incidente("Incidente1", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Se revento un banitory", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente11 = new Incidente("Incidente2", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Detonaron un inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente12 = new Incidente("Incidente3", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Exploto un bidet", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente13 = new Incidente("Incidente4", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Hay uno durmiendo en el inodoro", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente14 = new Incidente("Incidente5", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "Reventaron los azulejos de los baños", EstadoIncidentes.CERRADO, miembro1);
      Incidente incidente17 = new Incidente("Incidente6", Date.valueOf(LocalDate.of(2023,9,25)),Date.valueOf(LocalDate.of(2023,9,26)), servicio3, "El lavamanos gotea", EstadoIncidentes.CERRADO, miembro1);

      Incidente incidente18 = new Incidente("Incidente6", Date.valueOf(LocalDate.of(2023,9,25)),null, servicio3, "Se afanaron un inodoro", EstadoIncidentes.ABIERTO, miembro1);

      GeneradorInforme criterio1 =  new MayorTiempoPromedioDeCierreIncidentes("Tiempo promedio de cierre de Incidentes");
      GeneradorInforme criterio2 =  new MayorCantidadDeIncidentesReportadosSemana("Mayor cantidad de Incidentes reportados");
      GeneradorInforme criterio3 =  new MayorGradoImpactoProblematicas("Grado de impacto");



      List<Entidad> posicionesInforme1 = new ArrayList<>();
      posicionesInforme1.add(org);
      posicionesInforme1.add(org2);
      posicionesInforme1.add(org3);
      Informe informe1 = new Informe("Informe1", "Informe1 generado el 16/11/23",Date.valueOf(LocalDate.now()), posicionesInforme1, criterio1);

      List<Entidad> posicionesInforme2 = new ArrayList<>();
      posicionesInforme2.add(org3);
      posicionesInforme2.add(org2);
      posicionesInforme2.add(org);
      Informe informe2 = new Informe("Informe2", "Informe2 generado el 16/11/23",Date.valueOf(LocalDate.now()), posicionesInforme2, criterio2);


      List<Entidad> posicionesInforme3 = new ArrayList<>();
      posicionesInforme3.add(org3);
      posicionesInforme3.add(org);
      posicionesInforme3.add(org2);
      Informe informe3 = new Informe("Informe3", "Informe3 generado el 16/11/23",Date.valueOf(LocalDate.now()), posicionesInforme3, criterio3);



      persist(servicioMail);
      persist(usuario1);
      persist(usuario2);
      persist(usuario3);
      persist(usuario4);
      persist(usuario5);

      persist(miembro1);
      persist(miembro2);
      persist(miembro3);
      persist(comunidad1);
      persist(comunidad2);
      persist(persona1);

      persist(org);
      persist(org2);
      persist(org3);
      serviciosOrg1.forEach(this::persist);
      serviciosOrg2.forEach(this::persist);
      serviciosOrg3.forEach(this::persist);
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
      persist(incidente15);
      persist(incidente16);
      persist(incidente17);
      persist(incidente18);
      persist(criterio1);
      persist(criterio2);
      persist(criterio3);
      persist(informe1);
      persist(informe2);
      persist(informe3);



    });
  }

}
