package main.ApiClientePesado.Servicios;

import Modelo.Comunidades.Miembro;
import Modelo.Comunidades.TipoMiembro;
import Persistencia.Repositorios.RepoComunidades;
import Persistencia.Repositorios.RepoMiembros;
import main.ApiClientePesado.dto.MiembroUpdateRequest;
import main.ApiClientePesado.dto.MiembrosResponse;
import org.springframework.stereotype.Service;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

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

    public void actualizarRolMiembro(MiembroUpdateRequest solicitud){
        EntityManager em = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Miembro miembro = RepoMiembros.instance().obtenerMiembro(solicitud.getMiembroId());
        if(Objects.equals(solicitud.getNuevoRol(), TipoMiembro.DE_SERVICIO.toString())){
            miembro.setTipo(TipoMiembro.DE_SERVICIO);
        } else {
            miembro.setTipo(TipoMiembro.OBSERVADOR);
        }
        transaction.begin();
        em.merge(miembro);
        transaction.commit();
    }

}
