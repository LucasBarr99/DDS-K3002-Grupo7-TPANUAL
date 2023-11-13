package main.ApiClientePesado.Servicios;

import Modelo.Personas.Usuario;
import Persistencia.Repositorios.RepoUsuarios;
import Persistencia.Repositorios.Repositorio;
import main.ApiClientePesado.SesionManager;
import main.ApiClientePesado.dto.LoginRequest;
import main.ApiClientePesado.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class servicioLogin {

    public String handleLogin(LoginRequest newLoginRequest) {
        String user = newLoginRequest.getUsername();
        String passwordIngresada = newLoginRequest.getPassword();
        System.out.println("User: "+user);
        System.out.println("Pass: "+passwordIngresada);
        RepoUsuarios repo = RepoUsuarios.instance();
        //Usuario usuarioBuscado = new Usuario(user,password);
        String passwordAlmacenada = repo.obtenerContrasenia(user);
        System.out.println("Pass almacenada: "+passwordAlmacenada);
        //AGREGAR VALIDACION POR SI NO EXISTE EL USUARIO??

        if(! Objects.equals(passwordIngresada,passwordAlmacenada )){
            System.out.println("[ERROR] Las contraseñas no coinciden");
            String json = "{\"sesionId\":\"\"}";
            return json;
        }

        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crearSesion("usuario",user);
        System.out.println("Id de Sesion: "+idSesion);
        //sesionManager.agregarAtributo(idSesion,"rol", obtener de algun lado);
        String json = "{\"sesionId\":\""+idSesion+"\"}";
        return json;
    }

    public String validar(String sesionId) {
        SesionManager sesionManager = SesionManager.get();
        String json = "{\"esValido\":"+false+"}";
        if(sesionManager.esValido(sesionId)){
            json = "{\"esValido\":"+true+"}";
        }
        return json;
    }
}
