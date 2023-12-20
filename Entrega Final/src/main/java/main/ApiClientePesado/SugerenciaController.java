
package main.ApiClientePesado;

import Modelo.Incidentes.Incidente;
import Modelo.Servicios.Servicio;
import Persistencia.Repositorios.RepoServicios;
import com.twilio.rest.flexapi.v1.interaction.interactionchannel.InteractionChannelInviteCreator;
import main.ApiClientePesado.Servicios.ServicioSugerenciaIncidentes;
import main.ApiClientePesado.dto.IncidentResponse;
import org.hsqldb.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@RestController
public class SugerenciaController {

  @Autowired
  ServicioSugerenciaIncidentes servicio;

  @GetMapping("/apiPesada/sugerenciaIncidente")
  public ResponseEntity<IncidentResponse> revisarIncidente(@RequestParam("sessionId") String sessionId){
    System.out.println("[GET] /apiPesada/sugerenciaIncidente - Session: "+sessionId);
    IncidentResponse resp = servicio.incidenteASugerir(sessionId);
    return ResponseEntity.ok(resp);
  }
}

