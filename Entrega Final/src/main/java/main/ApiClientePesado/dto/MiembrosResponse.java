package main.ApiClientePesado.dto;

import Modelo.Comunidades.Miembro;
import Modelo.Personas.Usuario;

import java.util.List;

public class MiembrosResponse {
    List<Miembro> miembros;

    public MiembrosResponse(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    public MiembrosResponse(){

    }
    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros= miembros;
    }
}
