package main.ControllersOauth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginOauthController {

  @GetMapping("/login")
    public String home(){

    return "login";
    }

  /*@GetMapping("/secured")
    public String secured(){
      return "Bienvenido, ya se encuentra logueado";
    }*/


}