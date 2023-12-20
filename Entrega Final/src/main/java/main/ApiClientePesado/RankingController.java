package main.ApiClientePesado;

import jakarta.servlet.http.HttpServletResponse;
import main.ApiClientePesado.Servicios.ServicioRankingIncidentes;
import main.ApiClientePesado.Servicios.ServicioSugerenciaIncidentes;
import main.ApiClientePesado.dto.CriterioResponse;
import main.ApiClientePesado.dto.InformeResponse;
import main.ApiClientePesado.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RankingController {

  @Autowired
  ServicioRankingIncidentes servicio;

  @GetMapping(value="/apiPesada/criterios")
  public ResponseEntity<CriterioResponse> getCriterios(){
    System.out.println("[GET] /apiPesada/criterios");
    CriterioResponse resp = servicio.buscarCriterios();
    return ResponseEntity.ok(resp);
  }

  @GetMapping(value="/apiPesada/informes")
  public ResponseEntity<InformeResponse> getInformeSegunCriterio(@RequestParam("idCriterio") Integer idCriterio){
    System.out.println("[GET] /apiPesada/informes criterio: "+idCriterio);
    InformeResponse resp = servicio.buscarInforme(idCriterio);
    return ResponseEntity.ok(resp);
  }
}
