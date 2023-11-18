package main.ApiClientePesado;
import jakarta.servlet.http.HttpServletResponse;
import main.ApiClientePesado.Servicios.ServicioLogin;
import main.ApiClientePesado.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

  @Autowired
  ServicioLogin servicioLogin;

  @PostMapping(value="/apiPesada/login",produces = "application/json")
  public String postLogin(@RequestBody LoginRequest newLoginRequest, HttpServletResponse response){
    System.out.println("[POST] /apiPesada/login Request: "+newLoginRequest);
    return servicioLogin.handleLogin(newLoginRequest);
  }

  @PostMapping(value="/apiPesada/validarSesionId",produces = "application/json")
  public String validarSesionId(@RequestBody String sesionId, HttpServletResponse response){
    System.out.println("[POST] /apiPesada/validarSesionId Request: "+sesionId);
    return servicioLogin.validar(sesionId);
  }

}