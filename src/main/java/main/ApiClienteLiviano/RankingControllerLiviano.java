package main.ApiClienteLiviano;

import Modelo.Comunidades.Miembro;
import Modelo.Comunidades.TipoMiembro;
import Modelo.InformeRanking.GeneradorInforme;
import Modelo.InformeRanking.Informe;
import Persistencia.Repositorios.RepoComunidades;
import Persistencia.Repositorios.RepoCriteriosInforme;
import Persistencia.Repositorios.RepoInformesRanking;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import main.ApiClientePesado.dto.CriterioResponse;
import main.ApiClientePesado.dto.InformeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RankingControllerLiviano {
  private final Handlebars handlebars = new Handlebars();


  @GetMapping(value="/apiLiviana/informes")
  public ResponseEntity<String> getInformeSegunCriterio(@RequestParam("criterioRanking") Integer idCriterio) throws IOException {
    System.out.println("[GET] /apiPesada/informes criterio: "+idCriterio);
    Map<String,Object> model = new HashMap<>();
    Informe informe = RepoInformesRanking.instance().obtenerInformeRecientePorCriterio(idCriterio);

    model.put("entidades", informe.getEntidades());

    Template template = handlebars.compile("/templates/RankingLiviano");

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);

  }





}

