package main.ApiClientePesado;

import main.ApiClientePesado.Servicios.servicioLogin;
import main.ApiClientePesado.dto.LoginRequest;
import main.ApiClientePesado.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hola")
public class LoginController {

    public class RankingController {
        @Autowired
        servicioLogin servicioLogin;

        @GetMapping
        public String getHola(){
            return "Hola";
        }

        @PostMapping
        public String postLogin(@RequestBody LoginRequest newLoginRequest){
            System.out.println("[POST] /apiPesada/login Request: "+newLoginRequest);
            return servicioLogin.handleLogin(newLoginRequest);
        }


    }
}
