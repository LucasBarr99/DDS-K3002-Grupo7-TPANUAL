package main.ApiClientePesado.dto;

public class MiembroUpdateRequest {

    String miembroId;
    String nuevoRol;

    public MiembroUpdateRequest(){}

    public MiembroUpdateRequest(String miembroId) {
        this.miembroId = miembroId;
    }

    public String getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(String miembroId) {
        this.miembroId = miembroId;
    }

    public String getNuevoRol() {
        return nuevoRol;
    }

    public void setNuevoRol(String nuevoRol) {
        this.nuevoRol = nuevoRol;
    }


}
