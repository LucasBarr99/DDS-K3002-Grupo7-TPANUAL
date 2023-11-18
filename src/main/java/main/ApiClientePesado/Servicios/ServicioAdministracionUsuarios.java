package main.ApiClientePesado.Servicios;

import Modelo.Comunidades.Miembro;
import Persistencia.Repositorios.RepoComunidades;
import main.ApiClientePesado.dto.MiembrosResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAdministracionUsuarios {

    public MiembrosResponse obtenerMiembrosDeComunidad(int idComunidad){
        List<Miembro> miembros = RepoComunidades.instance().obtenerMiembrosDeComunidad(idComunidad);
        return new MiembrosResponse(miembros);
    }

}
