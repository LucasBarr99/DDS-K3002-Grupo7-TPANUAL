package main.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

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

        return "DatosIncidente";
    }

    @GetMapping("/comunidades")
    public String comunidades(){
        return "Comunidades";
    }

    @GetMapping("/comunidades/{id}/usuarios")
    public String administrarUsuarios(){
        return "AdministracionDeRolesDeUsuario";
    }

}
