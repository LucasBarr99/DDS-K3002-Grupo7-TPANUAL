
package main.ApiClienteLiviano;

import Modelo.Comunidades.Comunidad;
import Modelo.Comunidades.Miembro;
import Modelo.Comunidades.TipoMiembro;
import Modelo.Incidentes.Incidente;
import Modelo.Personas.TipoUsuario;
import Modelo.Personas.Usuario;
import Persistencia.Repositorios.RepoComunidades;
import Persistencia.Repositorios.RepoMiembros;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import main.ApiClientePesado.dto.MiembroUpdateRequest;
import main.ApiClientePesado.dto.MiembrosResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdministracionUsuariosControllerLiviano {

  private final Handlebars handlebars = new Handlebars();

  public void limpiarEntityManager(){
    PerThreadEntityManagers.getEntityManager();
    PerThreadEntityManagers.closeEntityManager();
  }
  @GetMapping("/apiLiviana/comunidades/{idComunidad}/usuarios")
  public ResponseEntity<String> revisarMiembros(@PathVariable int idComunidad) throws IOException {
    Map<String,Object> model = new HashMap<>();

    List<Miembro> miembros = RepoComunidades.instance().obtenerMiembrosDeComunidad(idComunidad);
    List<Map<String, Object>> miembrosMap = miembros.stream()
        .map(this::convertirMiembro)
        .collect(Collectors.toList());

    model.put("miembros", miembrosMap);


    Template template = handlebars.compile("/templates/AdministracionDeRolesDeUsuarioLiviano");

    String html = template.apply(model);

    limpiarEntityManager();
    return ResponseEntity.status(200).body(html);
  }

  @PostMapping("/miembros/{idMiembro}/nuevoRol")
  public String cambiarRolMiembro(@PathVariable String idMiembro,@RequestParam("rol") String rol){
    Miembro miembro = RepoMiembros.instance().obtenerMiembro(idMiembro);
    List<Miembro> miembros = new ArrayList<>();

    miembros.add(miembro);

    List<Comunidad> comunidades = RepoComunidades.instance().obtenerTodos();

    Comunidad comunidadBuscada = comunidades.stream().filter(comunidad -> comunidad.tieneMiembros(miembros)).collect(Collectors.toList()).get(0);


    if(rol.equalsIgnoreCase("OBSERVADOR")){
      miembro.setTipo(TipoMiembro.OBSERVADOR);
    } else {
      miembro.setTipo(TipoMiembro.DE_SERVICIO);
    }

    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.merge(miembro);
    transaction.commit();

    em.close();
    limpiarEntityManager();

    return "redirect:/apiLiviana/comunidades/"+String.valueOf(comunidadBuscada.getId())+"/usuarios";

  }

  private Map<String,Object> convertirMiembro(Miembro miembro){
    Map<String,Object> mapMiembros = new HashMap<>();
    mapMiembros.put("nombre", miembro.getNombre());
    mapMiembros.put("apellido", miembro.getApellido());
    mapMiembros.put("idMiembro", miembro.getId());
    mapMiembros.put("esDeServicio", miembro.getTipo() == TipoMiembro.DE_SERVICIO);
    mapMiembros.put("esObservador", miembro.getTipo() == TipoMiembro.OBSERVADOR);

    return mapMiembros;
  }
}
