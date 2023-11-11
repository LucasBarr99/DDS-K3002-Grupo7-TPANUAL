package Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @GetMapping("/")
    public String home(){

    return "Bienvenido, aun no se encuentra logueado";
    }

  @GetMapping("/secured")
    public String secured(){
      return "Bienvenido, ya se encuentra logueado";
    }

}
