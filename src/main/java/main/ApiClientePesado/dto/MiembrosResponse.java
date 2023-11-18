package main.ApiClientePesado.dto;

import Modelo.Comunidades.Miembro;
import Modelo.Personas.Usuario;

import java.util.List;

public class MiembrosResponse {
    List<Miembro> miembros;

    public MiembrosResponse(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    public List<Miembro> getUsuarios() {
        return miembros;
    }

    public void setUsuarios(List<Miembro> miembros) {
        this.miembros= miembros;
    }
}
