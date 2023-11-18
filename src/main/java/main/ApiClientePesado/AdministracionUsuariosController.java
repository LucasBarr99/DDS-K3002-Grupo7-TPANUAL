package main.ApiClientePesado;

import main.ApiClientePesado.Servicios.ServicioAdministracionUsuarios;
import main.ApiClientePesado.dto.MiembrosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministracionUsuariosController {
    @Autowired
    ServicioAdministracionUsuarios servicioAdministracion;



    @GetMapping("/apiPesada/comunidades/{idComunidad}/usuarios")
    public ResponseEntity<MiembrosResponse> revisarIncidente(@PathVariable int idComunidad){
        MiembrosResponse resp = servicioAdministracion.obtenerMiembrosDeComunidad(idComunidad);
        return ResponseEntity.ok(resp);
    }


}
