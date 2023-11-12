package main.ApiClientePesado.dto;

public class LoginResponse {
    public String idSesion;

    public LoginResponse(String sesionID){
        this.idSesion = sesionID;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }


}
