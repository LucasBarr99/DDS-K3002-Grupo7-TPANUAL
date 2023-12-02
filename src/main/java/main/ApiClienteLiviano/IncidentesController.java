package main.ApiClienteLiviano;


import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.Incidentes.EstadoIncidentes;
import Modelo.Incidentes.Incidente;
import Modelo.Servicios.Servicio;
import Persistencia.Repositorios.RepoComunidades;
import Persistencia.Repositorios.RepoMiembros;
import Persistencia.Repositorios.RepoServicios;
import main.ApiClienteLiviano.dto.IncidenteRequest;
import main.ApiClientePesado.dto.CriterioResponse;
import main.ApiClientePesado.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

@Controller
public class IncidentesController {


  @PostMapping(value="/incidentes/nuevo", consumes = "application/x-www-form-urlencoded")
  public String cargarIncidente(@RequestParam("miembro") String idMiembro,@RequestParam("comunidad") String idComunidad,@ModelAttribute IncidenteRequest newIncidenteRequest){
    System.out.println("[POST] /incidentes/nuevo - Miembro: "+idMiembro+" - Request: "+newIncidenteRequest);

    Comunidad comunidad = RepoComunidades.instance().obtenerComunidad(idComunidad);
    Miembro miembro = RepoMiembros.instance().obtenerMiembro(idMiembro);
    Servicio servicio = RepoServicios.instance().obtenerServicio(newIncidenteRequest.getServicio());

    Incidente incidente = new Incidente("Incidente Miembro" + miembro.getNombre(), Date.valueOf(LocalDate.now()), servicio, newIncidenteRequest.getDescripcion(), EstadoIncidentes.ABIERTO, miembro);

    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = em.getTransaction();

    servicio.agregarIncidente(incidente);

    transaction.begin();
    em.persist(incidente);
    em.merge(servicio);
    transaction.commit();

    comunidad.notificarIncidente(incidente);

    return "redirect:/home";
  }

}
