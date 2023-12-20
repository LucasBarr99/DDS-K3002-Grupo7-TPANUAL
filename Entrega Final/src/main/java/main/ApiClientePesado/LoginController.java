package main.ApiClientePesado;
import jakarta.servlet.http.HttpServletResponse;
import main.ApiClientePesado.Servicios.ServicioLogin;
import main.ApiClientePesado.dto.LoginRequest;
import main.ApiClientePesado.dto.ValidacionRequest;
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

  @PostMapping(value="/apiPesada/loginOauth",produces = "application/json")
  public String postLoginOauth(@RequestBody LoginRequest newLoginRequest, HttpServletResponse response){
    System.out.println("[POST] /apiPesada/loginOauth Request: "+newLoginRequest);
    return servicioLogin.handleLoginOauth(newLoginRequest);
  }

  @PostMapping(value="/apiPesada/validarSesionId",produces = "application/json")
  public String validarSesionId(@RequestBody ValidacionRequest validacion, HttpServletResponse response){
    System.out.println("[POST] /apiPesada/validarSesionId - sesion: "+validacion.getSessionId() +" para url: /"+validacion.getUrl());
    return servicioLogin.validar(validacion);
  }

}