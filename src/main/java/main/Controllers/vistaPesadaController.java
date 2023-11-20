package main.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class vistaPesadaController {

    @GetMapping("/home")
    public String home(){

        return "index";
    }
    @GetMapping("/cargaMasiva")
    public String cargaMasiva(){

        return "cargamasiva";
    }

    @GetMapping("/sugerenciaIncidente")
    public String sugerenciaIncidente(){

        return "sugerenciaIncidente";
    }

    @GetMapping("/comunidades")
    public String comunidades(){
        return "Comunidades";
    }

    @GetMapping("/comunidades/{id}/usuarios")
    public String administrarUsuarios(){
        return "AdministracionDeRolesDeUsuario";
    }

    @GetMapping("/rankings")
    public String rankings(){
        return "Rankings";
    }

    @GetMapping("/errorPermisos")
    public String errorPermisos(){
        return "ErrorPermisos";
    }

}
