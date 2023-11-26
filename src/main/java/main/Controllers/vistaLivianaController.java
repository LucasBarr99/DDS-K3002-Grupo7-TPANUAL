package main.Controllers;


import Modelo.Comunidades.Comunidad;
import Modelo.Personas.Usuario;
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
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
  public ResponseEntity<String> aperturaIncidentes(@RequestParam("sesion") String idSesion) throws IOException {

    Map<String, Object> model = new HashMap<>();
    System.out.println(" idSesion: "+idSesion);
    SesionManager sesionManager = SesionManager.get();
    Map<String, Object> atributos = sesionManager.obtenerAtributos(idSesion);
    Usuario user = (Usuario) atributos.get("user");


    Template template = handlebars.compile("/templates/aperturaIncidentes");

    String html = template.apply(model);
    return ResponseEntity.status(200).body(html);
  }

}
