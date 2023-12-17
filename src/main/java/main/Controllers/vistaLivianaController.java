package main.Controllers;


import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.Entidades.Entidad;
import Modelo.Incidentes.EstadoIncidentes;
import Modelo.Incidentes.Incidente;
import Modelo.InformeRanking.GeneradorInforme;
import Modelo.InformeRanking.Informe;
import Modelo.Personas.Usuario;
import Persistencia.Repositorios.*;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import main.ApiClientePesado.SesionManager;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@Controller
public class vistaLivianaController {
  private final Handlebars handlebars = new Handlebars();

  /*@GetMapping(value = "/homeLiviano", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> home() throws IOException {
    Map<String, Object> model = new HashMap<>();

    Template template = handlebars.compile("/templates/prueba");

    String html = template.apply(model);
    return ResponseEntity.status(200).body(html);
  }*/

  public void limpiarEntityManager(){
    PerThreadEntityManagers.getEntityManager();
    PerThreadEntityManagers.closeEntityManager();
  }

  @GetMapping(value = "/aperturaIncidentes", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> aperturaIncidentes(@RequestParam("sesion") String idSesion,@RequestParam(value="comunidad", required=false) String idComunidad) throws IOException {

    Map<String, Object> model = new HashMap<>();
    System.out.println(" idSesion: "+idSesion);
    SesionManager sesionManager = SesionManager.get();
    Map<String, Object> atributos = sesionManager.obtenerAtributos(idSesion);
    Usuario user = (Usuario) atributos.get("usuario");
    //Usuario user = RepoUsuarios.instance().obtenerUsuario(nombreUsuario);
    List<Miembro> membresiasUsuario = RepoMiembros.instance().obtenerMembresiasUsuario(user.getId());

    List<Comunidad> comunidades = RepoComunidades.instance().obtenerTodos();

    List<Comunidad> comunidadesUsuario = comunidades.stream().filter(comunidad -> comunidad.tieneMiembros(membresiasUsuario)).toList();
    model.put("idComunidadSeleccionada",idComunidad);

    if(idComunidad == null){
      model.put("comunidades", comunidadesUsuario);
      Comunidad comunidad1 = comunidadesUsuario.get(0);
      model.put("servicios", comunidad1.getServiciosDeInteres());
    }
    else {
      Comunidad comunidadSeleccionada = comunidades.stream().filter(comunidad -> Integer.valueOf(idComunidad).equals(comunidad.getId())).toList().get(0);
      comunidades.remove(comunidadSeleccionada);
      List<Comunidad> comunidadesAMostrarSeleccion = new ArrayList<>();
      comunidadesAMostrarSeleccion.add(comunidadSeleccionada);
      comunidadesAMostrarSeleccion.addAll(comunidades);
      model.put("comunidades", comunidadesAMostrarSeleccion);
      model.put("servicios", comunidadSeleccionada.getServiciosDeInteres());
      Miembro membresia = RepoMiembros.instance().obtenerMembresiaUsuarioComunidad(user.getId(), comunidadSeleccionada.getId());
      model.put("idMiembro", membresia.getId());
    }

    Template template = handlebars.compile("/templates/aperturaIncidentes");

    String html = template.apply(model);
    limpiarEntityManager();
    return ResponseEntity.status(200).body(html);
  }

  @GetMapping(value="/incidentes/{idIncidente}")
  public ResponseEntity<String> verIncidente(@PathVariable int idIncidente) throws IOException{
    RepoIncidentes repoIncidentes = RepoIncidentes.instance();
    Incidente incidente = repoIncidentes.obtenerIncidente(idIncidente);

    Map<String, Object> model = convertirIncidente(incidente);
    Template template = handlebars.compile("/templates/verIncidente");

    String html = template.apply(model);
    limpiarEntityManager();

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping(value="/incidentes")
  public ResponseEntity<String> consultarIncidentes(@RequestParam(value="estado", required=false) Integer estado) throws IOException{
    RepoIncidentes repoIncidentes = RepoIncidentes.instance();
    List<Incidente> incidentes = null;
    if(estado != null){
      incidentes = repoIncidentes.obtenerIncidentesConEstado(estado);
    }
    else{
      incidentes = repoIncidentes.todos();
    }
    List<Map<String, Object>> incidentesMap = incidentes.stream()
            .map(this::convertirIncidente)
            .collect(Collectors.toList());
    Map<String, Object> model = new HashMap<>();
    model.put("Incidentes",incidentesMap);
    Template template = handlebars.compile("/templates/consultaIncidentesPorEstado");

    String html = template.apply(model);
    limpiarEntityManager();

    return ResponseEntity.status(200).body(html);
  }

  private Map<String,Object> convertirIncidente(Incidente incidente){
    Map<String,Object> mapIncidente = new HashMap<>();
    mapIncidente.put("idIncidente",incidente.getId());
    mapIncidente.put("Entidad",incidente.getServicio().getEntidad().getNombre());
    mapIncidente.put("Servicio",incidente.getServicio().getDescripcion());
    mapIncidente.put("Descripcion",incidente.getDescripcion());
    mapIncidente.put("Estado",incidente.getEstado());
    mapIncidente.put("FechaApertura",incidente.getFechaAperturaBD());
    mapIncidente.put("FechaCierre",incidente.getFechaCierreBD());
    mapIncidente.put("estaAbierto",! incidente.estaCerrado());
    limpiarEntityManager();

    return mapIncidente;
  }

  @GetMapping(value="/apiLiviana/rankings")
  public ResponseEntity<String> consultarRankings(@RequestParam(value="criterioRanking", required=false) Integer idCriterio) throws IOException{
    List<GeneradorInforme> criteriosInforme = RepoCriteriosInforme.instance().obtenerCriterios();

    Map<String,Object> model = new HashMap<>();

    List<Map<String, Object>> criteriosMap = criteriosInforme.stream()
        .map(this::convertirCriterio)
        .collect(Collectors.toList());

    Template template = handlebars.compile("/templates/RankingLiviano");

    if(idCriterio != null) {
      Informe informe = RepoInformesRanking.instance().obtenerInformeRecientePorCriterio(idCriterio);
      List<Map<String, Object>> entidadesMap = informe.getEntidades().stream()
          .map(e -> convertirEntidad(e,informe.getEntidades().indexOf(e) + 1))
          .collect(Collectors.toList());
      model.put("entidades", entidadesMap);
    }

    model.put("criterios", criteriosMap);
    String html = template.apply(model);
    limpiarEntityManager();

    return ResponseEntity.status(200).body(html);
  }
  private Map<String,Object> convertirCriterio(GeneradorInforme criterio){
    Map<String,Object> mapCriterios = new HashMap<>();
    mapCriterios.put("nombre", criterio.getNombre());
    mapCriterios.put("idCriterio", criterio.getId());
    return mapCriterios;
  }

  private Map<String,Object> convertirEntidad(Entidad entidad, Integer posicion){
    Map<String,Object> mapCriterios = new HashMap<>();
    mapCriterios.put("nombre", entidad.getNombre());
    mapCriterios.put("posicion", posicion);
    return mapCriterios;
  }

}
