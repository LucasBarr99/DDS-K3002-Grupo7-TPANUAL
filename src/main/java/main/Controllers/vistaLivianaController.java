package main.Controllers;


import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.Incidentes.Incidente;
import Modelo.Personas.Usuario;
import Persistencia.Repositorios.RepoComunidades;
import Persistencia.Repositorios.RepoIncidentes;
import Persistencia.Repositorios.RepoMiembros;
import Persistencia.Repositorios.RepoUsuarios;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import main.ApiClientePesado.SesionManager;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

  @GetMapping(value = "/aperturaIncidentes", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> aperturaIncidentes(@RequestParam("sesion") String idSesion,@RequestParam(value="comunidad", required=false) String idComunidad) throws IOException {

    Map<String, Object> model = new HashMap<>();
    System.out.println(" idSesion: "+idSesion);
    SesionManager sesionManager = SesionManager.get();
    Map<String, Object> atributos = sesionManager.obtenerAtributos(idSesion);
    String nombreUsuario = (String) atributos.get("usuario");
    Usuario user = RepoUsuarios.instance().obtenerUsuario(nombreUsuario);
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
    return ResponseEntity.status(200).body(html);
  }

  @GetMapping(value="/incidentes/{idIncidente}")
  public ResponseEntity<String> verIncidente(@PathVariable int idIncidente) throws IOException{
    RepoIncidentes repoIncidentes = RepoIncidentes.instance();
    Incidente incidente = repoIncidentes.obtenerIncidente(idIncidente);

    Map<String, Object> model = new HashMap<>();
      model.put("idIncidente",idIncidente);
      model.put("Entidad",incidente.getServicio().getEntidad().getNombre());
      model.put("Servicio",incidente.getServicio().getDescripcion());
      model.put("Descripcion",incidente.getDescripcion());
      model.put("Estado",incidente.getEstado());
      model.put("FechaApertura",incidente.getFechaAperturaBD());
      model.put("FechaCierre",incidente.getFechaCierreBD());
      model.put("estaAbierto",! incidente.estaCerrado());
    Template template = handlebars.compile("/templates/verIncidente");

    String html = template.apply(model);
    return ResponseEntity.status(200).body(html);
  }

}
