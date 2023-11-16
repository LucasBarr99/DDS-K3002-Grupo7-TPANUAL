
package main.ApiClientePesado.Servicios;


import Modelo.Incidentes.Incidente;
import Modelo.Personas.Interesado;
import Modelo.Personas.Persona;
import Modelo.Personas.Usuario;
import Persistencia.Repositorios.RepoIncidentes;
import Persistencia.Repositorios.RepoInteresados;
import Persistencia.Repositorios.RepoUsuarios;
import main.ApiClientePesado.dto.IncidentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ServicioSugerenciaIncidentes {

  public IncidentResponse incidenteASugerir(){
    List<Incidente> incidentes = RepoIncidentes.instance().obtenerIncidentesAbiertos();
    Random random = new Random();
    int numeroIncidente =  random.ints(0, incidentes.size()).findFirst().getAsInt();
    Incidente incidente = RepoIncidentes.instance().obtenerIncidentesAbiertos().get(numeroIncidente);

    return new IncidentResponse(incidente.getNombre(),incidente.fechaAperturaBD, incidente.fechaCierreBD,
        incidente.getServicioAfectado().getDescripcion(), incidente.getDescripcion(), incidente.getEstado(),
        incidente.getServicio().getEntidad().getNombre());

  }

}
