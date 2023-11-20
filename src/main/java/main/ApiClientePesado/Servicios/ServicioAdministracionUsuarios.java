package main.ApiClientePesado.Servicios;

import Modelo.Comunidades.Miembro;
import Persistencia.Repositorios.RepoComunidades;
import main.ApiClientePesado.dto.MiembrosResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Objects;

@Service
public class ServicioAdministracionUsuarios {

    public MiembrosResponse obtenerMiembrosDeComunidad(int idComunidad){
        List<Miembro> miembros = RepoComunidades.instance().obtenerMiembrosDeComunidad(idComunidad);
        return new MiembrosResponse(miembros);
    }

}
