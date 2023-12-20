package main.ApiClientePesado.Servicios;

import Modelo.Personas.TipoUsuario;
import Modelo.Personas.Usuario;
import Persistencia.Repositorios.RepoUsuarios;
import main.ApiClientePesado.SesionManager;
import main.ApiClientePesado.dto.LoginRequest;
import main.ApiClientePesado.dto.ValidacionRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioLogin {

    List<String> vistasUserAdminSinAcceso = new ArrayList<String>();
    List<String> vistasUserAdminComuSinAcceso = List.of("cargaMasiva");
    List<String> vistasUserBasicoSinAcceso = Arrays.asList("cargaMasiva","usuarios");

    public String handleLogin(LoginRequest newLoginRequest) {
        String user = newLoginRequest.getUsername();
        String passwordIngresada = newLoginRequest.getPassword();
        System.out.println("User: "+user);
        System.out.println("Pass: "+passwordIngresada);
        RepoUsuarios repo = RepoUsuarios.instance();
        Usuario usuarioBuscado = repo.obtenerUsuario(user);
        String passwordAlmacenada = usuarioBuscado.getContrasenia();
        System.out.println("Pass almacenada: "+passwordAlmacenada);
        //AGREGAR VALIDACION POR SI NO EXISTE EL USUARIO??

        if(! Objects.equals(passwordIngresada,passwordAlmacenada )){
            System.out.println("[ERROR] Las contraseñas no coinciden");
            String json = "{\"sesionId\":\"\"}";
            return json;
        }

        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crearSesion("usuario",usuarioBuscado);
        System.out.println("Id de Sesion: "+idSesion);
        TipoUsuario tipo = usuarioBuscado.getTipo();
        sesionManager.agregarAtributo(idSesion,"rol", tipo);
        System.out.println("ROL: "+tipo);
        String json = "{\"sesionId\":\""+idSesion+"\"}";
        return json;
    }

    public String handleLoginOauth(LoginRequest newLoginRequest) {
        String user = newLoginRequest.getUsername();
        System.out.println("User: "+user);
        RepoUsuarios repo = RepoUsuarios.instance();
        Usuario usuarioBuscado = repo.obtenerUsuario(user);

        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crearSesion("usuario",user);
        System.out.println("Id de Sesion: "+idSesion);
        TipoUsuario tipo = usuarioBuscado.getTipo();
        sesionManager.agregarAtributo(idSesion,"rol", tipo);
        System.out.println("ROL: "+tipo);
        String json = "{\"sesionId\":\""+idSesion+"\"}";
        return json;
    }

    public String validar(ValidacionRequest validacionRequest) {
        String sesionId = validacionRequest.getSessionId();
        String url = validacionRequest.getUrl();
        SesionManager sesionManager = SesionManager.get();
        String json = "{\"esValido\":"+false+"}";
        if(sesionManager.esValido(sesionId)){
            Boolean tienePermiso = true;
            Map<String, Object> atributos = sesionManager.obtenerAtributos(sesionId);
            TipoUsuario rol = (TipoUsuario) atributos.get("rol");
            System.out.println("ROL: "+rol);
            if(rol == TipoUsuario.ADMINPRESTADORA){
                tienePermiso = ! this.vistasUserAdminSinAcceso.contains(url);
            } else if (rol == TipoUsuario.ADMINCOMUNIDAD) {
                tienePermiso = ! this.vistasUserAdminComuSinAcceso.contains(url);
            }
            else if (rol == TipoUsuario.BASICO) {
                tienePermiso = ! this.vistasUserBasicoSinAcceso.contains(url);
            }
            json = "{\"esValido\":"+true+", \"tienePermiso\":"+tienePermiso+"}";
        }
        return json;
    }


}
