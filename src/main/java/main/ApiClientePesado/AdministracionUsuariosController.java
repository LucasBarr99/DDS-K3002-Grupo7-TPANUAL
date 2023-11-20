package main.ApiClientePesado;

import main.ApiClientePesado.Servicios.ServicioAdministracionUsuarios;
import main.ApiClientePesado.dto.MiembroUpdateRequest;
import main.ApiClientePesado.dto.MiembrosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdministracionUsuariosController {
    @Autowired
    ServicioAdministracionUsuarios servicioAdministracion;



    @GetMapping("/apiPesada/comunidades/{idComunidad}/usuarios")
    public ResponseEntity<MiembrosResponse> revisarIncidente(@PathVariable int idComunidad){
        System.out.println("[GET] /apiPesada/comunidades/"+idComunidad+"/usuarios");
        MiembrosResponse resp = servicioAdministracion.obtenerMiembrosDeComunidad(idComunidad);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/apiPesada/comunidades/{idComunidad}/usuarios")
    public void cambiarRolMiembro(@PathVariable int idComunidad,@RequestBody MiembroUpdateRequest miembroUpdateRequest){
        System.out.println("[POST] /apiPesada/comunidades/"+idComunidad+"/usuarios - Actualizacion de ROL");
        System.out.println("miembroId: "+miembroUpdateRequest.getMiembroId());
        System.out.println("nuevoROl: "+miembroUpdateRequest.getNuevoRol());
        servicioAdministracion.actualizarRolMiembro(miembroUpdateRequest);
    }

}
