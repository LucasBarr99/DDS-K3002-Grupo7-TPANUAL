package main.Controllers;


import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.Personas.Usuario;
import Persistencia.Repositorios.RepoComunidades;
import Persistencia.Repositorios.RepoMiembros;
import Persistencia.Repositorios.RepoUsuarios;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import main.ApiClientePesado.SesionManager;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class vistaLivianaController {
  private final Handlebars handlebars = new Handlebars();
  @GetMapping(value = "/homeLiviano", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> home() throws IOException {
    Map<String, Object> model = new HashMap<>();

    Template template = handlebars.compile("/templates/prueba");

    String html = template.apply(model);
    return ResponseEntity.status(200).body(html);
  }

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

}